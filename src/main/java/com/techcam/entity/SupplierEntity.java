package com.techcam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Table(name = "supplier")
@Entity
public class SupplierEntity extends BaseEntity {
    @Column(name = "NAME", nullable = false, length = 200)
    private String name;

    @Column(name = "PHONE_NUMBER", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;

    @Column(name = "ADDRESS", nullable = false, length = 200)
    private String address;

    @Column(name = "STATUS", nullable = false, length = 50)
    private String status;

    @Lob
    @Column(name = "NOTE")
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
