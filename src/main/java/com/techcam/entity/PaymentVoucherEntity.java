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
@Table(name = "payment_voucher", schema = "poly_techcam", catalog = "")
public class PaymentVoucherEntity {
    private String id;
    private String recipient;
    private String recipientPhone;
    private String paymentName;
    private Integer paymentValue;
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

    @Id
    @Column(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "recipient")
    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Basic
    @Column(name = "recipient_phone")
    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    @Basic
    @Column(name = "payment_name")
    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    @Basic
    @Column(name = "payment_value")
    public Integer getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(Integer paymentValue) {
        this.paymentValue = paymentValue;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PaymentVoucherEntity that = (PaymentVoucherEntity) o;

        if (deleteFlag != that.deleteFlag) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (recipient != null ? !recipient.equals(that.recipient) : that.recipient != null) return false;
        if (recipientPhone != null ? !recipientPhone.equals(that.recipientPhone) : that.recipientPhone != null)
            return false;
        if (paymentName != null ? !paymentName.equals(that.paymentName) : that.paymentName != null) return false;
        if (paymentValue != null ? !paymentValue.equals(that.paymentValue) : that.paymentValue != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (note != null ? !note.equals(that.note) : that.note != null) return false;
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) return false;
        if (modifierDate != null ? !modifierDate.equals(that.modifierDate) : that.modifierDate != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (modifierBy != null ? !modifierBy.equals(that.modifierBy) : that.modifierBy != null) return false;
        if (givenMoney != null ? !givenMoney.equals(that.givenMoney) : that.givenMoney != null) return false;
        if (returnMoney != null ? !returnMoney.equals(that.returnMoney) : that.returnMoney != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (recipient != null ? recipient.hashCode() : 0);
        result = 31 * result + (recipientPhone != null ? recipientPhone.hashCode() : 0);
        result = 31 * result + (paymentName != null ? paymentName.hashCode() : 0);
        result = 31 * result + (paymentValue != null ? paymentValue.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (modifierDate != null ? modifierDate.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (modifierBy != null ? modifierBy.hashCode() : 0);
        result = 31 * result + (deleteFlag ? 1 : 0);
        result = 31 * result + (givenMoney != null ? givenMoney.hashCode() : 0);
        result = 31 * result + (returnMoney != null ? returnMoney.hashCode() : 0);
        return result;
    }
}
