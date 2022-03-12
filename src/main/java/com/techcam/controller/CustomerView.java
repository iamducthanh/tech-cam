package com.techcam.controller;

import com.techcam.dto.response.Customer.CustomerInfoResponse;
import com.techcam.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 2/27/2022
 * Project_name: Tech-cam
 */

@Controller
@RequestMapping("/customer")
public class CustomerView {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("")
    public String customerView(Model model) {
//        List<CustomerInfoResponse> customerInfoResponses = customerService.getCustomers();
//        model.addAttribute("customers",customerInfoResponses);
        return "views/customer/004_customer";
    }

}
