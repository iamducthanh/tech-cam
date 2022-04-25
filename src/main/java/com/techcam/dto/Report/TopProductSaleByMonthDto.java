package com.techcam.dto.Report;

import lombok.Data;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 2022/04/24 17:22
 * Project_name : tech-cam
 */

@Data
public class TopProductSaleByMonthDto {

    public TopProductSaleByMonthDto(String productCode, String productName, String image, Long price, Integer sumQuantity, Long discout, Long totalAmount) {
        this.productCode = productCode;
        this.productName = productName;
        this.image = image;
        this.price = price;
        this.sumQuantity = sumQuantity;
        this.discout = discout;
        this.totalAmount = totalAmount;
    }

    private String productCode;

    private String productName;

    private String image;

    private Long price;

    private Integer sumQuantity;

    private Long discout;

    private Long totalAmount;

}
