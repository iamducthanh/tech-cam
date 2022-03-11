package com.techcam.dto.response.voucher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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

    private Date voucherStartDate;

    private Date voucherEndDate;

    private Long voucherDiscount;

    private String voucherTypeDiscount;

    private Integer voucherMinAmount;

    private Integer voucherQuantity;

    private String voucherDescription;

    private String categoryId;

    private String voucherStatus;

    private Boolean voucherHidden;

    private Timestamp voucherCreateDate;

    private String voucherCreateBy;

    private Timestamp voucherModifiedDate;

    private Long typeDiscountMinAmount;

    private List<String> lstCustomer;

}
