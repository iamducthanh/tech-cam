package com.techcam.controller;

import com.techcam.dto.response.CategoryRespDto;
import com.techcam.dto.response.VoucherRespDto;
import com.techcam.service.ICategoryService;
import com.techcam.service.IVoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 2/27/2022 10:48 AM
 * Project_name : tech-cam
 */

@Controller
@RequestMapping("/voucher")
@RequiredArgsConstructor
public class VoucherController {

    private final IVoucherService voucherService;

    private final ICategoryService categoryService;

    @GetMapping
    public String voucherManager(Model model) {
//        List<VoucherRespDto> lstVoucher = voucherService.getAllVoucher();
//        List<CategoryRespDto> lstCategory = categoryService.getAllCategory();
        List<VoucherRespDto> lstVoucher = new ArrayList<>();
        List<CategoryRespDto> lstCategory = new ArrayList<>();
        VoucherRespDto voucherRespDto = VoucherRespDto.builder()
                .id("1")
                .voucherCode("2")
                .voucherName("3")
                .startDate(new Date())
                .endDate(new Date())
                .discount(Long.parseLong("324325"))
                .minAmount(Long.parseLong("20000"))
                .status(true)
                .hidden(false)
                .createBy("Ngoc")
                .createDate(new Date())
                .modifiedDate(new Date())
                .build();
        lstVoucher.add(voucherRespDto);
        model.addAttribute("lstVoucher", lstVoucher);
        model.addAttribute("lstCategory", lstCategory);
        // TODO lịch sử sử dụng voucher
//        model.addAttribute("lstHistoryUseVoucher", )
        return "views/voucher";
    }

}
