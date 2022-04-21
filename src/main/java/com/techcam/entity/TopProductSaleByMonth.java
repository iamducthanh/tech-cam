package com.techcam.entity;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 4/21/2022 11:24 PM
 */
@ToString
public class TopProductSaleByMonth {
 @Column(name = "PRODUCT_CODE")
 private String productCode;
 @Column(name = "NAME")
 private String name;
 @Column(name = "price")
 private Double price;
 @Column(name = "total_quantity")
 private int totalQuantity;
 @Column(name = "total_amout")
 private Double totalAmount;
}
