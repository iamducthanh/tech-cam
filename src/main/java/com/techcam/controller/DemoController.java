package com.techcam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * Description :
 */
@Controller
public class DemoController {

    @GetMapping("/")
    public String index() {
        return "views/layout_container";
    }
    @GetMapping("/1")
    public String index1() {
        return "index";
    }

    public static void main(String[] args) {

    }
}
