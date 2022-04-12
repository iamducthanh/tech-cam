package com.techcam.dto.response.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 4/9/2022 9:54 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDto {
 private String productId;
 private String productName;
 private String content;
 private String time;
 private Boolean read;
 private String url;
}
