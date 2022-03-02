package com.techcam.entity;

import javax.persistence.*;

@Table(name = "product_detail")
@Entity
public class ProductDetailEntity extends BaseEntity {
    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "PRICE", nullable = false)
    private Long price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private ProductEntity product;

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

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
