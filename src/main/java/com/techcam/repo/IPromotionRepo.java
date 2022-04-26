package com.techcam.repo;

import com.techcam.entity.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPromotionRepo extends JpaRepository<PromotionEntity, String> {

    List<PromotionEntity> findAllByDeleteFlagFalse();

    Optional<PromotionEntity> findByIdAndStatusTrue(String id);

    List<PromotionEntity> findByStatusTrueAndDeleteFlagFalse();

    Optional<PromotionEntity> findByIdAndDeleteFlagFalse(String id);

    List<PromotionEntity> findAllByStatusTrueAndIdIn(List<String> ids);
}
