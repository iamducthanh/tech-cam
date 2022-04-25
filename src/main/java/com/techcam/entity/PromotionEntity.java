package com.techcam.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "promotion")
@Data
public class PromotionEntity extends BaseEntity{
    @Id
    @Column(name = "id", nullable = false)
    private String id = UUID.randomUUID().toString();

    @Column(name = "NAME")
    private String name;

    @Column(name = "DISCOUNT")
    private Long discount;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "TYPE_DISCOUNT")
    private String typeDiscount;

    @Column(name = "STATUS")
    private Boolean status;

    @Column(name = "NOTE")
    private String note;

}
