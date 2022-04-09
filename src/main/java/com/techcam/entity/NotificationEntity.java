package com.techcam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 4/9/2022 9:34 PM
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "notification")
public class NotificationEntity {
 @Id
 @Column(name = "id")
 private Integer id;

 @Column(name = "product_id")
 private String productId;

 @Column(name = "content")
 private String content;

 @Column(name = "CREATE_DATE")
 private java.sql.Timestamp createDate;

 @Column(name = "MODIFY_DATE")
 private java.sql.Timestamp modifyDate;

 @Column(name = "CREATE_BY")
 private String createBy;

 @Column(name = "MODIFY_BY")
 private String modifyBy;

 @Column(name = "DELETE_FLAG")
 private Boolean deleteFlag;

 @Column(name = "URL")
 private String url;

 @Column(name = "READ")
 private Boolean read;
}
