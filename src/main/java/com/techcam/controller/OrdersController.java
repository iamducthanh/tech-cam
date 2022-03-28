package com.techcam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/27/2022
 * Project_name: Tech-cam
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {
    @GetMapping
    public String getALlOrder(Model model){
        return null;
    }
}
