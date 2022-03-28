package com.techcam.repo;

import com.techcam.entity.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPromotionRepo extends JpaRepository<PromotionEntity, Long> {

}
