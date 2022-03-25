//package com.techcam.entity;
//
//import lombok.Data;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "promotion_product")
//@Data
//public class PromotionProductEntity extends BaseEntity{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    ProductEntity product;
//
//    @ManyToOne
//    @JoinColumn(name = "promotion_id")
//    PromotionEntity promotion;
//}
