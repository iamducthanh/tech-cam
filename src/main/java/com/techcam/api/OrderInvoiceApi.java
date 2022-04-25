package com.techcam.api;

import com.techcam.dto.request.invoiceOrder.InvoiceOrderDetailRequest;
import com.techcam.dto.request.invoiceOrder.InvoiceOrderRequest;
import com.techcam.dto.response.invoiceOrder.InvoiceOrderResponse;
import com.techcam.exception.TechCamExp;
import com.techcam.service.IGoodsOrderService;
import com.techcam.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Objects;

import static com.techcam.constants.ConstantsErrorCode.*;
import static com.techcam.type.CustomerStatus.SUCCESS;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 4/3/2022 8:57 PM
 * Project_name : tech-cam
 */

@RestController
@RequestMapping("/api/order-invoice")
@RequiredArgsConstructor
public class OrderInvoiceApi {

    private final IGoodsOrderService goodsOrderService;

    @PostMapping
    public ResponseEntity<?> createOrderInvoice(@Validated @RequestBody InvoiceOrderRequest request, Errors errors) {
//        if (!goodsOrderService.findAllOrderDetailByOrderId(request.getId()).isEmpty()) {
//            throw new TechCamExp(ERROR_EXISTS, "Mã đặt hàng");
//        }
        if (ConvertUtil.get().strToDate(request.getDate(), "dd-MM-yyyy").compareTo(new Date()) < 0) {
            throw new TechCamExp(VOUCHER_DATE_NOT_PAST);
        }
        if (request.getDetails().isEmpty()) {
            throw new TechCamExp(INVOICE_NOT_DETAIL);
        }
        for (InvoiceOrderDetailRequest x : request.getDetails()) {
            if (x.getQuantity() < 1) {
                throw new TechCamExp(INVOICE_NOT_ZERO, "Số lượng đặt");
            }
        }
        try {
            if (goodsOrderService.createOrderInvoice(request).equals(SUCCESS.name())) {
                return ResponseEntity.ok(SUCCESS.name());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new TechCamExp(ERROR_SAVE_FAILED);
    }

    @PutMapping
    public ResponseEntity<?> updateOrderInvoice(@Validated @RequestBody InvoiceOrderRequest request, Errors errors) {
        InvoiceOrderResponse invoiceOrderResponse = goodsOrderService.getByOrderId(request.getId());
        if (Objects.isNull(invoiceOrderResponse)) {
            throw new TechCamExp(ERROR_NOT_EXISTS, "Hoá đơn đặt hàng");
        }
//        if (!invoiceOrderResponse.getInvoiceOrderCode().equals(request.getCode())) {
//            if (!goodsOrderService.findAllOrderDetailByOrderId(request.getId()).isEmpty()) {
//                throw new TechCamExp(ERROR_EXISTS, "Mã đặt hàng");
//            }
//        }
//        if (ConvertUtil.get().strToDate(request.getDate(), "dd-MM-yyyy").compareTo(new Date()) < 0) {
//            throw new TechCamExp(VOUCHER_DATE_NOT_PAST);
//        }
        if (request.getDetails().isEmpty()) {
            throw new TechCamExp(INVOICE_NOT_DETAIL);
        }
        for (InvoiceOrderDetailRequest x : request.getDetails()) {
            if (x.getQuantity() < 1) {
                throw new TechCamExp(INVOICE_NOT_ZERO, "Số lượng đặt");
            }
        }
        try {
            if (goodsOrderService.updateOrderInvoice(request).equals(SUCCESS.name())) {
                return ResponseEntity.ok(SUCCESS.name());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new TechCamExp(ERROR_SAVE_FAILED);
    }

    @PutMapping(path = "/{id}", params = "cancel")
    public ResponseEntity<?> cancelOrderInvoice(@PathVariable("id") String id, @RequestParam("cancel") String cancel) {
        InvoiceOrderResponse invoiceOrderResponse = goodsOrderService.getByOrderId(id);
        if (Objects.isNull(invoiceOrderResponse)) {
            throw new TechCamExp(PRODUCT_NOT_EXISTS);
        }
        if (cancel.equals("true")) {
            goodsOrderService.cancelOrderInvoice(id);
        } else {
            goodsOrderService.reverseCancelOrderInvoice(id);
        }
        return ResponseEntity.ok(invoiceOrderResponse);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        InvoiceOrderResponse invoiceOrderResponse = goodsOrderService.getByOrderId(id);
        if (Objects.isNull(invoiceOrderResponse)) {
            throw new TechCamExp(PRODUCT_NOT_EXISTS);
        }
        invoiceOrderResponse.setDetails(goodsOrderService.findAllOrderDetailByOrderId(id));
        return ResponseEntity.ok(invoiceOrderResponse);
    }

}
