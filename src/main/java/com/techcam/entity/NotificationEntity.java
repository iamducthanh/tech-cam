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
@Table(name = "notifications", schema = "poly_techcam", catalog = "")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity {
 @Id
 private String id;
 @Column(name = "product_id")
 private String productId;
 private String content;
// @Column(name = "TYPE")
 private String type;
// @Column(name = "READ")
 private Boolean read;
 @Column(name = "create_date")
 private Date createDate;
 @Column(name = "modify_date")
 private Date modifyDate;
 @Column(name = "create_by")
 private String createBy;
 @Column(name = "modify_by")
 private String modifyBy;
 @Column(name = "delete_flag")
 private boolean deleteFlag;

}
