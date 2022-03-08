package com.techcam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "product")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity extends BaseEntity {

    @Column(name = "NAME", nullable = false, length = 100)
    private String name;

    @Column(name = "PRODUCT_CODE", nullable = false, length = 100)
    private String productCode;

    @ManyToOne(optional = false)
    @JoinColumn(name = "category_ID", nullable = false)
    private CategoryEntity category;

    @Lob
    @Column(name = "DETAIL")
    private String detail;

    @Column(name = "STATUS", nullable = false)
    private Boolean status;

    @Lob
    @Column(name = "NOTE")
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public CategoryEntity getcategory() {
        return category;
    }

    public void setcategory(CategoryEntity category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
