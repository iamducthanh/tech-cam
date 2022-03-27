package com.techcam.api;

import com.techcam.dto.request.invoice.InvoiceRequest;
import com.techcam.dto.response.invoice.InvoiceResponse;
import com.techcam.exception.TechCamExp;
import com.techcam.service.IGoodsreceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @PostMapping
    public ResponseEntity<?> createInvoice(@Validated @RequestBody InvoiceRequest invoiceRequest, Errors errors) {
        validateInvoice(invoiceRequest, errors);
        if (!goodsreceiptService.findAllByInvoiceCode(invoiceRequest.getInvoiceCode()).isEmpty()) {
            // mã nhập hàng đã tồn tại rồi
            throw new TechCamExp(ERROR_EXISTS, "Mã nhập hàng");
        }
        Long totalMoney = invoiceRequest.getDetails().stream()
                .mapToLong(e -> e.getPrice() * e.getQuantity()).sum();
        String result = goodsreceiptService.createInvoice(invoiceRequest);
        if (result.equals(SUCCESS.name())) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping
    public ResponseEntity<?> updateInvoice(@Validated @RequestBody InvoiceRequest invoiceRequest, Errors errors) {
        validateInvoice(invoiceRequest, errors);
        List<InvoiceResponse> invoiceFindByCode = goodsreceiptService.findAllByInvoiceCode(invoiceRequest.getInvoiceCode())
                .stream().filter(e -> !e.getInvoiceCode().equals(invoiceRequest.getInvoiceCode()))
                .collect(Collectors.toList());
        if (!invoiceFindByCode.isEmpty()) {
            // mã nhập hàng đã tồn tại rồi
            throw new TechCamExp(ERROR_EXISTS, "Mã nhập hàng");
        }
        String result = goodsreceiptService.updateInvoice(invoiceRequest);
        if (result.equals(SUCCESS.name())) {
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        InvoiceResponse invoiceResponse = goodsreceiptService.getByInvoiceId(id);
        if (Objects.nonNull(invoiceResponse)) {
            return ResponseEntity.ok(invoiceResponse);
        }
        throw new TechCamExp(ERROR_NOT_EXISTS, "Hoá đơn nhập hàng");
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