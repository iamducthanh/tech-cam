package com.techcam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 8:55 PM
 * Project_name : tech-cam
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
