package com.techcam.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Table(name = "brand")
@Entity
public class BrandEntity extends BaseEntity {
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "img_logo", nullable = false)
    private String imgLogo;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "note")
    private Integer note;
}
