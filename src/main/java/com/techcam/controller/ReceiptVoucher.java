package com.techcam.controller;

import com.techcam.service.IReceiptVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 4/24/2022
 * Project_name: Tech-cam
 */
@Controller
@RequestMapping("/receipt-voucher")
public class ReceiptVoucher {
    @Autowired
    private IReceiptVoucherService voucherService;
    @GetMapping
    public  String getAllVoucher(Model model){
        model.addAttribute("receipt_voucher",voucherService.getAllReceiptVoucher());
        return  "views/receipt_voucher/receipt";

    }
}
