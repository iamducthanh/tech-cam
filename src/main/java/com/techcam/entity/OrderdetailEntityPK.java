package com.techcam.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Dev
 * @version 1.0
 * @since 13.3.2022
 */
public class OrderdetailEntityPK implements Serializable {
    @Column(name = "Order_ID", nullable = false, length = 64)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String orderId;
    @Column(name = "Product_ID", nullable = false, length = 64)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String productId;
    @Column(name = "Imei", nullable = false, length = 64)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String imei;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderdetailEntityPK that = (OrderdetailEntityPK) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(productId, that.productId) && Objects.equals(imei, that.imei);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, productId, imei);
    }
}
