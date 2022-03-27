package com.techcam.dto.response.voucher;

import lombok.*;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/26/2022 8:48 PM
 * Project_name : tech-cam
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VoucherUseByOrderResponse {

    private String orderId;

    private String orderCode;

    private String voucherId;

    private String voucherCode;

    private String totalMoney;

    private String customerId;

    private String fullName;

    private String dateUsed;

}
