package com.techcam.api;

import com.techcam.constants.ConstantsErrorCode;
import com.techcam.dto.request.voucher.VoucherRequest;
import com.techcam.dto.response.voucher.VoucherResponse;
import com.techcam.dto.response.voucher.VoucherUseByOrderResponse;
import com.techcam.exception.TechCamExp;
import com.techcam.service.IOrderService;
import com.techcam.service.IVoucherService;
import com.techcam.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import static com.techcam.type.CustomerStatus.FAILED;
import static com.techcam.type.CustomerStatus.SUCCESS;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 3:05 PM
 * Project_name : tech-cam
 */

@RestController
@RequestMapping("/api/voucher")
@RequiredArgsConstructor
public class VoucherApi {

    private final IVoucherService voucherService;

    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<String> createVoucher(@RequestBody @Validated VoucherRequest voucherRequest, Errors errors) {
        final String patternDate = "dd-MM-yyyy";
        if (errors.hasErrors()) {
            throw new TechCamExp(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        if (checkEqualLength(voucherRequest.getVoucherCode(), 5, 50)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_LENGTH, "Mã giảm giá", 5, 50);
        }
        if (checkEqualLength(voucherRequest.getVoucherName(), 5, 255)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_LENGTH, "Tên chương trình", 5, 255);
        }
        voucherRequest.setVoucherCode(voucherRequest.getVoucherCode().toLowerCase());
        if (!voucherService.findAllByCode(voucherRequest.getVoucherCode()).isEmpty()) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_EXISTS, "Mã giảm giá");
        }
        try {
            if (ConvertUtil.get().strToDate(voucherRequest.getVoucherStartDate(), patternDate).compareTo(new Date()) < 0 || ConvertUtil.get().strToDate(voucherRequest.getVoucherStartDate(), patternDate).compareTo(ConvertUtil.get().strToDate(voucherRequest.getVoucherEndDate(), patternDate)) > 0) {
                throw new TechCamExp(ConstantsErrorCode.VOUCHER_DATE_NOT_PAST);
            }
            if (Long.parseLong(voucherRequest.getVoucherDiscount()) <= 0) {
                throw new TechCamExp(ConstantsErrorCode.ERROR_MIN_MONEY, "giảm giá", "1đ");
            }
            if (Integer.parseInt(voucherRequest.getVoucherQuantity()) < 1) {
                throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
            }
            if (voucherService.createVoucher(voucherRequest).equals(SUCCESS.name())) {
                return ResponseEntity.ok(SUCCESS.name());
            }
        } catch (ArithmeticException e) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_FORMAT_NUMBER);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new TechCamExp(ConstantsErrorCode.ERROR_SAVE_FAILED);
    }

    @PutMapping
    public ResponseEntity<String> updateVoucher(@RequestBody @Validated VoucherRequest voucherRequest, Errors errors) {
        final String patternDate = "dd-MM-yyyy";
        if (errors.hasErrors()) {
            throw new TechCamExp(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        VoucherResponse voucherResponse = voucherService.getById(voucherRequest.getVoucherId());
        if (Objects.isNull(voucherResponse) || Objects.isNull(voucherResponse.getVoucherId())) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_NOT_EXISTS, "Mã giảm giả");
        }
        if (!voucherResponse.getVoucherCode().equals(voucherRequest.getVoucherCode().toUpperCase())) {
            if (!voucherService.findAllByCode(voucherRequest.getVoucherCode()).isEmpty()) {
                throw new TechCamExp(ConstantsErrorCode.ERROR_EXISTS, "Mã giảm giá");
            }
        }
        voucherRequest.setVoucherCode(voucherRequest.getVoucherCode().toLowerCase());
        if (checkEqualLength(voucherRequest.getVoucherCode(), 5, 50)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_LENGTH, "Mã giảm giá", 5, 50);
        }
        if (checkEqualLength(voucherRequest.getVoucherName(), 10, 255)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_LENGTH, "Tên chương trình", 10, 255);
        }
        try {
            if (ConvertUtil.get().strToDate(voucherRequest.getVoucherEndDate(), patternDate).compareTo(new Date()) < 0) {
                throw new TechCamExp(ConstantsErrorCode.VOUCHER_DATE_NOT_PAST);
            }
            if (Long.parseLong(voucherRequest.getVoucherDiscount()) <= 0) {
                throw new TechCamExp(ConstantsErrorCode.ERROR_MIN_MONEY, "giảm giá", "1đ");
            }
            if (Integer.parseInt(voucherRequest.getVoucherQuantity()) < 1) {
                throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
            }
            if (Integer.parseInt(voucherRequest.getVoucherQuantity()) < orderService.findAllByVoucherId(voucherRequest.getVoucherId()).size()) {
                throw new TechCamExp(ConstantsErrorCode.VOUCHER_MAX_USED);
            }
            if (voucherService.updateVoucher(voucherRequest).equals(SUCCESS.name())) {
                return ResponseEntity.ok(SUCCESS.name());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new TechCamExp(ConstantsErrorCode.ERROR_SAVE_FAILED);
    }

    @PutMapping("/active/{id}")
    public ResponseEntity<String> activeVoucher(@PathVariable("id") String id) {
        try {
            if (voucherService.activeVoucher(id).equals(SUCCESS.name())) {
                return ResponseEntity.ok(SUCCESS.name());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new TechCamExp(ConstantsErrorCode.ERROR_SAVE_FAILED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoucherResponse> getById(@PathVariable("id") String id) {
        VoucherResponse voucherResponse = voucherService.getById(id);
        if (Objects.isNull(voucherResponse) || voucherResponse.getVoucherStartDate().compareTo(new Date()) < 0) {
            // voucher không đúng
            throw new TechCamExp(ConstantsErrorCode.VOUCHER_NOT_EXISTS);
        }
        List<VoucherUseByOrderResponse> lstUsedByVoucherId = orderService.findAllByVoucherId(voucherResponse.getVoucherId());
        if (voucherResponse.getVoucherEndDate().compareTo(new Date()) > 0 || voucherResponse.getVoucherQuantity() <= lstUsedByVoucherId.size()) {
            // VOUCHER đã hết lượt dùng
            throw new TechCamExp(ConstantsErrorCode.VOUCHER_END_USED);
        }
        return ResponseEntity.ok(voucherResponse);
    }

    @GetMapping(params = "code")
    public ResponseEntity<VoucherResponse> getAllByCode(@RequestParam("code") String code) {
        List<VoucherResponse> lstVoucher = voucherService.findAllByCode(code);
        if (lstVoucher.isEmpty()) {
            // voucher không đúng
            throw new TechCamExp(ConstantsErrorCode.VOUCHER_NOT_EXISTS);
        }
        VoucherResponse response = null;
        for (VoucherResponse x : lstVoucher) {
            List<VoucherUseByOrderResponse> lstUsedByVoucherId = orderService.findAllByVoucherId(x.getVoucherId());
            if (x.getVoucherEndDate().compareTo(new Date()) <= 0 && x.getVoucherQuantity() > lstUsedByVoucherId.size()) {
                response = x;
                break;
            }
        }
        if (Objects.isNull(response)) {
            // VOUCHER đã hết lượt dùng
            throw new TechCamExp(ConstantsErrorCode.VOUCHER_END_USED);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}/used")
    public ResponseEntity<List<VoucherUseByOrderResponse>> getCustemorUserByVoucherId(@PathVariable("id") String id) {
        return ResponseEntity.ok(orderService.findAllByVoucherId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVoucher(@PathVariable("id") String id) {
        if (!id.isEmpty() && voucherService.deleteVoucher(id).equals(SUCCESS.name())) {
            return ResponseEntity.ok(SUCCESS.name());
        }
        return ResponseEntity.badRequest().body(FAILED.name());
    }

    private boolean checkEqualLength(String value, int min, int max) {
        return value.length() < min || value.length() > max;
    }


}
