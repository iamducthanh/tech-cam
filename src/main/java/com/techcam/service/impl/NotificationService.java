package com.techcam.service.impl;

import com.techcam.dto.response.notification.NotificationDto;
import com.techcam.dto.response.product.ProductResponse;
import com.techcam.entity.NotificationEntity;
import com.techcam.repo.INotificationRepo;
import com.techcam.service.INotificationService;
import com.techcam.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
 @Override
 public List<NotificationDto> getAllNotifications() {
  DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
  List<NotificationEntity> notificationEntities = notificationRepo.findAllNotifications();
  List<NotificationDto> notificationDtos = new ArrayList<>();
  notificationEntities.forEach(o -> {
   ProductResponse productEntity = productService.getById(o.getProductId());
   notificationDtos.add(NotificationDto.builder()
//           .productName(productEntity.getProductName())
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
  List<NotificationEntity> notificationEntities = notificationRepo.findTop3(PageRequest.of(0, 3));
  List<NotificationDto> notificationDtos = new ArrayList<>();
  notificationEntities.forEach(o -> {
   ProductResponse productEntity = productService.getById(o.getProductId());
   notificationDtos.add(NotificationDto.builder()
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
 public Integer countRead() {
  return notificationRepo.countRead();
 }

 @Override
 public void saveNotification(NotificationEntity notificationEntity) {
  notificationRepo.save(notificationEntity);
 }
}
