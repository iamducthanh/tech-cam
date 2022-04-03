package com.techcam.api;

import com.techcam.dto.request.invoice.InvoiceDetailRequest;
import com.techcam.dto.request.invoice.InvoiceRequest;
import com.techcam.dto.response.invoice.InvoiceResponse;
import com.techcam.exception.TechCamExp;
import com.techcam.service.IGoodsOrderService;
import com.techcam.service.IGoodsreceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static com.techcam.constants.ConstantsErrorCode.*;
import static com.techcam.type.CustomerStatus.SUCCESS;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/26/2022 9:42 PM
 * Project_name : tech-cam
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/invoice")
public class InvoiceApi {

    private final IGoodsreceiptService goodsreceiptService;

    private final IGoodsOrderService goodsOrderService;

    @PostMapping
    public ResponseEntity<?> createInvoice(@Validated @RequestBody InvoiceRequest invoiceRequest, Errors errors) {
        validateInvoice(invoiceRequest, errors);
        if (!goodsreceiptService.findAllByInvoiceCode(invoiceRequest.getInvoiceCode()).isEmpty()) {
            // mã nhập hàng đã tồn tại rồi
            throw new TechCamExp(ERROR_EXISTS, "Mã nhập hàng");
        }
        if (invoiceRequest.getDetails().isEmpty()) {
            throw new TechCamExp(INVOICE_NOT_DETAIL);
        }
        for (InvoiceDetailRequest x : invoiceRequest.getDetails()) {
            try {
                final int minNumber = 1; // số tốit hiểu
                if (x.getQuantity() < minNumber) {
                    throw new TechCamExp(INVOICE_NOT_ZERO, "Số lượng trên hoá đơn");
                }
                if (x.getQuantityActual() < minNumber) {
                    throw new TechCamExp(INVOICE_NOT_ZERO, "Số lượng nhập");
                }
                if (x.getPrice() < minNumber) {
                    throw new TechCamExp(ERROR_MIN_MONEY, "mỗi sản phẩm", minNumber);
                }
            } catch (ArithmeticException e) {
                throw new TechCamExp(ERROR_FORMAT_NUMBER);
            }
        }
        String result = goodsreceiptService.createInvoice(invoiceRequest);
        if (result.equals(SUCCESS.name())) {
            return ResponseEntity.ok(result);
        }
        throw new TechCamExp(ERROR_SAVE_FAILED);
    }

    @PutMapping
    public ResponseEntity<?> updateInvoice(@Validated @RequestBody InvoiceRequest invoiceRequest, Errors errors) {
        validateInvoice(invoiceRequest, errors);
        InvoiceResponse invoiceResponse = goodsreceiptService.getByInvoiceId(invoiceRequest.getInvoiceId());
        if (Objects.isNull(invoiceResponse) || Objects.isNull(invoiceResponse.getInvoiceId()))
            throw new TechCamExp(ERROR_NOT_EXISTS, "Hoá đơn nhập hàng");
        if (!invoiceRequest.getInvoiceCode().equals(invoiceResponse.getInvoiceCode())) {
            List<InvoiceResponse> invoiceFindByCode = goodsreceiptService.findAllByInvoiceCode(invoiceRequest.getInvoiceCode());
            if (!invoiceFindByCode.isEmpty()) {
                // mã nhập hàng đã tồn tại rồi
                throw new TechCamExp(ERROR_EXISTS, "Mã nhập hàng");
            }
        }
        if (invoiceRequest.getDetails().isEmpty()) {
            throw new TechCamExp(INVOICE_NOT_DETAIL);
        }
        for (InvoiceDetailRequest x : invoiceRequest.getDetails()) {
            try {
                final int minNumber = 1; // số tốit hiểu
                if (x.getQuantity() < minNumber) {
                    throw new TechCamExp(INVOICE_NOT_ZERO, "Số lượng trên hoá đơn");
                }
                if (x.getQuantityActual() < minNumber) {
                    throw new TechCamExp(INVOICE_NOT_ZERO, "Số lượng nhập");
                }
                if (x.getPrice() < minNumber) {
                    throw new TechCamExp(ERROR_MIN_MONEY, "mỗi sản phẩm", minNumber);
                }
            } catch (ArithmeticException e) {
                throw new TechCamExp(ERROR_FORMAT_NUMBER);
            }
        }
        String result = goodsreceiptService.updateInvoice(invoiceRequest);
        if (result.equals(SUCCESS.name())) {
            return ResponseEntity.ok(result);
        }
        throw new TechCamExp(ERROR_SAVE_FAILED);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        InvoiceResponse invoiceResponse = goodsreceiptService.getByInvoiceId(id);
        if (Objects.nonNull(invoiceResponse)) {
            invoiceResponse.setDetails(goodsreceiptService.findAllInvoiceDetailByInvoiceId(id));
            return ResponseEntity.ok(invoiceResponse);
        }
        throw new TechCamExp(ERROR_NOT_EXISTS, "Hoá đơn nhập hàng");
    }

    @GetMapping(params = {"invoiceId"})
    public ResponseEntity<?> getAllDetailInvoiceById(@RequestParam("invoiceId") String invoiceId) {
        return ResponseEntity.ok(goodsreceiptService.findAllInvoiceDetailByInvoiceId(invoiceId));
    }

    @GetMapping(params = {"orderId"})
    public ResponseEntity<?> getByOrderId(@RequestParam("orderId") String orderId) {
        return ResponseEntity.ok(goodsOrderService.findAllOrderDetailByOrderId(orderId));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        InvoiceResponse invoiceResponse = goodsreceiptService.getByInvoiceId(id);
        if (Objects.nonNull(invoiceResponse)) {
            goodsreceiptService.deleteById(id);
            return ResponseEntity.ok(invoiceResponse);
        }
        throw new TechCamExp(ERROR_NOT_EXISTS, "Hoá đơn nhập hàng");
    }

    private void validateInvoice(@RequestBody @Validated InvoiceRequest invoiceRequest, Errors errors) {
        if (errors.hasErrors()) {
            throw new TechCamExp(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        if (invoiceRequest.getDetails().isEmpty()) {
            // không có chi tiết nhập hàng  = không có sản phẩm nhập
            throw new TechCamExp(INVOICE_NOT_DETAIL);
        }
        if (invoiceRequest.getInvoiceCode().length() > 50) {
            throw new TechCamExp(ERROR_LENGTH, 1, 50);
        }
        if (invoiceRequest.getDiscount() < 0) {
            throw new TechCamExp(ERROR_MIN_MONEY, "giảm giá", 0);
        }
        if (invoiceRequest.getPaid() < 0) {
            throw new TechCamExp(ERROR_MIN_MONEY, "đã thanh toán NCC ", 0);
        }
    }

}