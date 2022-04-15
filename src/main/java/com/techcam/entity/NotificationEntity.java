package com.techcam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 4/15/2022 9:50 PM
 */
@Entity
@Data
@Table(name = "notification", schema = "poly_techcam", catalog = "")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity {
 @Id
 private String id;
 @Column(name = "product_id")
 private String productId;
 private String content;
 @Column(name = "TYPE")
 private String type;
 @Column(name = "READ")
 private Boolean read;
 @Column(name = "CREATE_DATE")
 private Date createDate;
 @Column(name = "MODIFY_DATE")
 private Date modifyDate;
 @Column(name = "CREATE_BY")
 private String createBy;
 @Column(name = "MODIFY_BY")
 private String modifyBy;
 @Column(name = "DELETE_FLAG")
 private boolean deleteFlag;

}
