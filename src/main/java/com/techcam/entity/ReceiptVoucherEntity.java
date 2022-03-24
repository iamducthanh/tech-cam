package com.techcam.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/23/2022
 * Project_name: Tech-cam
 */

@Entity
@Table(name = "receipt_voucher", schema = "poly_techcam", catalog = "")
public class ReceiptVoucherEntity {
    private String id;
    private String payer;
    private String payerPhone;
    private String receiptName;
    private Integer receiptValue;
    private String description;
    private String status;
    private String note;
    private Date createDate;
    private Date modifierDate;
    private String createBy;
    private String modifierBy;
    private boolean deleteFlag;
    private Integer givenMoney;
    private Integer returnMoney;
//    private OrdersEntity orders;

    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    @ManyToOne @JoinColumn(name = "order_id")
//    public OrdersEntity getOrders() {
//        return orders;
//    }
//
//    public void setOrders(OrdersEntity orders) {
//        this.orders = orders;
//    }

    @Basic
    @Column(name = "payer")
    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    @Basic
    @Column(name = "payer_phone")
    public String getPayerPhone() {
        return payerPhone;
    }

    public void setPayerPhone(String payerPhone) {
        this.payerPhone = payerPhone;
    }

    @Basic
    @Column(name = "receipt_name")
    public String getReceiptName() {
        return receiptName;
    }

    public void setReceiptName(String receiptName) {
        this.receiptName = receiptName;
    }

    @Basic
    @Column(name = "receipt_value")
    public Integer getReceiptValue() {
        return receiptValue;
    }

    public void setReceiptValue(Integer receiptValue) {
        this.receiptValue = receiptValue;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
    @Column(name = "CREATE_DATE")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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
    @Column(name = "DELETE_FLAG")
    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Basic
    @Column(name = "given_money")
    public Integer getGivenMoney() {
        return givenMoney;
    }

    public void setGivenMoney(Integer givenMoney) {
        this.givenMoney = givenMoney;
    }

    @Basic
    @Column(name = "return_money")
    public Integer getReturnMoney() {
        return returnMoney;
    }

    public void setReturnMoney(Integer returnMoney) {
        this.returnMoney = returnMoney;
    }

}
