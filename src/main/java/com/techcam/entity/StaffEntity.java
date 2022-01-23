package com.techcam.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Table(name = "staff")
@Entity
public class StaffEntity extends BaseEntity {
    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "staff_code", nullable = false)
    private Integer staffCode;

    @Column(name = "avatar", nullable = false)
    private String avatar;

    @Column(name = "role", nullable = false, length = 20)
    private String role;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "count_login_false", nullable = false)
    private Integer countLoginFalse;

    @Lob
    @Column(name = "note")
    private String note;
}
