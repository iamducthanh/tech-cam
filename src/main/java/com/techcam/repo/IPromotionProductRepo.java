package com.techcam.repo;

import com.techcam.entity.PromotionProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPromotionProductRepo extends JpaRepository<PromotionProductEntity, String> {

    Optional<PromotionProductEntity> findByProductIdAndDeleteFlagFalse(String productId);

    List<PromotionProductEntity> findAllByPromotionId(String promotionId);
}
