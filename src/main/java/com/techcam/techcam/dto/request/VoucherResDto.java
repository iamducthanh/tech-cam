package com.techcam.techcam.dto.request;

import lombok.Data;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 8:26 PM
 * Project_name : tech-cam
 */

@Data
public class VoucherResDto {

    private String id;

    private String name;

    private String voucherCode;

    private Integer quantity;

    private String startDate;

    private String endDate;

    private String note;

    private Long discount;

}
