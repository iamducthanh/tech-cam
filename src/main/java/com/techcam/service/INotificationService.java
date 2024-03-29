package com.techcam.service;

import com.techcam.dto.response.notification.NotificationDto;
import com.techcam.entity.NotificationEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GMO_ThanhND
 * @version 1.0
 * @since 4/9/2022
 */
@Service
public interface INotificationService {
    List<NotificationDto> getAllNotifications();
    List<NotificationDto> getTop3();
    List<NotificationEntity> getAllByContent(String content, String productId);
    Integer countRead();
    void saveNotification(NotificationEntity notificationEntity);
    void updateReadNoti(String id);
    void addNotification(NotificationDto notificationDto);

    void setReadAllNotification();
}
