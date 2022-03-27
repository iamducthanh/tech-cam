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
@Table(name = "goodsreceipt", schema = "poly_techcam", catalog = "")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GoodsreceiptEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID", nullable = false, length = 64)
    private String id;
    @Basic
    @Column(name = "TotalAmount", nullable = false, precision = 0)
    private int totalAmount;
    @Basic
    @Column(name = "Supplier_ID", nullable = false, length = 64)
    private String supplierId;
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
        GoodsreceiptEntity that = (GoodsreceiptEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
