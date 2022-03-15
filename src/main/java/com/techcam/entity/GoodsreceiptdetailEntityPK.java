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
public class GoodsreceiptdetailEntityPK implements Serializable {
    @Column(name = "GoodsReceipt_ID", nullable = false, length = 64)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String goodsReceiptId;
    @Column(name = "Product_ID", nullable = false, length = 64)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String productId;

    public String getGoodsReceiptId() {
        return goodsReceiptId;
    }

    public void setGoodsReceiptId(String goodsReceiptId) {
        this.goodsReceiptId = goodsReceiptId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsreceiptdetailEntityPK that = (GoodsreceiptdetailEntityPK) o;
        return Objects.equals(goodsReceiptId, that.goodsReceiptId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(goodsReceiptId, productId);
    }
}
