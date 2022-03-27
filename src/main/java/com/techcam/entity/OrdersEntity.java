package com.techcam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/21/2022
 * Project_name: Tech-cam
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders", schema = "poly_techcam", catalog = "")
public class OrdersEntity {
    private String id;
    private Date orderDate;
    private Integer tax;
    private String transactionStatus;
    private Date paymentDate;
    private Integer itemQuantity;
    private Integer totalAmount;
    private String orderType;
    private String stockKeeper;
    private String recipientName;
    private String recipientPhone;
    private String paymentMethod;
    private String recipientAddress;
    private String shipmentStatus;
    private String salesPerson;
    private String accounting;
    private String shipmentId;
    private String status;
    private String note;
    private Date createDate;
    private Date modifierDate;
    private String createBy;
    private String modifierBy;
    private Boolean deleteFlag;
    private String ipAddress;
    private Date deliveryDate;
    private CustomerEntity customer;
    private VoucherEntity voucher;
    private String bankTransaction;
    private List<OrderdetailEntity> ordersEntities;
//    private List<ReceiptVoucherEntity> orReceiptVoucherEntities;
//    @OneToMany(mappedBy = "orders")
//    public List<ReceiptVoucherEntity> getOrReceiptVoucherEntities() {
//        return orReceiptVoucherEntities;
//    }
//
//    public void setOrReceiptVoucherEntities(List<ReceiptVoucherEntity> orReceiptVoucherEntities) {
//        this.orReceiptVoucherEntities = orReceiptVoucherEntities;
//    }
    @Basic
    @Column(name = "bank_transaction")
    public String getBankTransaction() {
        return bankTransaction;
    }

    public void setBankTransaction(String bankTransaction) {
        this.bankTransaction = bankTransaction;
    }

    @OneToMany(mappedBy = "orders", fetch = FetchType.LAZY)
    public List<OrderdetailEntity> getOrdersEntities() {
        return ordersEntities;
    }

    public void setOrdersEntities(List<OrderdetailEntity> ordersEntities) {
        this.ordersEntities = ordersEntities;
    }
    @ManyToOne
    @JoinColumn(name = "voucher_customer_id")
    public VoucherEntity getVoucher() {
        return voucher;
    }

    public void setVoucher(VoucherEntity voucher) {
        this.voucher = voucher;
    }
    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "order_date")
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    @Basic
    @Column(name = "delivery_date")
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Basic
    @Column(name = "tax")
    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    @Basic
    @Column(name = "transaction_status")
    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @Basic
    @Column(name = "payment_date")
    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Basic
    @Column(name = "item_quantity")
    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Basic
    @Column(name = "total_amount")
    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Basic
    @Column(name = "order_type")
    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @Basic
    @Column(name = "stock_keeper")
    public String getStockKeeper() {
        return stockKeeper;
    }

    public void setStockKeeper(String stockKeeper) {
        this.stockKeeper = stockKeeper;
    }

    @Basic
    @Column(name = "recipient_name")
    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
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
    @Column(name = "payment_method")
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Basic
    @Column(name = "recipient_address")
    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    @Basic
    @Column(name = "shipment_status")
    public String getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    @Basic
    @Column(name = "sales_person")
    public String getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(String salesPerson) {
        this.salesPerson = salesPerson;
    }

    @Basic
    @Column(name = "accounting")
    public String getAccounting() {
        return accounting;
    }

    public void setAccounting(String accounting) {
        this.accounting = accounting;
    }

    @Basic
    @Column(name = "shipment_id")
    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
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
    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Basic
    @Column(name = "IP_ADDRESS")
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    @ManyToOne
    @JoinColumn(name = "customer_id")
    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity orders = (OrdersEntity) o;
        return Objects.equals(id, orders.id) && Objects.equals(recipientName, orders.recipientName) && Objects.equals(recipientPhone, orders.recipientPhone) && Objects.equals(recipientAddress, orders.recipientAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, recipientName, recipientPhone, recipientAddress);
    }
}
