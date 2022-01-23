package com.techcam.techcam.api;

import com.techcam.techcam.dto.response.VoucherResp;
import com.techcam.techcam.service.VoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(params = "voucher-code")
    public VoucherResp checkVoucher(@RequestParam("voucher-code") String voucherCode) {

        return _voucherService.checkVoucher(voucherCode);
    }


}
