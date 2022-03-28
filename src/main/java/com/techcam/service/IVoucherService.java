package com.techcam.service;

import com.techcam.dto.request.voucher.VoucherRequest;
import com.techcam.dto.response.voucher.VoucherResponse;

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

    String createVoucher(VoucherRequest voucherRequest);

    String updateVoucher(VoucherRequest voucherRequest);

    String deleteVoucher(String id);

    List<VoucherResponse> getAllVoucher();

    String activeVoucher(String id);

    List<String> findAllIdCustomerByVoucherId(String voucherId);

    VoucherResponse getById(String voucherId);

    List<VoucherResponse> findAllByCode(String code);
}
