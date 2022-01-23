package com.techcam.techcam.api;

import com.techcam.techcam.dto.request.VoucherResDto;
import com.techcam.techcam.dto.response.VoucherRespDto;
import com.techcam.techcam.service.VoucherService;
import com.techcam.techcam.util.ConvertUtil;
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

    private final VoucherService _voucherService;

    @PostMapping
    public VoucherRespDto createVoucher(@RequestBody VoucherResDto voucherResDto){
        Instant startDate = ConvertUtil.get().strToDate(voucherResDto.getStartDate(), "yyyy-MM-dd hh:mm").toInstant();
        Instant endDate = ConvertUtil.get().strToDate(voucherResDto.getEndDate(), "yyyy-MM-dd hh:mm").toInstant();
        if (startDate.compareTo(Instant.now()) < 0) {
            // TODO trả về thời gian đã trôi qua
            return null;
        }
        if (startDate.compareTo(endDate) > 0) {
            // TODO trả về thời gian kết thúc không thể sớm hơn thời gian bắt đầu
            return null;
        }
        if (voucherResDto.getQuantity() < 1) {
            // TODO trả về số lượng không thể nhỏ hơn 1
            return null;
        }
        if (voucherResDto.getDiscount() < 1) {
            // TODO trả về số tiền giảm không thể nhỏ hơn 1
            return null;
        }
        return _voucherService.createVoucher(voucherResDto);
    }

    @GetMapping(params = "voucher-code")
    public VoucherRespDto checkVoucher(@RequestParam("voucher-code") String voucherCode) {

        return _voucherService.checkVoucher(voucherCode);
    }


}
