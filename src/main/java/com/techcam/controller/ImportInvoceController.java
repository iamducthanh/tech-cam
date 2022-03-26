package com.techcam.controller;

import com.techcam.dto.response.invoice.InvoiceResponse;
import com.techcam.service.IGoodsreceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/26/2022 4:17 PM
 * Project_name : tech-cam
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/invoice")
public class ImportInvoceController {

    private final IGoodsreceiptService goodsreceiptService;

    @GetMapping
    public String homeInvoice(Model model) {
        List<InvoiceResponse> lstInvoice = goodsreceiptService.findAllInvoice();
        for (InvoiceResponse x:lstInvoice             ) {
            x.setDetails(goodsreceiptService.findAllInvoiceDetailByInvoiceId(x.getInvoiceId()));
        }
        model.addAttribute("lstInvoice", lstInvoice);
        return "views/import-invoice/008_import_invoice";
    }

}
