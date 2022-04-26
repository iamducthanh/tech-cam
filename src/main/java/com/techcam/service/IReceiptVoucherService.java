package com.techcam.service;

import com.techcam.dto.request.receiptvoucher.ReceiptVoucherRequest;
import com.techcam.dto.response.receiptvoucher.GetInfoReceiptVoucher;
import com.techcam.dto.response.receiptvoucher.ReceiptVoucherResponse;

import java.util.List;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/24/2022
 * Project_name: Tech-cam
 */

public interface IReceiptVoucherService {
    ReceiptVoucherResponse resgistration(ReceiptVoucherRequest request);

    GetInfoReceiptVoucher getInfoReceiptVoucher(Integer id);

    ReceiptVoucherResponse deleteReceiptVoucher(Integer id, String note);

    //    public ReceiptVoucherResponse editReceiptById(){
    //
    //    }
    String getInfoStaff();

    List<GetInfoReceiptVoucher> getAllReceiptVoucher();
}
