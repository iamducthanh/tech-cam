package com.techcam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Dev
 * @version 1.0
 * @since 13.3.2022
 */
@Entity
@Table(name = "goodsreceipt", schema = "poly_techcam", catalog = "")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoodsreceiptEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false, length = 64)
    private String id;
    @Basic
    @Column(name = "Product_ID", nullable = false, length = 64)
    private String productId;
    @Basic
    @Column(name = "TotalQuantity", nullable = false)
    private int totalQuantity;
    @Basic
    @Column(name = "TotalAmount", nullable = false, precision = 0)
    private int totalAmount;
    @Basic
    @Column(name = "Supplier_ID", nullable = false, length = 64)
    private String supplierId;
    @Basic
    @Column(name = "ReceiptStatus", nullable = false, length = 50)
    private String receiptStatus;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getReceiptStatus() {
        return receiptStatus;
    }

    public void setReceiptStatus(String receiptStatus) {
        this.receiptStatus = receiptStatus;
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
        GoodsreceiptEntity that = (GoodsreceiptEntity) o;
        return totalQuantity == that.totalQuantity && totalAmount == that.totalAmount && deleteFlag == that.deleteFlag && Objects.equals(id, that.id) && Objects.equals(productId, that.productId) && Objects.equals(supplierId, that.supplierId) && Objects.equals(receiptStatus, that.receiptStatus) && Objects.equals(status, that.status) && Objects.equals(note, that.note) && Objects.equals(createDate, that.createDate) && Objects.equals(modifierDate, that.modifierDate) && Objects.equals(createBy, that.createBy) && Objects.equals(modifierBy, that.modifierBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, totalQuantity, totalAmount, supplierId, receiptStatus, status, note, createDate, modifierDate, createBy, modifierBy, deleteFlag);
    }
}
