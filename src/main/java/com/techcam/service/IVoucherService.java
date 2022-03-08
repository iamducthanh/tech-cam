package com.techcam.service;

import com.techcam.dto.request.VoucherResDto;
import com.techcam.dto.response.VoucherRespDto;

import java.util.List;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * Description :
 */
public interface IVoucherService {

    String createVoucher(VoucherResDto voucherResDto);

    String updateVoucher(VoucherResDto voucherResDto);

    String deleteVoucher(String id);

    List<VoucherRespDto> getAllVoucher();
}
