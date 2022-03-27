package com.techcam.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Dev
 * @version 1.0
 * @since 13.3.2022
 */
@Entity
@Table(name = "goodsreceiptdetail", schema = "poly_techcam", catalog = "")
@IdClass(GoodsreceiptdetailEntityPK.class)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GoodsreceiptdetailEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "GoodsReceipt_ID", nullable = false, length = 64)
    private String goodsReceiptId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Product_ID", nullable = false, length = 64)
    private String productId;
    @Basic
    @Column(name = "Quantity", nullable = false)
    private int quantity;
    @Basic
    @Column(name = "Price", nullable = false, precision = 0)
    private int price;
    @Basic
    @Column(name = "STATUS", nullable = false, length = 50)
    private String status;
    @Basic
    @Column(name = "NOTE", nullable = true, length = -1)
    private String note;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        GoodsreceiptdetailEntity that = (GoodsreceiptdetailEntity) o;
        return goodsReceiptId != null && Objects.equals(goodsReceiptId, that.goodsReceiptId)
                && productId != null && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsReceiptId, productId);
    }
}
