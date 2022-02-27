package com.techcam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 2/27/2022
 * Project_name: Tech-cam
 */

@Controller
@RequestMapping("v1/view/customer")
public class CustomerView {
    @GetMapping()
    public String customerView(){
        return "link customer";
    }

}
