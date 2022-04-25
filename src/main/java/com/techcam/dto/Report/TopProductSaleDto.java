package com.techcam.dto.Report;

import lombok.Data;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 2022/04/24 17:19
 * Project_name : tech-cam
 */

@Data
public class TopProductSaleDto {

    public TopProductSaleDto(String productCode, String productName, Integer sumQuantity, Long totalAmount) {
        this.productCode = productCode;
        this.productName = productName;
        this.sumQuantity = sumQuantity;
        this.totalAmount = totalAmount;
    }

    private String productCode;

    private String productName;

    private Integer sumQuantity;

    private Long totalAmount;

}
