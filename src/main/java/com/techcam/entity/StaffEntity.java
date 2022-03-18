package com.techcam.entity;

import lombok.ToString;
import org.apache.commons.lang3.RandomStringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author DucBV
 * @version 1.0
 * @since 8.3.2022
 */
@ToString
@Entity
@Table(name = "staff", schema = "poly_techcam", catalog = "")
public class StaffEntity extends BaseEntity implements Serializable {
    @Id
    @Column(name = "ID")
    private String id = RandomStringUtils.randomNumeric(6);
    @Basic
    @Column(name = "FULL_NAME")
    private String fullName;
    @Basic
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    @Basic
    @Column(name = "EMAIL")
    private String email;
    @Basic
    @Column(name = "ADDRESS")
    private String address;
    @Basic
    @Column(name = "DATE_OF_BIRTH")
    private Date dateOfBirth;
    @Basic
    @Column(name = "STAFF_CODE")
    private int staffCode;
    @Basic
    @Column(name = "IDENTITY_NUMBER")
    private String identityNumber;
    @Basic
    @Column(name = "AVATAR")
    private String avatar;
    @Basic
    @Column(name = "ROLE")
    private String role;
    @Basic
    @Column(name = "USERNAME")
    private String username;
    @Basic
    @Column(name = "PASSWORD")
    private String password;
    @Basic
    @Column(name = "COUNT_LOGIN_FALSE")
    private int countLoginFalse;
    @Basic
    @Column(name = "STATUS")
    private String status;
    @Basic
    @Column(name = "NOTE")
    private String note;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(int staffCode) {
        this.staffCode = staffCode;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCountLoginFalse() {
        return countLoginFalse;
    }

    public void setCountLoginFalse(int countLoginFalse) {
        this.countLoginFalse = countLoginFalse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
