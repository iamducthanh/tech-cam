package com.techcam.api;

import com.techcam.dto.request.VoucherResDto;
import com.techcam.exception.TechCamExp;
import com.techcam.service.IVoucherService;
import com.techcam.repo.util.ConstantsErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> createVoucher(@RequestBody @Validated VoucherResDto voucherResDto, Errors errors) {
        if (errors.hasErrors()) {
            throw new TechCamExp(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        if (checkEqualLength(voucherResDto.getVoucherCode(), 0, 50)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_LENGTH, "Mã giảm giá", 0, 50);
        }
        if (checkEqualLength(voucherResDto.getVoucherName(), 10, 255)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_LENGTH, "Tên mã giảm giá", 10, 255);
        }
//        return ResponseEntity.ok(voucherService.createVoucher(voucherResDto));
        return null;
    }

    @PutMapping
    public ResponseEntity<?> updateVoucher(@RequestBody @Validated VoucherResDto voucherResDto, Errors errors) {
        if (errors.hasErrors()) {
            throw new TechCamExp(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        if (checkEqualLength(voucherResDto.getVoucherCode(), 0, 50)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_LENGTH, "Mã giảm giá", 0, 50);
        }
        if (checkEqualLength(voucherResDto.getVoucherName(), 10, 255)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_LENGTH, "Tên mã giảm giá", 10, 255);
        }
        return null;
//        return ResponseEntity.ok(voucherService.updateVoucher(voucherResDto));
    }

    @GetMapping(params = "voucher-code")
    public ResponseEntity<?> checkVoucher(@RequestParam("voucher-code") String voucherCode) {
        return null;
//        return ResponseEntity.ok(voucherService.checkVoucher(voucherCode));
    }

    private boolean checkEqualLength(String value, int min, int max) {
        return value.length() < min || value.length() > max;
    }

}
