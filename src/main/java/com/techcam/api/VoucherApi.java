package com.techcam.api;

import com.techcam.dto.error.ErrorRespDto;
import com.techcam.dto.request.VoucherResDto;
import com.techcam.dto.response.VoucherRespDto;
import com.techcam.exception.IllegalStateConfig;
import com.techcam.service.IVoucherService;
import com.techcam.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 3:05 PM
 * Project_name : tech-cam
 */

@RestController
@RequestMapping("/api/v1/voucher")
@RequiredArgsConstructor
public class VoucherApi {

    private final IVoucherService _voucherService;

    @PostMapping
    public VoucherRespDto createVoucher(@RequestBody VoucherResDto voucherResDto) {
        Instant startDate = ConvertUtil.get().strToDate(voucherResDto.getStartDate(), "yyyy-MM-dd hh:mm").toInstant();
        Instant endDate = ConvertUtil.get().strToDate(voucherResDto.getEndDate(), "yyyy-MM-dd hh:mm").toInstant();
        if (startDate.compareTo(Instant.now()) < 0) {
            throw new IllegalStateConfig(ErrorRespDto.builder()
                    .message("Không thể tạo voucher cho một thời gian đã trôi qua")
                    .build());
        }
        if (startDate.compareTo(endDate) > 0) {
            throw new IllegalStateConfig(ErrorRespDto.builder()
                    .message("Thời gian kết thúc không thể sớm hơn thời gian bắt đầu hiệu lực voucher")
                    .build());
        }
        if (voucherResDto.getQuantity() < 1) {
            throw new IllegalStateConfig(ErrorRespDto.builder()
                    .message("Số lượng voucher tạo ban đầu không thể nhỏ hơn 1")
                    .build());
        }
        if (voucherResDto.getDiscount() < 1) {
            throw new IllegalStateConfig(ErrorRespDto.builder()
                    .message("Số tiền giảm cho mỗi voucher không thể nhỏ hơn 1")
                    .build());
        }
        return _voucherService.createVoucher(voucherResDto);
    }

    @GetMapping(params = "voucher-code")
    public VoucherRespDto checkVoucher(@RequestParam("voucher-code") String voucherCode) {
        return _voucherService.checkVoucher(voucherCode);
    }


}
