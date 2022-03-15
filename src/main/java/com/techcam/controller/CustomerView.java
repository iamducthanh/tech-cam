package com.techcam.controller;

import com.techcam.dto.response.Customer.CustomerInfoResponse;
import com.techcam.exception.TechCamExp;
import com.techcam.service.ICustomerService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        List<CustomerInfoResponse> customerInfoResponses = customerService.getCustomers();
        model.addAttribute("customers",customerInfoResponses);
        model.addAttribute("selectCustomer",0);
        return "views/customer/004_customer";
    }
    @GetMapping("/search")
    public String CustomerViewByCreateDate(@RequestParam String startDate, @RequestParam String endDate,@RequestParam int selectCustomer, Model model)  {
        System.out.println(startDate);
        System.out.println(endDate);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date endDateFilter = format.parse(endDate);

            List<CustomerInfoResponse> customerInfoResponses = customerService.getCustomersByCreateDate(format.parse(startDate), DateUtils.addDays(endDateFilter,+1));
            System.out.println("Data ========= "+customerInfoResponses);
            model.addAttribute("customers",customerInfoResponses);
            model.addAttribute("selectCustomer",selectCustomer);
            model.addAttribute("startDate",startDate);
            model.addAttribute("endDate",endDate);
        }catch (Exception e){
            System.out.println("ERROR");
        }
        return "views/customer/004_customer";
    }

}
