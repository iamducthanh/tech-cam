package com.techcam.dto.Report;

import lombok.Data;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 2022/04/24 17:15
 * Project_name : tech-cam
 */

@Data
public class DailySaleDto {

    public DailySaleDto(String productCode, String productName, Long price, Integer sumQuantity, Long promotion, Long totalAmount) {
        this.productCode = productCode;
        this.productName = productName;
        this.price = price;
        this.sumQuantity = sumQuantity;
        this.promotion = promotion;
        this.totalAmount = totalAmount;
    }

    private String productCode;

    private String productName;

    private Long price;

    private Integer sumQuantity;

    private Long promotion;

    private Long totalAmount;

}
