package com.techcam.dto.request.voucher;

import com.techcam.constants.ConstantsErrorCode;
import com.techcam.constants.ContainsFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
public class VoucherRequest {

    private String voucherId;

    @NotNull(message = ConstantsErrorCode.ERROR_DATA_REQUEST)
    @NotBlank(message = ConstantsErrorCode.VOUCHER_CODE_BLANK)
    private String voucherCode;

    @NotNull(message = ConstantsErrorCode.ERROR_DATA_REQUEST)
    @NotBlank(message = ConstantsErrorCode.VOUCHER_NAME_BLANK)
    private String voucherName;

    @NotNull(message = ConstantsErrorCode.ERROR_DATA_REQUEST)
    @NotBlank(message = ConstantsErrorCode.VOUCHER_START_DATE_BLANK)
    @Pattern(regexp = ContainsFormat.REGEX_DATE, message = ConstantsErrorCode.DATE_NOT_FORMAT)
    private String voucherStartDate;

    @NotNull(message = ConstantsErrorCode.ERROR_DATA_REQUEST)
    @NotBlank(message = ConstantsErrorCode.VOUCHER_END_DATE_BLANK)
    @Pattern(regexp = ContainsFormat.REGEX_DATE, message = ConstantsErrorCode.DATE_NOT_FORMAT)
    private String voucherEndDate;

    @NotNull(message = ConstantsErrorCode.ERROR_DATA_REQUEST)
    @NotBlank(message = ConstantsErrorCode.VOUCHER_DISCOUNT_BLANK)
    @Pattern(regexp = ContainsFormat.REGEX_NUMBER)
    private String voucherDiscount;

    @Pattern(regexp = ContainsFormat.REGEX_NUMBER)
    private String voucherMoneyMin;

    @Pattern(regexp = ContainsFormat.REGEX_NUMBER)
    private String voucherQuantity;

    private String voucherDescription;

    private String voucherCategory;

    private String voucherStatus;

    private String note;

    private String voucherPersonApply;

    private String voucherTypeDiscount;

    private List<String> typeDiscountPerson;

    private String typeDiscountMoneyMin;

}
