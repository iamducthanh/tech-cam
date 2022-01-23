package com.techcam.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * Description :
 */
@RequestMapping("/staff")
@Controller
public class StaffController {

    @GetMapping
    public String index() {
        return "001_Staff";
    }
}
