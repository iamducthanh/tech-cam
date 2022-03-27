package com.techcam.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "goodsreceiptdetail")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GoodsreceiptdetailEntity extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false, length = 64)
    private String id;

    @Column(name = "Goods_Receipt_ID", nullable = false)
    private String goodsReceiptId;

    @Column(name = "Product_ID", nullable = false)
    private String productId;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "Price", nullable = false, precision = 10)
    private BigDecimal price;

    @Column(name = "STATUS", nullable = false, length = 50)
    private String status;

    @Lob
    @Column(name = "NOTE")
    private String note;

    @Column(name = "discount", precision = 10)
    private BigDecimal discount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GoodsreceiptdetailEntity that = (GoodsreceiptdetailEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}