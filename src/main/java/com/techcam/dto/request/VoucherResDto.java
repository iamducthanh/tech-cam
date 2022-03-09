package com.techcam.dto.request;

import com.techcam.repo.util.ConstantsErrorCode;
import com.techcam.repo.util.ContainsFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

    @NotNull(message = ConstantsErrorCode.ERROR_DATA_REQUEST)
    @NotBlank(message = ConstantsErrorCode.VOUCHER_CODE_BLANK)
    private String voucherCode;

    @NotNull(message = ConstantsErrorCode.ERROR_DATA_REQUEST)
    @NotBlank(message = ConstantsErrorCode.VOUCHER_NAME_BLANK)
    private String voucherName;

    @NotNull(message = ConstantsErrorCode.ERROR_DATA_REQUEST)
    @NotBlank(message = ConstantsErrorCode.VOUCHER_START_DATE_BLANK)
    @Pattern(regexp = ContainsFormat.REGEX_DATE, message = ConstantsErrorCode.DATE_NOT_FORMAT)
    private String startDate;

    @NotNull(message = ConstantsErrorCode.ERROR_DATA_REQUEST)
    @NotBlank(message = ConstantsErrorCode.VOUCHER_END_DATE_BLANK)
    @Pattern(regexp = ContainsFormat.REGEX_DATE, message = ConstantsErrorCode.DATE_NOT_FORMAT)
    private String endDate;

    @NotNull(message = ConstantsErrorCode.ERROR_DATA_REQUEST)
    @NotBlank(message = ConstantsErrorCode.VOUCHER_DISCOUNT_BLANK)
    @Pattern(regexp = ContainsFormat.REGEX_NUMBER)
    private String discount;

    @Pattern(regexp = ContainsFormat.REGEX_NUMBER)
    private String minAmount;

    @Pattern(regexp = ContainsFormat.REGEX_NUMBER)
    private String quantity;

    private String description;

    private String categoryId;

    private String status;

}
