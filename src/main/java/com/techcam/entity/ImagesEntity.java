package com.techcam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author DucBV
 * @version 1.0
 * @since 8.3.2022
 */
@Entity
@Table(name = "images", schema = "poly_techcam", catalog = "")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImagesEntity extends BaseEntity {
    @Id
    @Column(name = "ID")
    private String id;
    @Basic
    @Column(name = "IMAGES_NAME")
    private String imagesName;
    @Basic
    @Column(name = "IMAGES_LINK")
    private String imagesLink;
    @Basic
    @Column(name = "PRODUCT_ID")
    private String productId;
    @Basic
    @Column(name = "STATUS")
    private String status;
    @Basic
    @Column(name = "NOTE")
    private String note;

}
