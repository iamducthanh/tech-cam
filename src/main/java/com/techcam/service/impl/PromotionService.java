package com.techcam.service.impl;

import com.techcam.constants.ConstantsErrorCode;
import com.techcam.dto.request.promotion.PromotionRequestDTO;
import com.techcam.dto.response.product.ProductResponseDTO;
import com.techcam.dto.response.PromotionResponseDTO;
import com.techcam.entity.CategoryEntity;
import com.techcam.entity.ProductEntity;
import com.techcam.entity.PromotionEntity;
import com.techcam.entity.PromotionProductEntity;
import com.techcam.exception.TechCamExp;
import com.techcam.mapper.ProductMapper;
import com.techcam.mapper.PromotionMapper;
import com.techcam.repo.IProductRepo;
import com.techcam.repo.IPromotionProductRepo;
import com.techcam.repo.IPromotionRepo;
import com.techcam.service.IPromotionService;
import com.techcam.type.DiscountType;
import com.techcam.util.BeanUtil;
import com.techcam.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PromotionService implements IPromotionService {

    @Autowired
    IPromotionRepo promotionRepository;

    @Autowired
    IPromotionProductRepo promotionProductRepository;

    @Autowired
    IProductRepo productRepository;

    @Autowired
    PromotionMapper promotionMapper;

    @Autowired
    ProductMapper productMapper;


    @Override
    public List<PromotionResponseDTO> getAll() {
        List<PromotionEntity> promotions = promotionRepository.findAllByDeleteFlagFalse();
        List<PromotionResponseDTO> promotionResponseDTOS = promotionMapper.toPromotionResponseDTOs(promotions);
        promotionResponseDTOS.forEach(x -> {
            List<PromotionProductEntity> promotionProducts = promotionProductRepository.findAllByPromotionIdAndDeleteFlagFalse(x.getId());
            List<String> productIds = promotionProducts.stream().map(promotionProduct -> promotionProduct.getProductId()).collect(Collectors.toList());
            x.setProductIds(productIds);
        });
        return promotionResponseDTOS;
    }

    @Override
    public PromotionResponseDTO findById(String id) {
        Optional<PromotionEntity> promotionOptional = promotionRepository.findById(id);
        if (!promotionOptional.isPresent()) {
            throw new TechCamExp(ConstantsErrorCode.PROMOTION_NOT_FOUND);
        }
        PromotionEntity promotionEntity = promotionOptional.get();
        List<PromotionProductEntity> promotionProducts = promotionProductRepository.findAllByPromotionId(promotionEntity.getId());
        List<String> productIds = promotionProducts.stream().map(x -> x.getProductId()).collect(Collectors.toList());
        List<ProductEntity> products = productRepository.findAllByIdInAndDeleteFlagFalse(productIds);
        PromotionResponseDTO promotionResponseDTO = promotionMapper.toPromotionResponseDTO(promotionEntity);
        promotionResponseDTO.setProducts(productMapper.toProductResponseDTOs(products));
        return promotionResponseDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PromotionResponseDTO create(PromotionRequestDTO promotionRequestDTO) {
        List<String> productIds = new ArrayList<>();
        if (promotionRequestDTO.getProductIds().size() > 0) {
            productIds = promotionRequestDTO.getProductIds();
            List<String> promotionIds = promotionProductRepository.findAllByProductIdInAndDeleteFlagFalse(productIds).stream().map(x -> x.getPromotionId()).collect(Collectors.toList());
            List<PromotionEntity> promotions = promotionRepository.findAllByStatusTrueAndIdIn(promotionIds);
            if (promotions.size() > 0) {
                throw new TechCamExp(ConstantsErrorCode.PROMOTION_PRODUCT_EXIST);
            }
        }
        if (promotionRequestDTO.getCategoryIds().size() > 0) {
            List<ProductEntity> products = productRepository.findAllByCategoryIdInAndDeleteFlagFalse(promotionRequestDTO.getCategoryIds());
            productIds = products.stream().map(x -> x.getId()).collect(Collectors.toList());
            List<String> promotionIds = promotionProductRepository.findAllByProductIdInAndDeleteFlagFalse(productIds).stream().map(x -> x.getPromotionId()).collect(Collectors.toList());
            List<PromotionEntity> promotions = promotionRepository.findAllByStatusTrueAndIdIn(promotionIds);
            if (promotions.size() > 0) {
                throw new TechCamExp(ConstantsErrorCode.PROMOTION_PRODUCT_EXIST);
            }
        }
        List<PromotionEntity> promotionList = promotionRepository.findAllByDeleteFlagFalse();
        List<String> promotionIds = promotionList.stream().map(x -> x.getId()).collect(Collectors.toList());
        List<PromotionProductEntity> promotionProductEntityList = promotionProductRepository.findAllByPromotionIdInAndDeleteFlagFalse(promotionIds);
        List<String> productIdTotal = promotionProductEntityList.stream().map(x -> x.getProductId()).collect(Collectors.toList());
        int i = 0;
        for (String x : productIds) {
            for(String id: productIdTotal){
                if(id.equals(x)){
                    i++;
                    break;
                }
            }
        }
        if(i>0){
            promotionList.forEach(x -> {
                if ((promotionRequestDTO.getStartDate().compareTo(x.getEndDate()) <= 0 && promotionRequestDTO.getEndDate().compareTo(x.getEndDate()) >= 0) ||
                        (promotionRequestDTO.getEndDate().compareTo(x.getStartDate()) >= 0 && promotionRequestDTO.getStartDate().compareTo(x.getStartDate()) <= 0)) {
                    throw new TechCamExp(ConstantsErrorCode.PROMOTION_PRODUCT_EXIST);
                }
            });
        }
        if (promotionRequestDTO.getEndDate().compareTo(promotionRequestDTO.getStartDate()) < 0) {
            throw new TechCamExp(ConstantsErrorCode.DATE_ERROR);
        }
        Date now = new Date();
        promotionRequestDTO.setStatus(false);
        if (promotionRequestDTO.getStartDate().compareTo(DateUtil.addHour(now,7)) <= 0 && DateUtil.addDays(promotionRequestDTO.getEndDate(), 1).compareTo(DateUtil.addHour(now,7)) >= 0) {
            promotionRequestDTO.setStatus(true);
        }
        PromotionEntity promotionEntity = promotionMapper.toPromotionEntity(promotionRequestDTO);
        promotionEntity.setId(UUID.randomUUID().toString());
        promotionEntity = promotionRepository.save(promotionEntity);
        String promotionId = promotionEntity.getId();
        List<PromotionProductEntity> promotionProducts = new ArrayList<>();
        if (Objects.nonNull(promotionRequestDTO.getIsAllProduct()) && promotionRequestDTO.getIsAllProduct()) {
            List<ProductEntity> products = productRepository.findAllByDeleteFlagIsFalse();
            productIds = products.stream().map(x -> x.getId()).collect(Collectors.toList());
            if (productIds.size() > 0) {
                products.forEach(x -> {
                    PromotionProductEntity promotionProduct = new PromotionProductEntity();
                    promotionProduct.setPromotionId(promotionId);
                    promotionProduct.setProductId(x.getId());
                    promotionProduct.setId(UUID.randomUUID().toString());
                    promotionProducts.add(promotionProduct);
                });
            }
        }
        if (Objects.nonNull(promotionRequestDTO.getCategoryIds()) && promotionRequestDTO.getCategoryIds().size() > 0) {
            List<ProductEntity> products = productRepository.findAllByCategoryIdInAndDeleteFlagFalse(promotionRequestDTO.getCategoryIds());
            productIds = products.stream().map(x -> x.getId()).collect(Collectors.toList());
            if (productIds.size() > 0) {
                products.forEach(x -> {
                    PromotionProductEntity promotionProduct = new PromotionProductEntity();
                    promotionProduct.setPromotionId(promotionId);
                    promotionProduct.setProductId(x.getId());
                    promotionProduct.setId(UUID.randomUUID().toString());
                    promotionProducts.add(promotionProduct);
                });
            }
        }
        if (Objects.nonNull(promotionRequestDTO.getProductIds()) && promotionRequestDTO.getProductIds().size() > 0) {
            promotionRequestDTO.getProductIds().forEach(x -> {
                PromotionProductEntity promotionProductEntity = new PromotionProductEntity();
                promotionProductEntity.setPromotionId(promotionId);
                promotionProductEntity.setProductId(x);
                promotionProductEntity.setId(UUID.randomUUID().toString());
                promotionProducts.add(promotionProductEntity);
            });
        }
        promotionProductRepository.saveAll(promotionProducts);
        PromotionResponseDTO promotionResponseDTO = promotionMapper.toPromotionResponseDTO(promotionEntity);
        return promotionResponseDTO;
    }

    @Override
    public PromotionResponseDTO findByProductId(String productId) {
        PromotionResponseDTO promotionResponseDTO = new PromotionResponseDTO();
        List<PromotionProductEntity> promotionProducts = promotionProductRepository.findByProductIdAndDeleteFlagFalse(productId);
        if (promotionProducts.size() > 0) {
            List<String> promotionIds = promotionProducts.stream().map(PromotionProductEntity::getPromotionId).collect(Collectors.toList());
            List<PromotionEntity> promotions = promotionRepository.findAllByStatusTrueAndIdIn(promotionIds);
            if (promotions.size() > 0) {
                try {
                    promotionResponseDTO = promotionMapper.toPromotionResponseDTO(promotions.stream().findFirst().get());
                    ProductEntity product = productRepository.findById(productId).get();
                    List<ProductResponseDTO> products = Arrays.asList(productMapper.toProductResponseDTO(product));
                    promotionResponseDTO.setProducts(products);
                } catch (Exception e) {
                    log.error("Sản phẩm không tồn tại");
                }
            }
        }
        return promotionResponseDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PromotionResponseDTO update(String id, PromotionRequestDTO promotionRequestDTO) {
        if (!promotionRepository.findByIdAndDeleteFlagFalse(id).isPresent()) {
            throw new TechCamExp(ConstantsErrorCode.PROMOTION_NOT_FOUND);
        }

        Date now = new Date();
        if (promotionRequestDTO.getStartDate().compareTo(DateUtil.addHour(now,7)) <= 0 && DateUtil.addDays(promotionRequestDTO.getEndDate(), 1).compareTo(DateUtil.addHour(now,7)) >= 0) {
            promotionRequestDTO.setStatus(true);
        } else {
            promotionRequestDTO.setStatus(false);
        }
        PromotionEntity promotionEntity = promotionRepository.findById(id).get();
        promotionEntity.setModifierDate(new Timestamp(new Date().getTime()));
        promotionEntity.setModifierBy(null);
        BeanUtils.copyProperties(promotionRequestDTO, promotionEntity, BeanUtil.getNullPropertyNames(promotionRequestDTO));
        List<PromotionProductEntity> promotionProducts = promotionProductRepository.findAllByPromotionId(id);
        List<String> productIds = promotionProducts.stream().map(x -> x.getProductId()).collect(Collectors.toList());
        List<ProductEntity> products = productRepository.findAllByIdInAndDeleteFlagFalse(productIds);
        if (products.size() > 0) {
            products.forEach(product -> {
                product.setPromotion(0L);
            });
            productRepository.saveAll(products);
            promotionProductRepository.deleteAll(promotionProducts);
        }
        if (Objects.nonNull(promotionRequestDTO.getIsAllProduct()) && promotionRequestDTO.getIsAllProduct()) {
            List<PromotionProductEntity> promotionProductsUpdate = new ArrayList<>();
            products = productRepository.findAllByDeleteFlagIsFalse();
            productIds = products.stream().map(x -> x.getId()).collect(Collectors.toList());
            if (productIds.size() > 0) {
                products.forEach(x -> {
                    PromotionProductEntity promotionProduct = new PromotionProductEntity();
                    promotionProduct.setPromotionId(id);
                    promotionProduct.setProductId(x.getId());
                    promotionProduct.setId(UUID.randomUUID().toString());
                    promotionProductsUpdate.add(promotionProduct);
                });
                promotionProductRepository.saveAll(promotionProductsUpdate);
            }
        }
        if (Objects.nonNull(promotionRequestDTO.getCategoryIds()) && promotionRequestDTO.getCategoryIds().size() > 0) {
            List<PromotionProductEntity> promotionProductsUpdate = new ArrayList<>();
            products = productRepository.findAllByCategoryIdInAndDeleteFlagFalse(promotionRequestDTO.getCategoryIds());
            productIds = products.stream().map(x -> x.getId()).collect(Collectors.toList());
            if (productIds.size() > 0) {
                List<PromotionEntity> promotionList = promotionRepository.findAllByDeleteFlagFalse();
                promotionList.remove(promotionEntity);
                List<String> promotionIds = promotionList.stream().map(x -> x.getId()).collect(Collectors.toList());
                List<PromotionProductEntity> promotionProductEntityList = promotionProductRepository.findAllByPromotionIdInAndDeleteFlagFalse(promotionIds);
                List<String> productIdTotal = promotionProductEntityList.stream().map(x -> x.getProductId()).collect(Collectors.toList());
                int i = 0;
                for (String x : productIds) {
                    if (productIdTotal.contains(x)) {
                        i++;
                        break;
                    }
                }
                if(i>0){
                    promotionList.forEach(x -> {
                        if ((promotionRequestDTO.getStartDate().compareTo(x.getEndDate()) <= 0 && promotionRequestDTO.getEndDate().compareTo(x.getEndDate()) >= 0) ||
                                (promotionRequestDTO.getEndDate().compareTo(x.getStartDate()) >= 0 && promotionRequestDTO.getStartDate().compareTo(x.getStartDate()) <= 0)) {
                            throw new TechCamExp(ConstantsErrorCode.PROMOTION_PRODUCT_EXIST);
                        }
                    });
                }
                products.forEach(x -> {
                    PromotionProductEntity promotionProduct = new PromotionProductEntity();
                    promotionProduct.setPromotionId(id);
                    promotionProduct.setProductId(x.getId());
                    promotionProduct.setId(UUID.randomUUID().toString());
                    promotionProductsUpdate.add(promotionProduct);
                });
                promotionProductRepository.saveAll(promotionProductsUpdate);
            }
        }
        if (Objects.nonNull(promotionRequestDTO.getProductIds()) && promotionRequestDTO.getProductIds().size() > 0) {
            List<PromotionEntity> promotionList = promotionRepository.findAllByDeleteFlagFalse();
            promotionList.remove(promotionEntity);
            List<String> promotionIds = promotionList.stream().map(x -> x.getId()).collect(Collectors.toList());
            List<PromotionProductEntity> promotionProductEntityList = promotionProductRepository.findAllByPromotionIdInAndDeleteFlagFalse(promotionIds);
            List<String> productIdTotal = promotionProductEntityList.stream().map(x -> x.getProductId()).collect(Collectors.toList());
            int i = 0;
            for (String x : promotionRequestDTO.getProductIds()) {
                if (productIdTotal.contains(x)) {
                    i++;
                    break;
                }
            }
            if(i>0){
                promotionList.forEach(x -> {
                    if ((promotionRequestDTO.getStartDate().compareTo(x.getEndDate()) <= 0 && promotionRequestDTO.getEndDate().compareTo(x.getEndDate()) >= 0) ||
                            (promotionRequestDTO.getEndDate().compareTo(x.getStartDate()) >= 0 && promotionRequestDTO.getStartDate().compareTo(x.getStartDate()) <= 0)) {
                        throw new TechCamExp(ConstantsErrorCode.PROMOTION_PRODUCT_EXIST);
                    }
                });
            }
            List<PromotionProductEntity> promotionProductsUpdate = new ArrayList<>();
            promotionRequestDTO.getProductIds().forEach(x -> {
                PromotionProductEntity promotionProductEntity = new PromotionProductEntity();
                promotionProductEntity.setPromotionId(id);
                promotionProductEntity.setProductId(x);
                promotionProductEntity.setId(UUID.randomUUID().toString());
                promotionProductsUpdate.add(promotionProductEntity);
            });
            promotionProductRepository.saveAll(promotionProductsUpdate);
        }
        if (Objects.nonNull(promotionRequestDTO.getStatus()) && promotionRequestDTO.getStatus()) {
            if (promotionEntity.getStartDate().compareTo(DateUtil.addHour(now,7)) <= 0 && DateUtil.addDays(promotionEntity.getEndDate(), 1).compareTo(DateUtil.addHour(now,7)) >= 0) {
                productIds = promotionProductRepository.findAllByPromotionIdAndDeleteFlagFalse(promotionEntity.getId()).stream().map(promotionProduct -> promotionProduct.getProductId()).collect(Collectors.toList());
                products = productRepository.findAllByIdInAndDeleteFlagFalse(productIds);
                if (products.size() > 0) {
                    products.forEach(product -> {
                        if (promotionEntity.getTypeDiscount().equals(DiscountType.MONEY.name())) {
                            product.setPromotion(product.getPrice() - promotionEntity.getDiscount());
                        }
                        if (promotionEntity.getTypeDiscount().equals(DiscountType.PERCENT.name())) {
                            product.setPromotion(product.getPrice() - product.getPrice() * promotionEntity.getDiscount() / 100);
                        }
                    });
                    productRepository.saveAllAndFlush(products);
                }
            }
        }
        promotionRepository.save(promotionEntity);
        return promotionMapper.toPromotionResponseDTO(promotionEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        if (!promotionRepository.findByIdAndDeleteFlagFalse(id).isPresent()) {
            throw new TechCamExp(ConstantsErrorCode.PROMOTION_NOT_FOUND);
        }
        PromotionEntity promotionEntity = promotionRepository.findById(id).get();
        promotionEntity.setDeleteFlag(true);
        List<PromotionProductEntity> promotionProducts = promotionProductRepository.findAllByPromotionId(id);
        if (promotionProducts.size() > 0) {
            List<String> productIds = promotionProducts.stream().map(x -> x.getProductId()).collect(Collectors.toList());
            promotionProducts.forEach(x -> {
                x.setDeleteFlag(true);
            });
            List<ProductEntity> products = productRepository.findAllByIdInAndDeleteFlagFalse(productIds);
            products.forEach(product -> product.setPromotion(0L));
            promotionProductRepository.deleteAll(promotionProducts);
            promotionRepository.delete(promotionEntity);
            productRepository.saveAll(products);
        }
    }

    @Override
    public double getPromotionProduct(String id) {
        Double sale = 0.0;
        PromotionResponseDTO promotionResponseDTO = this.findByProductId(id);
        if (Objects.nonNull(promotionResponseDTO.getProducts()) && promotionResponseDTO.getProducts().size() > 0) {
            Long price = promotionResponseDTO.getProducts().stream().findFirst().get().getPrice();
            if (promotionResponseDTO.getTypeDiscount().equals(DiscountType.MONEY.name())) {
                sale = Double.valueOf(promotionResponseDTO.getDiscount() / price * 100);
            }
            if (promotionResponseDTO.getTypeDiscount().equals(DiscountType.PERCENT.name())) {
                sale = Double.valueOf(promotionResponseDTO.getDiscount());
            }
        }
        return sale;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void activePromotion(String id) {
        if (!promotionRepository.findById(id).isPresent()) {
            throw new TechCamExp(ConstantsErrorCode.PROMOTION_NOT_FOUND);
        }
        PromotionEntity promotion = promotionRepository.getById(id);
        Date now = new Date();
        if (promotion.getStartDate().compareTo(DateUtil.addHour(now,7)) <= 0 && DateUtil.addDays(promotion.getEndDate(), 1).compareTo(DateUtil.addHour(now,7)) >= 0) {
            List<String> productIds = promotionProductRepository.findAllByPromotionIdAndDeleteFlagFalse(promotion.getId()).stream().map(promotionProduct -> promotionProduct.getProductId()).collect(Collectors.toList());
            List<ProductEntity> products = productRepository.findAllByIdInAndDeleteFlagFalse(productIds);
            promotion.setStatus(true);
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
        } else {
//            promotion.setStatus(false);
//            List<String> productIds = promotionProductRepository.findAllByPromotionIdAndDeleteFlagFalse(promotion.getId()).stream().map(promotionProduct -> promotionProduct.getProductId()).collect(Collectors.toList());
//            List<ProductEntity> products = productRepository.findAllByIdInAndDeleteFlagFalse(productIds);
//            if (products.size() > 0) {
//                products.forEach(product -> {
//                    product.setPromotion(0L);
//                });
//                productRepository.saveAll(products);
//            }
            throw new TechCamExp(ConstantsErrorCode.PROMOTION_NOT_ACEPTED);
        }
        promotionRepository.save(promotion);
    }
}
