package com.techcam.api;

import com.techcam.dto.response.notification.NotificationDto;
import com.techcam.service.impl.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 4/9/2022 9:53 PM
 */
@RequiredArgsConstructor
@RestController
public class NotificationApi {
 private final NotificationService notificationService;

 @GetMapping("/api/notification")
 public List<NotificationDto> getAllNotifications(){
  return notificationService.getAllNotifications();
 }
}
