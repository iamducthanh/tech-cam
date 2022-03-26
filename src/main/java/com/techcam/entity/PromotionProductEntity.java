package com.techcam.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "promotion_product")
@Data
public class PromotionProductEntity extends BaseEntity{
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "promotion_id", nullable = false)
    private String promotionId;

    @Column(name = "product_id", nullable = false)
    private String product_id;
}
