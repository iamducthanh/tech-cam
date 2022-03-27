package com.techcam.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author DucBV
 * @version 1.0
 * @since 8.3.2022
 */
@Entity
@Table(name = "voucher_customer", schema = "poly_techcam", catalog = "")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoucherCustomerEntity extends BaseEntity {
    @Id
    @Column(name = "ID")
    private String id;
    @Basic
    @Column(name = "VOUCHER_ID")
    private String voucherId;
    @Basic
    @Column(name = "CUSTOMER_ID")
    private String customerId;
    // không biết ông nào yêu cầu thêm 3 trường dưới đây vào db luôn??
    @Basic
    @Column(name = "DISCOUNT")
    private long discount;
    @Basic
    @Column(name = "START_DT")
    private Date startDatte;
    @Basic
    @Column(name = "END_DT")
    private Date endDate;
    @Basic
    @Column(name = "STATUS")
    private String status;
    @Basic
    @Column(name = "NOTE")
    private String note;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoucherCustomerEntity that = (VoucherCustomerEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(customerId, that.customerId) && Objects.equals(voucherId, that.voucherId) && Objects.equals(status, that.status) && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, voucherId, status, note);
    }
}
