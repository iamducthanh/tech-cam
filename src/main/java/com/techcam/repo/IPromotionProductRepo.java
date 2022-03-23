package com.techcam.repo;

import com.techcam.entity.PromotionProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPromotionProductRepo extends JpaRepository<PromotionProductEntity, Long> {

}
