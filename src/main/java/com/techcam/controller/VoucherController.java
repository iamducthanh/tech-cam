package com.techcam.controller;

import com.techcam.dto.response.Customer.CustomerInfoResponse;
import com.techcam.dto.response.category.CategoryResponse;
import com.techcam.dto.response.order.GetInfoOrder;
import com.techcam.dto.response.voucher.VoucherResponse;
import com.techcam.service.ICategoryService;
import com.techcam.service.ICustomerService;
import com.techcam.service.IOrderService;
import com.techcam.service.IVoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    private final ICustomerService customerService;

    private final IOrderService orderService;

    @GetMapping
    public String voucherManager(Model model) {
        List<VoucherResponse> lstVoucher = voucherService.getAllVoucher();
        for (VoucherResponse x : lstVoucher) {
            x.setLstCustomer(voucherService.findAllIdCustomerByVoucherId(x.getVoucherId()));
        }
        List<CategoryResponse> lstCategory = categoryService.getAllCategory();
        List<CustomerInfoResponse> lstCustomer = customerService.getCustomers();
        lstVoucher.sort(((o1, o2) -> o2.getVoucherCreateDate().compareTo(o1.getVoucherCreateDate())));
        model.addAttribute("lstVoucher", lstVoucher);
        model.addAttribute("lstCategory", lstCategory);
        model.addAttribute("lstCustomer", lstCustomer);
        return "views/voucher/index";
    }

}
