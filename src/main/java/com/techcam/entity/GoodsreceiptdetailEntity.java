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
@Table(name = "goodsreceiptdetail", schema = "poly_techcam", catalog = "")
@IdClass(GoodsreceiptdetailEntityPK.class)
public class GoodsreceiptdetailEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "GoodsReceipt_ID", nullable = false, length = 64)
    private String goodsReceiptId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Product_ID", nullable = false, length = 64)
    private String productId;
    @Basic
    @Column(name = "Quantity", nullable = false)
    private int quantity;
    @Basic
    @Column(name = "Price", nullable = false, precision = 0)
    private int price;
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

    public String getGoodsReceiptId() {
        return goodsReceiptId;
    }

    public void setGoodsReceiptId(String goodsReceiptId) {
        this.goodsReceiptId = goodsReceiptId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
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
        GoodsreceiptdetailEntity that = (GoodsreceiptdetailEntity) o;
        return quantity == that.quantity && price == that.price && deleteFlag == that.deleteFlag && Objects.equals(goodsReceiptId, that.goodsReceiptId) && Objects.equals(productId, that.productId) && Objects.equals(status, that.status) && Objects.equals(note, that.note) && Objects.equals(createDate, that.createDate) && Objects.equals(modifierDate, that.modifierDate) && Objects.equals(createBy, that.createBy) && Objects.equals(modifierBy, that.modifierBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsReceiptId, productId, quantity, price, status, note, createDate, modifierDate, createBy, modifierBy, deleteFlag);
    }
}
