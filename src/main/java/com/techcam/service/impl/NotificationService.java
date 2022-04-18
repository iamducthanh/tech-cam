package com.techcam.service.impl;

import com.techcam.dto.response.notification.NotificationDto;
import com.techcam.dto.response.product.ProductResponse;
import com.techcam.entity.NotificationEntity;
import com.techcam.repo.INotificationRepo;
import com.techcam.service.INotificationService;
import com.techcam.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 4/9/2022 9:58 PM
 */

@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService {
 private final INotificationRepo notificationRepo;
 private final IProductService productService;

 @Autowired
 private SimpMessageSendingOperations messagingTemplate;
 @Override
 public List<NotificationDto> getAllNotifications() {
  DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
  List<NotificationEntity> notificationEntities = notificationRepo.findAllNotifications();
  List<NotificationDto> notificationDtos = new ArrayList<>();
  notificationEntities.forEach(o -> {
   notificationDtos.add(NotificationDto.builder()
           .id(o.getId())
           .productId(o.getProductId())
           .content(o.getContent())
           .type(o.getType())
           .time(dateFormat.format(o.getModifierDate()))
           .read(o.getRead())
           .build());
  });
  return notificationDtos;
 }

 @Override
 public List<NotificationDto> getTop3() {
  DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
  List<NotificationEntity> notificationEntities = notificationRepo.findTop3(PageRequest.of(0, 4));
  List<NotificationDto> notificationDtos = new ArrayList<>();
  notificationEntities.forEach(o -> {
   ProductResponse productEntity = productService.getById(o.getProductId());
   notificationDtos.add(NotificationDto.builder()
           .id(o.getId())
           .productName(productEntity.getProductName())
           .productId(productEntity.getProductId())
           .content(o.getContent())
           .time(dateFormat.format(o.getModifierDate()))
           .type(o.getType())
           .read(o.getRead())
           .build());
  });
  return notificationDtos;
 }

 @Override
 public List<NotificationEntity> getAllByContent(String content, String productId) {
  return notificationRepo.findAllByContent(content, productId);
 }


 @Override
 public Integer countRead() {
  return notificationRepo.countRead();
 }

 @Override
 public void saveNotification(NotificationEntity notificationEntity) {
  notificationRepo.save(notificationEntity);
 }

 @Override
 public void updateReadNoti(String id) {
  NotificationEntity notificationEntity = notificationRepo.getById(id);
  notificationEntity.setRead(true);
  notificationRepo.save(notificationEntity);
 }

 @Override
 public void addNotification(NotificationDto notificationDto) {
  List<NotificationEntity>  notificationEntities = getAllByContent(notificationDto.getContent(), notificationDto.getProductId());
  if(notificationEntities.isEmpty()){
   NotificationEntity notificationEntity = NotificationEntity.builder()
           .id(UUID.randomUUID().toString())
           .productId(notificationDto.getProductId())
           .content(notificationDto.getContent())
           .createDate(new Date())
           .modifierDate(new Date())
           .createBy("System")
           .modifierBy("System")
           .deleteFlag(false)
           .type("COMMENT")
           .read(false)
           .build();
   notificationRepo.save(notificationEntity);
  } else {
   NotificationEntity notificationUpdate = notificationEntities.get(0);
   notificationUpdate.setModifierDate(new Date());
   notificationRepo.save(notificationUpdate);
  }

  messagingTemplate.convertAndSend("/topic/notify", notificationDto);

 }
}
