//package com.techcam.repo;
//
//import com.techcam.entity.PromotionEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.Date;
//import java.util.List;
//
//@Repository
//public interface IPromotionRepo extends JpaRepository<PromotionEntity, Long> {
//
//    List<PromotionEntity> findAllByDeleteFlagFalse();
//
//    List<PromotionEntity> findByStartDateGreaterThanOrEqualToAndEndDateLessThanOrEqualTo(Date startDate, Date endDate);
//}
