package com.techcam.controller;

import com.techcam.dto.response.SupplierResponseDTO;
import com.techcam.dto.response.invoiceOrder.InvoiceOrderDetailResponse;
import com.techcam.dto.response.invoiceOrder.InvoiceOrderResponse;
import com.techcam.dto.response.product.ProductResponse;
import com.techcam.service.IGoodsOrderService;
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
import java.util.stream.Collectors;

import static com.techcam.type.CustomerStatus.ON;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 4/3/2022 9:02 PM
 * Project_name : tech-cam
 */

@Controller
@RequestMapping("order-invoice")
@RequiredArgsConstructor
public class OrderInvoiceController {

    private final IGoodsOrderService goodsOrderService;

    private final ISupplierService supplierService;

    private final IProductService productService;

    @GetMapping
    public String home(Model model) {
        List<InvoiceOrderResponse> lst = goodsOrderService.getAllInvoiceOrder();
        List<SupplierResponseDTO> lstSupplier = supplierService.getAll();
        List<ProductResponse> lstProduct = productService.getAllProduct();
        lstProduct = lstProduct.stream().filter(e -> e.getProductStatus().equals(ON.name())).collect(Collectors.toList());
//        lstSupplier = lstSupplier.stream().filter(e -> Objects.nonNull(e.getStatus()) && e.getStatus().equals(ON.name())).collect(Collectors.toList());
//        lst.sort((o1, o2) -> o2.getCreateDate().compareTo(o1.getCreateDate()));
//        lstProduct.sort((o1, o2) -> o2.getCreateDate().compareTo(o1.getCreateDate()));
        model.addAttribute("lstOrderInvoice", lst);
        model.addAttribute("lstSupplier", lstSupplier);
        model.addAttribute("lstProduct", lstProduct);
        return "views/order-invoice/014_order_invoice";
    }

    @GetMapping(value = "/component/add-order-invoice", params = "id")
    public String modalAddOrderInvoice(Model model, @RequestParam(value = "id", defaultValue = "1") String id) {
        InvoiceOrderResponse invoiceOrderResponse = goodsOrderService.getByOrderId(id);
        if (Objects.isNull(invoiceOrderResponse)) {
            invoiceOrderResponse = new InvoiceOrderResponse();
        }
        List<SupplierResponseDTO> lstSupplier = supplierService.getAll();
        List<InvoiceOrderDetailResponse> lstDetail = goodsOrderService.findAllOrderDetailByOrderId(id);
        if (lstDetail.isEmpty()) {
            lstDetail.add(new InvoiceOrderDetailResponse());
        }
        List<ProductResponse> lstProduct = productService.getAllProduct();
        model.addAttribute("orderInvoice", invoiceOrderResponse);
        model.addAttribute("lstSupplier", lstSupplier);
        model.addAttribute("lstOrderDetail", lstDetail);
        model.addAttribute("lstProduct", lstProduct);
        return "views/order-invoice/component/add-order-invoice";
    }

    @GetMapping(value = "/component/add-product-order-invoice", params = {"number"})
    public String modalAddProductOrderInvoice(Model model, @RequestParam(value = "number", defaultValue = "1") String number) {
        List<SupplierResponseDTO> lstSupplier = supplierService.getAll();
        List<ProductResponse> lstProduct = productService.getAllProduct();
        model.addAttribute("lstSupplier", lstSupplier);
        model.addAttribute("lstProduct", lstProduct);
        model.addAttribute("number", number);
        return "views/order-invoice/component/add-product-order-invoice";
    }

}
