package com.techcam.dto.response.voucher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
public class VoucherResponse {

    private String voucherId;

    private String voucherCode;

    private String voucherName;

    private LocalDate voucherStartDate;

    private LocalDate voucherEndDate;

    private Long voucherDiscount;

    private Integer voucherMinAmount;

    private Integer voucherQuantity;

    private String voucherDescription;

    private String categoryId;

    private Boolean voucherStatus;

    private Boolean voucherHidden;

    private LocalDateTime voucherCreateDate;

    private String voucherCreateBy;

    private LocalDateTime voucherModifiedDate;

}
