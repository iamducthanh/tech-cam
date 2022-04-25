package com.techcam.service.schedule;

import com.techcam.entity.ProductEntity;
import com.techcam.entity.PromotionEntity;
import com.techcam.mapper.ProductMapper;
import com.techcam.mapper.PromotionMapper;
import com.techcam.repo.IProductRepo;
import com.techcam.repo.IPromotionProductRepo;
import com.techcam.repo.IPromotionRepo;
import com.techcam.service.IPromotionProductService;
import com.techcam.service.IPromotionService;
import com.techcam.type.DiscountType;
import com.techcam.util.DateUtil;
import lombok.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ActivePromotion {

    @Autowired
    IPromotionService promotionService;

    @Autowired
    IPromotionProductService promotionProductService;

    @Autowired
    IPromotionRepo promotionRepository;

    @Autowired
    IPromotionProductRepo promotionProductRepository;

    @Autowired
    IProductRepo productRepository;

    @Autowired
    ProductMapper productMapper;

    @Autowired
    PromotionMapper promotionMapper;

    @Scheduled(cron = "0 0 0 * * *")
    @Async
    @Transactional(rollbackFor = Exception.class)
    public void activePromotion() {
        Date now = new Date();
        List<PromotionEntity> promotionList = promotionRepository.findAllByDeleteFlagFalse();
        promotionList.forEach(promotion -> {
            if (promotion.getStartDate().compareTo(now) <= 0 && DateUtil.addDays(promotion.getEndDate(), 1).compareTo(now) >= 0) {
                if (!promotion.getStatus()) {
                    promotion.setStatus(true);
                    List<String> productIds = promotionProductRepository.findAllByPromotionIdAndDeleteFlagFalse(promotion.getId())
                            .stream().map(promotionProduct -> promotionProduct.getProductId()).collect(Collectors.toList());
                    List<ProductEntity> products = productRepository.findAllByIdInAndDeleteFlagFalse(productIds);
                    if (products.size() > 0) {
                        products.forEach(product -> {
                            if (promotion.getTypeDiscount().equals(DiscountType.MONEY.name())) {
                                product.setPromotion(product.getPrice() - promotion.getDiscount());
                            }
                            if (promotion.getTypeDiscount().equals(DiscountType.PERCENT.name())) {
                                product.setPromotion(product.getPrice() - product.getPrice() * promotion.getDiscount() / 100);
                            }
                        });
                        productRepository.saveAllAndFlush(products);
                    }
                }
            }
            if (DateUtil.addDays(promotion.getEndDate(), 1).compareTo(now) <= 0) {
                promotion.setStatus(false);
                List<String> productIds = promotionProductRepository.findAllByPromotionIdAndDeleteFlagFalse(promotion.getId())
                        .stream().map(promotionProduct -> promotionProduct.getProductId()).collect(Collectors.toList());
                List<ProductEntity> products = productRepository.findAllByIdInAndDeleteFlagFalse(productIds);
                if (products.size() > 0) {
                    products.forEach(product -> {
                        product.setPromotion(0L);
                    });
                    productRepository.saveAllAndFlush(products);
                }
            }
        });
        promotionRepository.saveAllAndFlush(promotionList);
    }
}
