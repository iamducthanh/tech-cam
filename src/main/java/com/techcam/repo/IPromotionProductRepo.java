package com.techcam.repo;

import com.techcam.entity.PromotionProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPromotionProductRepo extends JpaRepository<PromotionProductEntity, String> {


    List<PromotionProductEntity> findAllByPromotionId(String promotionId);

    List<PromotionProductEntity> findAllByPromotionIdAndDeleteFlagFalse(String promotionId);

    List<PromotionProductEntity> findAllByProductIdInAndDeleteFlagFalse(List<String> productIds);

    List<PromotionProductEntity> findByProductIdAndDeleteFlagFalse(String productId);

    List<PromotionProductEntity> findAllByPromotionIdInAndDeleteFlagFalse(List<String> promotionIds);
}
