package com.techcam.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Dev
 * @version 1.0
 * @since 13.3.2022
 */
@Entity
@Table(name = "wishlist", schema = "poly_techcam", catalog = "")
public class WishlistEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false, length = 64)
    private String id;
    @Basic
    @Column(name = "Customer_ID", nullable = false, length = 64)
    private String customerId;
    @Basic
    @Column(name = "OrderDate", nullable = false)
    private Date orderDate;
    @Basic
    @Column(name = "Tax", nullable = false, precision = 0)
    private int tax;
    @Basic
    @Column(name = "PaymentDate", nullable = false)
    private Date paymentDate;
    @Basic
    @Column(name = "ItemQuantity", nullable = true)
    private Integer itemQuantity;
    @Basic
    @Column(name = "TotalPrice", nullable = false, precision = 0)
    private int totalPrice;
    @Basic
    @Column(name = "WishlistStatus", nullable = false, length = 50)
    private String wishlistStatus;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getWishlistStatus() {
        return wishlistStatus;
    }

    public void setWishlistStatus(String wishlistStatus) {
        this.wishlistStatus = wishlistStatus;
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
        WishlistEntity that = (WishlistEntity) o;
        return tax == that.tax && totalPrice == that.totalPrice && deleteFlag == that.deleteFlag && Objects.equals(id, that.id) && Objects.equals(customerId, that.customerId) && Objects.equals(orderDate, that.orderDate) && Objects.equals(paymentDate, that.paymentDate) && Objects.equals(itemQuantity, that.itemQuantity) && Objects.equals(wishlistStatus, that.wishlistStatus) && Objects.equals(status, that.status) && Objects.equals(note, that.note) && Objects.equals(createDate, that.createDate) && Objects.equals(modifierDate, that.modifierDate) && Objects.equals(createBy, that.createBy) && Objects.equals(modifierBy, that.modifierBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, orderDate, tax, paymentDate, itemQuantity, totalPrice, wishlistStatus, status, note, createDate, modifierDate, createBy, modifierBy, deleteFlag);
    }
}
