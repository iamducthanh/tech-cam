package com.techcam.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "voucher_customer")
@Entity
public class VoucherCustomerEntity extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private CustomerEntity customer;

    @ManyToOne(optional = false)
    @JoinColumn(name = "VOUCHER_ID", nullable = false)
    private VoucherEntity voucher;

    @Column(name = "DISCOUNT", nullable = false)
    private Long discount;

    @Column(name = "START_DT", nullable = false)
    private LocalDate startDt;

    @Column(name = "END_DT", nullable = false)
    private LocalDate endDt;

    @Column(name = "STATUS", nullable = false, length = 50)
    private String status;

    @Lob
    @Column(name = "NOTE")
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getEndDt() {
        return endDt;
    }

    public void setEndDt(LocalDate endDt) {
        this.endDt = endDt;
    }

    public LocalDate getStartDt() {
        return startDt;
    }

    public void setStartDt(LocalDate startDt) {
        this.startDt = startDt;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public VoucherEntity getVoucher() {
        return voucher;
    }

    public void setVoucher(VoucherEntity voucher) {
        this.voucher = voucher;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
