package com.techcam.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author DucBV
 * @version 1.0
 * @since 8.3.2022
 */
@Entity
@Table(name = "product", schema = "poly_techcam", catalog = "")
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private String id;
    @Basic
    @Column(name = "NAME")
    private String name;
    @Basic
    @Column(name = "CATEGORY_ID")
    private String categoryId;
    @Basic
    @Column(name = "BRAND_ID")
    private String brandId;
    @Basic
    @Column(name = "PRODUCT_CODE")
    private String productCode;
    @Basic
    @Column(name = "QUANTITY")
    private int quantity;
    @Basic
    @Column(name = "PRICE")
    private long price;
    @Basic
    @Column(name = "DETAIL")
    private String detail;
    @Basic
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic
    @Column(name = "STATUS")
    private String status;
    @Basic
    @Column(name = "NOTE")
    private String note;
    @Basic
    @Column(name = "CREATE_DATE")
    private Timestamp createDate;
    @Basic
    @Column(name = "MODIFIER_DATE")
    private Timestamp modifierDate;
    @Basic
    @Column(name = "CREATE_BY")
    private String createBy;
    @Basic
    @Column(name = "MODIFIER_BY")
    private String modifierBy;
    @Basic
    @Column(name = "DELETE_FLAG")
    private boolean deleteFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getModifierDate() {
        return modifierDate;
    }

    public void setModifierDate(Timestamp modifierDate) {
        this.modifierDate = modifierDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getModifierBy() {
        return modifierBy;
    }

    public void setModifierBy(String modifierBy) {
        this.modifierBy = modifierBy;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return quantity == that.quantity && price == that.price && deleteFlag == that.deleteFlag && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(categoryId, that.categoryId) && Objects.equals(brandId, that.brandId) && Objects.equals(productCode, that.productCode) && Objects.equals(detail, that.detail) && Objects.equals(description, that.description) && Objects.equals(status, that.status) && Objects.equals(note, that.note) && Objects.equals(createDate, that.createDate) && Objects.equals(modifierDate, that.modifierDate) && Objects.equals(createBy, that.createBy) && Objects.equals(modifierBy, that.modifierBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, categoryId, brandId, productCode, quantity, price, detail, description, status, note, createDate, modifierDate, createBy, modifierBy, deleteFlag);
    }
}
