//package com.techcam.entity;
//
//import lombok.Data;
//
//import javax.persistence.*;
//import java.util.Date;
//
//@Entity
//@Table(name = "promotion")
//@Data
//public class PromotionEntity extends BaseEntity{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id", nullable = false)
//    private Long id;
//
//    @Column(name = "NAME")
//    private String name;
//
//    @Column(name = "DISCOUNT")
//    private Long discount;
//
//    @Column(name = "START_DATE")
//    private Date startDate;
//
//    @Column(name = "END_DATE")
//    private Date endDate;
//
//    @Column(name = "DESCRIPTION")
//    private String description;
//
//    @Column(name = "TYPE_DISCOUNT")
//    private String typeDiscount;
//
//    @Column(name = "STATUS")
//    private Boolean status;
//
//    @Column(name = "NOTE")
//    private String note;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//}
