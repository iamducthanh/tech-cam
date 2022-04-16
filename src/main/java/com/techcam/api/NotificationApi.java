package com.techcam.api;

import com.techcam.dto.response.notification.NotificationDto;
import com.techcam.entity.NotificationEntity;
import com.techcam.service.impl.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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


 @GetMapping("/api/notification/top3")
 public List<NotificationDto> getTop3(){
  return notificationService.getTop3();
 }

 @GetMapping("/api/notification/count")
 public Integer countRead(){
  return notificationService.countRead();
 }

 @CrossOrigin(origins = "http://localhost:8888")
 @PostMapping("/api/notification")
 public void addNotification(@RequestBody NotificationDto notificationDto){
  notificationService.addNotification(notificationDto);
 }

 @GetMapping("/api/notification/read")
 public void readNoti(@RequestParam("id") String id){
  notificationService.updateReadNoti(id);
 }
}
