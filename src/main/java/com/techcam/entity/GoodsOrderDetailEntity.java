package com.techcam.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "goods_order_details")
@Getter
@Setter
@ToString
public class GoodsOrderDetailEntity extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @Column(name = "goods_orders_id")
    private String goodsOrdersId;

    @Column(name = "product_id", length = 64)
    private String productId;

    @Column(name = "item_quantity", precision = 10)
    private int itemQuantity;

    @Column(name = "STATUS", length = 50)
    private String status;

    @Lob
    @Column(name = "NOTE")
    private String note;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GoodsOrderDetailEntity that = (GoodsOrderDetailEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}