package com.techcam.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "goods_orders")
@Getter
@Setter
@ToString
public class GoodsOrderEntity extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @Column(name = "order_id", length = 64)
    private String orderId;

    @Column(name = "supplier_id")
    private String supplierId;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "order_staff", length = 64)
    private String orderStaff;

    @Column(name = "order_delivery")
    private LocalDate orderDelivery;

    @Column(name = "STATUS", length = 50)
    private String status;

    @Lob
    @Column(name = "NOTE")
    private String note;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GoodsOrderEntity that = (GoodsOrderEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}