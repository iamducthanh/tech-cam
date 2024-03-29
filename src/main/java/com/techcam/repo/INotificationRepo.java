package com.techcam.repo;

import com.techcam.entity.NotificationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author GMO_ThanhND
 * @version 1.0
 * @since 4/9/2022
 */
public interface INotificationRepo extends JpaRepository<NotificationEntity, String> {
    @Query("select o from NotificationEntity o order by o.modifierDate desc ")
    List<NotificationEntity> findAllNotifications();

    @Query("select o from NotificationEntity o where o.content = ?1 and o.productId = ?2")
    List<NotificationEntity> findAllByContent(String content, String productId);

    @Query("select o from NotificationEntity o where o.read = false order by o.modifierDate desc")
    List<NotificationEntity> findTop3(Pageable pageable);

    @Query("select count(o)from NotificationEntity o where o.read = false order by o.modifierDate desc")
    Integer countRead();

    List<NotificationEntity> findAllByReadIsFalse();
}
