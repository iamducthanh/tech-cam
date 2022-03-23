package com.techcam.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/20/2022
 * Project_name: Tech-cam
 */

@Entity
@Table(name = "orderdetail", schema = "poly_techcam", catalog = "")
public class OrderdetailEntity {
    private String id;
    private String imei;
    private String status;
    private Date createDate;
    private String note;
    private String createBy;
    private String modifierBy;
    private Date modifierDate;
    private Boolean deleteFlag;
    private int discount;
    private int quantity;
    private OrdersEntity orders;
    private ProductEntity product;


    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }
    @Basic
    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "IMEI")
    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Basic
    @Column(name = "Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "NOTE")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Basic
    @Column(name = "CREATE_BY")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "MODIFIER_BY")
    public String getModifierBy() {
        return modifierBy;
    }

    public void setModifierBy(String modifierBy) {
        this.modifierBy = modifierBy;
    }

    @Basic
    @Column(name = "MODIFIER_DATE")
    public Date getModifierDate() {
        return modifierDate;
    }

    public void setModifierDate(Date modifierDate) {
        this.modifierDate = modifierDate;
    }

    @Basic
    @Column(name = "DELETE_FLAG")
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
    @Basic
    @Column(name = "discount")
    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @ManyToOne
    @JoinColumn(name = "order_id")
    public OrdersEntity getOrders() {
        return orders;
    }

    public void setOrders(OrdersEntity orders) {
        this.orders = orders;
    }
    @ManyToOne
    @JoinColumn(name = "product_id")
    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

}
