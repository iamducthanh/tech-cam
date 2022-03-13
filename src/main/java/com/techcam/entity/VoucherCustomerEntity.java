package com.techcam.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
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
    @Column(name = "CUSTOMER_ID")
    private String customerId;
    @Basic
    @Column(name = "VOUCHER_ID")
    private String voucherId;
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
