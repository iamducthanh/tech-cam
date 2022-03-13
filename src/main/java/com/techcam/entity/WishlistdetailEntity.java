package com.techcam.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Dev
 * @version 1.0
 * @since 13.3.2022
 */
@Entity
@Table(name = "wishlistdetail", schema = "poly_techcam", catalog = "")
@IdClass(WishlistdetailEntityPK.class)
public class WishlistdetailEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Wishlist_ID", nullable = false, length = 64)
    private String wishlistId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Product_ID", nullable = false, length = 64)
    private String productId;
    @Basic
    @Column(name = "STATUS", nullable = false, length = 50)
    private String status;
    @Basic
    @Column(name = "NOTE", nullable = true, length = -1)
    private String note;
    @Basic
    @Column(name = "CREATE_DATE", nullable = false)
    private Timestamp createDate;
    @Basic
    @Column(name = "MODIFIER_DATE", nullable = false)
    private Timestamp modifierDate;
    @Basic
    @Column(name = "CREATE_BY", nullable = false, length = 64)
    private String createBy;
    @Basic
    @Column(name = "MODIFIER_BY", nullable = false, length = 64)
    private String modifierBy;
    @Basic
    @Column(name = "DELETE_FLAG", nullable = false)
    private boolean deleteFlag;

    public String getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(String wishlistId) {
        this.wishlistId = wishlistId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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
        WishlistdetailEntity that = (WishlistdetailEntity) o;
        return deleteFlag == that.deleteFlag && Objects.equals(wishlistId, that.wishlistId) && Objects.equals(productId, that.productId) && Objects.equals(status, that.status) && Objects.equals(note, that.note) && Objects.equals(createDate, that.createDate) && Objects.equals(modifierDate, that.modifierDate) && Objects.equals(createBy, that.createBy) && Objects.equals(modifierBy, that.modifierBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wishlistId, productId, status, note, createDate, modifierDate, createBy, modifierBy, deleteFlag);
    }
}
