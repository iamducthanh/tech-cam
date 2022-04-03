package com.techcam.service.schedule;

import com.techcam.entity.PromotionEntity;
import com.techcam.repo.IPromotionRepo;
import com.techcam.service.IPromotionProductService;
import com.techcam.service.IPromotionService;
import com.techcam.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

public class ActivePromotion {

    @Autowired
    IPromotionService promotionService;

    @Autowired
    IPromotionProductService promotionProductService;

    @Autowired
    IPromotionRepo promotionRepository;

    @Scheduled(cron = "0 0 * * *")
    public void activePromotion(){
        Date now = new Date();
        List<PromotionEntity> promotionList = promotionRepository.findAllByDeleteFlagFalse();
        promotionList.forEach(promotion -> {
            if(promotion.getStartDate().compareTo(now) >= 0 && DateUtil.addDays(promotion.getEndDate(), 1).compareTo(now) >=0){
                promotion.setStatus(true);
            }else {
                promotion.setStatus(false);
            }
        });
        promotionRepository.saveAllAndFlush(promotionList);
    }

}
