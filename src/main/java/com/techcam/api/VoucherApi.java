package com.techcam.api;

import com.techcam.dto.error.ErrorRespDto;
import com.techcam.dto.request.VoucherResDto;
import com.techcam.dto.response.VoucherRespDto;
import com.techcam.exception.IllegalStateConfig;
import com.techcam.service.IVoucherService;
import com.techcam.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
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

    private final IVoucherService _voucherService;

    @PostMapping
    public ResponseEntity<?> createVoucher(@RequestBody VoucherResDto voucherResDto) {
        try {
            Instant startDate = ConvertUtil.get().strToDate(voucherResDto.getStartDate(), "yyyy-MM-dd hh:mm").toInstant();
            Instant endDate = ConvertUtil.get().strToDate(voucherResDto.getEndDate(), "yyyy-MM-dd hh:mm").toInstant();
            if (startDate.compareTo(Instant.now()) < 0) {
                throw new IllegalStateConfig(ErrorRespDto.builder()
                        .message("Không thể tạo voucher cho một thời gian đã trôi qua")
                        .date(LocalDateTime.now())
                        .build());
            }
            if (startDate.compareTo(endDate) > 0) {
                throw new IllegalStateConfig(ErrorRespDto.builder()
                        .message("Thời gian kết thúc không thể sớm hơn thời gian bắt đầu hiệu lực voucher")
                        .date(LocalDateTime.now())
                        .build());
            }
            if (Integer.parseInt(voucherResDto.getQuantity()) < 1) {
                throw new IllegalStateConfig(ErrorRespDto.builder()
                        .message("Số lượng voucher tạo ban đầu không thể nhỏ hơn 1")
                        .date(LocalDateTime.now())
                        .build());
            }
            if (Long.parseLong(voucherResDto.getDiscount()) < 1) {
                throw new IllegalStateConfig(ErrorRespDto.builder()
                        .message("Số tiền giảm cho mỗi voucher không thể nhỏ hơn 1")
                        .date(LocalDateTime.now())
                        .build());
            }
            return ResponseEntity.ok(_voucherService.createVoucher(voucherResDto));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateVoucher(@RequestBody VoucherResDto voucherResDto) {
        try {
            if (voucherResDto.getId() == null || voucherResDto.getId().isEmpty()) {
                throw new IllegalStateConfig("Voucher không đúng");
            }
            Date startDate = ConvertUtil.get().strToDate(voucherResDto.getStartDate(), "yyyy-MM-dd hh:mm");
            Date endDate = ConvertUtil.get().strToDate(voucherResDto.getEndDate(), "yyyy-MM-dd hh:mm");
            if (endDate.compareTo(new Date()) < 0) {
                throw new IllegalStateConfig(ErrorRespDto.builder()
                        .message("Không thể tạo voucher cho một thời gian đã trôi qua")
                        .date(LocalDateTime.now())
                        .build());
            }
            if (startDate.compareTo(endDate) > 0) {
                throw new IllegalStateConfig(ErrorRespDto.builder()
                        .message("Thời gian kết thúc không thể sớm hơn thời gian bắt đầu hiệu lực voucher")
                        .date(LocalDateTime.now())
                        .build());
            }
            if (Integer.parseInt(voucherResDto.getQuantity()) < 1) {
                throw new IllegalStateConfig(ErrorRespDto.builder()
                        .message("Số lượng voucher tạo ban đầu không thể nhỏ hơn 1")
                        .date(LocalDateTime.now())
                        .build());
            }
            if (Integer.parseInt(voucherResDto.getDiscount()) < 1) {
                throw new IllegalStateConfig(ErrorRespDto.builder()
                        .message("Số tiền giảm cho mỗi voucher không thể nhỏ hơn 1")
                        .date(LocalDateTime.now())
                        .build());
            }
            return ResponseEntity.ok(_voucherService.updateVoucher(voucherResDto));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @GetMapping(params = "voucher-code")
    public ResponseEntity<?> checkVoucher(@RequestParam("voucher-code") String voucherCode) {
        return ResponseEntity.ok(_voucherService.checkVoucher(voucherCode));
    }

}
