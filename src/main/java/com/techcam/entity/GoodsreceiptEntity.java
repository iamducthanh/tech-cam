package com.techcam.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "goodsreceipt")
@Getter
@Setter
@ToString
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class GoodsreceiptEntity extends BaseEntity {
    @Id
    @Column(name = "ID", nullable = false, length = 64)
    private String id;

    @Column(name = "Supplier_ID", nullable = false)
    private String supplierId;

    @Column(name = "STATUS", nullable = false, length = 50)
    private String status;

    @Lob
    @Column(name = "NOTE")
    private String note;

    @Column(name = "discount", precision = 10)
    private BigDecimal discount;

    @Column(name = "total_amount", nullable = false)
    private Long totalAmount;

    @Column(name = "paid", nullable = false)
    private Long paid;

    @Column(name = "receipt_status", length = 50)
    private String receiptStatus;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "receipt_id", length = 64)
    private String receiptId;

    @Column(name = "deliverier", nullable = false)
    private String deliverier;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GoodsreceiptEntity that = (GoodsreceiptEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}