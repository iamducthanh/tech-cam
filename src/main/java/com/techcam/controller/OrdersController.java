package com.techcam.controller;

import com.techcam.dto.response.order.GetInfoOrder;
import com.techcam.dto.response.staff.StaffResponseDTO;
import com.techcam.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
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
 * @since: 3/27/2022
 * Project_name: Tech-cam
 */
@Slf4j
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrderService orderService;

    @GetMapping
    public String index(Model model) {
        List<GetInfoOrder> orders = orderService.getAllOrder();
        model.addAttribute("orders", orders);

        return "views/orders/007_Orders_payment";
    }
}
