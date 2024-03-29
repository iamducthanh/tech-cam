package com.techcam.api;

import com.techcam.dto.request.receiptvoucher.ReceiptVoucherRequest;
import com.techcam.dto.response.receiptvoucher.GetInfoReceiptVoucher;
import com.techcam.dto.response.receiptvoucher.ReceiptVoucherResponse;
import com.techcam.service.IReceiptVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/30/2022
 * Project_name: Tech-cam
 */
@RestController
@RequestMapping("api/receipt-voucher")
public class ReceiptVoucherApi {
    @Autowired
    private IReceiptVoucherService iReceiptVoucherService;
    @PostMapping("registration")
    public ReceiptVoucherResponse registration(@Valid @RequestBody ReceiptVoucherRequest request){
        return iReceiptVoucherService.resgistration(request);
    }
    @GetMapping("find/{id}")
    public GetInfoReceiptVoucher getInfoReceiptVoucher(@PathVariable Integer id){
        return iReceiptVoucherService.getInfoReceiptVoucher(id);
    }
}
