package com.techcam.controller;

import com.techcam.dto.response.SupplierResponseDTO;
import com.techcam.dto.response.invoice.InvoiceResponse;
import com.techcam.dto.response.invoiceOrder.InvoiceOrderResponse;
import com.techcam.dto.response.product.ProductResponse;
import com.techcam.service.IGoodsOrderService;
import com.techcam.service.IGoodsreceiptService;
import com.techcam.service.IProductService;
import com.techcam.service.ISupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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

    private final ISupplierService supplierService;

    private final IProductService productService;

    private final IGoodsOrderService goodsOrderService;

    @GetMapping
    public String homeInvoice(Model model) {
        List<InvoiceResponse> lstInvoice = goodsreceiptService.findAllInvoice();
        for (InvoiceResponse x : lstInvoice) {
            x.setDetails(goodsreceiptService.findAllInvoiceDetailByInvoiceId(x.getInvoiceId()));
        }
        List<InvoiceOrderResponse> lstInvoiceOrder = goodsOrderService.getAllInvoiceOrder();
        List<SupplierResponseDTO> lstSupplier = supplierService.getAll();
//        List<SupplierResponseDTO> lstSupplier = new ArrayList<>();
        List<ProductResponse> lstProduct = productService.getAllProduct();
        model.addAttribute("lstInvoice", lstInvoice);
        model.addAttribute("lstInvoiceOrder", lstInvoiceOrder);
        model.addAttribute("lstSupplier", lstSupplier);
        model.addAttribute("lstProduct", lstProduct);
        return "views/import-invoice/008_import_invoice";
    }

}
