package com.techcam.service;

import com.techcam.dto.request.VoucherResDto;
import com.techcam.dto.response.VoucherRespDto;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * Description :
 */
public interface IVoucherService {

    VoucherRespDto checkVoucher(String voucherCode);

    VoucherRespDto createVoucher(VoucherResDto voucherResDto);

}
