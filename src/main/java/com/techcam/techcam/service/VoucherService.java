package com.techcam.techcam.service;

import com.techcam.techcam.dto.response.VoucherResp;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 4:42 PM
 * Project_name : tech-cam
 */

public interface VoucherService {

    VoucherResp checkVoucher(String voucherCode);



}
