package com.techcam.api;

import com.techcam.dto.request.voucher.VoucherRequest;
import com.techcam.dto.response.voucher.VoucherResponse;
import com.techcam.exception.TechCamExp;
import com.techcam.service.IVoucherService;
import com.techcam.constants.ConstantsErrorCode;
import com.techcam.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @PostMapping
    public ResponseEntity<String> createVoucher(@RequestBody @Validated VoucherRequest voucherRequest, Errors errors) {
        validateVoucher(voucherRequest, errors);
        try {
            if (Integer.parseInt(voucherRequest.getVoucherQuantity()) < 1) {
                throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
            }
            if (voucherService.createVoucher(voucherRequest).equals(ConstantsErrorCode.SUCCESS)) {
                return ResponseEntity.ok(ConstantsErrorCode.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body(ConstantsErrorCode.ERROR);
    }

    @PutMapping
    public ResponseEntity<String> updateVoucher(@RequestBody @Validated VoucherRequest voucherRequest, Errors errors) {
        validateVoucher(voucherRequest, errors);
        try {
            if (Integer.parseInt(voucherRequest.getVoucherQuantity()) < 1) {
                throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
            }
            if (voucherService.updateVoucher(voucherRequest).equals(ConstantsErrorCode.SUCCESS)) {
                return ResponseEntity.ok(ConstantsErrorCode.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body(ConstantsErrorCode.ERROR);
    }

    @PutMapping("/active/{id}")
    public ResponseEntity<String> activeVoucher(@PathVariable("id") String id) {
        try {
            if (voucherService.activeVoucher(id).equals(ConstantsErrorCode.SUCCESS)) {
                return ResponseEntity.ok(ConstantsErrorCode.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body(ConstantsErrorCode.ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VoucherResponse> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(voucherService.getById(id));
    }

    private void validateVoucher(@Validated @RequestBody VoucherRequest voucherRequest, Errors errors) {
        final String patternDate = "dd-MM-yyyy";
        if (errors.hasErrors()) {
            throw new TechCamExp(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        if (checkEqualLength(voucherRequest.getVoucherCode(), 0, 50)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_LENGTH, "Mã giảm giá", 0, 50);
        }
        if (checkEqualLength(voucherRequest.getVoucherName(), 10, 255)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_LENGTH, "Tên chương trình", 10, 255);
        }
        if (ConvertUtil.get().strToDate(voucherRequest.getVoucherStartDate(), patternDate)
                .compareTo(new Date()) < 0
                || ConvertUtil.get().strToDate(voucherRequest.getVoucherStartDate(), patternDate)
                .compareTo(ConvertUtil.get().strToDate(voucherRequest.getVoucherEndDate(), patternDate)) > 0) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVoucher(@PathVariable("id") String id) {
        if (!id.isEmpty() && voucherService.deleteVoucher(id).equals(ConstantsErrorCode.SUCCESS)) {
            return ResponseEntity.ok(ConstantsErrorCode.SUCCESS);
        }
        return ResponseEntity.badRequest().body(ConstantsErrorCode.ERROR);
    }

    private boolean checkEqualLength(String value, int min, int max) {
        return value.length() < min || value.length() > max;
    }

}
