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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

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
        List<ProductResponse> lstProduct = productService.getAllProduct();
        model.addAttribute("lstInvoice", lstInvoice);
        model.addAttribute("lstInvoiceOrder", lstInvoiceOrder);
        model.addAttribute("lstSupplier", lstSupplier);
        model.addAttribute("lstProduct", lstProduct);
        return "views/import-invoice/008_import_invoice";
    }

    @GetMapping(value = "/component/edit-invoice", params = {"id"})
    public String modalEditInvoice(Model model, @RequestParam(value = "id", defaultValue = "") String id) {
        InvoiceResponse invoice = goodsreceiptService.getByInvoiceId(id);
        List<InvoiceOrderResponse> lstInvoiceOrder = goodsOrderService.getAllInvoiceOrder();
        List<SupplierResponseDTO> lstSupplier = supplierService.getAll();
//        List<ProductResponse> lstProduct = productService.getAllProduct();
        model.addAttribute("invoice", Objects.isNull(invoice) ? new InvoiceResponse() : invoice);
        model.addAttribute("lstInvoiceOrder", lstInvoiceOrder);
        model.addAttribute("lstSupplier", lstSupplier);
//        model.addAttribute("lstProduct", lstProduct);
        return "views/import-invoice/component/008_edit_invoice";
    }

    @GetMapping(value = "/component/add-product", params = "number")
    public String modalAddProductInvoice(Model model, @RequestParam(value = "number", defaultValue = "1") String number
    ) {
        List<ProductResponse> lstProduct = productService.getAllProduct();
//        List<InvoiceDetailResponse> lstDetail = new ArrayList<>();
//        if (!invoiceId.isEmpty()) {
//            lstDetail = goodsreceiptService.findAllInvoiceDetailByInvoiceId(invoiceId);
//        } else {
//            lstDetail.add(new InvoiceDetailResponse());
//        }
//        List<String> lstProductId = lstDetail.stream().map(InvoiceDetailResponse::getProductId).collect(Collectors.toList());
        model.addAttribute("lstProduct", lstProduct);
        model.addAttribute("number", number);
//        model.addAttribute("lstDetail", lstDetail);
//        model.addAttribute("lstProductId", lstProductId);
        return "views/import-invoice/component/008_add_product";
    }

    @GetMapping(value = "/component/edit-product",
            params = {
                    "number",
                    "productId",
                    "quantity",
                    "quantityActual",
                    "price"
            })
    public String modalGetProductInvoice(Model model,
                                         @RequestParam(value = "number", defaultValue = "1") String number,
                                         @RequestParam(value = "productId", defaultValue = "1") String productId,
                                         @RequestParam(value = "quantity", defaultValue = "0") String quantity,
                                         @RequestParam(value = "quantityActual", defaultValue = "0") String quantityActual,
                                         @RequestParam(value = "price", defaultValue = "0") String price
    ) {
        List<ProductResponse> lstProduct = productService.getAllProduct();
        model.addAttribute("lstProduct", lstProduct);
        model.addAttribute("productId", productId);
        model.addAttribute("quantity", quantity);
        model.addAttribute("quantityActual", quantityActual);
        model.addAttribute("price", price);
        model.addAttribute("number", number);
        return "views/import-invoice/component/008_edit_product";
    }

}
