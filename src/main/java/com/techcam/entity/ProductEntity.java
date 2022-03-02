package com.techcam.entity;

import javax.persistence.*;

@Table(name = "product")
@Entity
public class ProductEntity extends BaseEntity {
    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CATAGORY_ID", nullable = false)
    private CategoryEntity catagory;

    @Lob
    @Column(name = "DETAIL")
    private String detail;

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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public CategoryEntity getCatagory() {
        return catagory;
    }

    public void setCatagory(CategoryEntity catagory) {
        this.catagory = catagory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
