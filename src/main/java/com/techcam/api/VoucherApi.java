package com.techcam.api;

import com.techcam.dto.request.VoucherResDto;
import com.techcam.exception.TechCamExp;
import com.techcam.service.IVoucherService;
import com.techcam.util.ConstantsErrorCode;
import com.techcam.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
    public ResponseEntity<String> createVoucher(@RequestBody @Validated VoucherResDto voucherResDto, Errors errors) {
        validateVoucher(voucherResDto, errors);
        try {
            if (Integer.parseInt(voucherResDto.getQuantity()) < 1) {
                throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
            }
            if (voucherService.createVoucher(voucherResDto).equals(ConstantsErrorCode.SUCCESS)) {
                return ResponseEntity.ok(ConstantsErrorCode.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body(ConstantsErrorCode.ERROR);
    }

    @PutMapping
    public ResponseEntity<String> updateVoucher(@RequestBody @Validated VoucherResDto voucherResDto, Errors errors) {
        validateVoucher(voucherResDto, errors);
        try {
            if (Integer.parseInt(voucherResDto.getQuantity()) < 1) {
                throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
            }
            if (voucherService.updateVoucher(voucherResDto).equals(ConstantsErrorCode.SUCCESS)) {
                return ResponseEntity.ok(ConstantsErrorCode.SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body(ConstantsErrorCode.ERROR);
    }

    private void validateVoucher(@Validated @RequestBody VoucherResDto voucherResDto, Errors errors) {
        final String patternDate = "dd-MM-yyyy";
        if (errors.hasErrors()) {
            throw new TechCamExp(errors.getFieldErrors().get(0).getDefaultMessage());
        }
        if (checkEqualLength(voucherResDto.getVoucherCode(), 0, 50)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_LENGTH, "Mã giảm giá", 0, 50);
        }
        if (checkEqualLength(voucherResDto.getVoucherName(), 10, 255)) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_LENGTH, "Tên chương trình", 10, 255);
        }
        if (ConvertUtil.get().strToDate(voucherResDto.getStartDate(), patternDate)
                .compareTo(LocalDate.now()) < 0
                || ConvertUtil.get().strToDate(voucherResDto.getStartDate(), patternDate)
                .compareTo(ConvertUtil.get().strToDate(voucherResDto.getEndDate(), patternDate)) > 0) {
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
