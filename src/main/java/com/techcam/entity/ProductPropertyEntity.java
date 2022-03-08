package com.techcam.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "product_property")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductPropertyEntity extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "ATTRIBUTE_ID", nullable = false)
    private AttributeEntity attribute;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PRODUCT_DETAIL_ID", nullable = false)
    private ProductDetailEntity productDetail;

    @Column(name = "STATUS", nullable = false, length = 50)
    private String status;

    @Lob
    @Column(name = "NOTE")
    private String note;

    @Column(name = "PROPERTY_VALUE", nullable = false, length = 50)
    private String propertyValue;

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

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

    public ProductDetailEntity getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetailEntity productDetail) {
        this.productDetail = productDetail;
    }

    public AttributeEntity getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeEntity attribute) {
        this.attribute = attribute;
    }
}
