package com.techcam.api;

import com.techcam.dto.response.order.GetInfoOrder;
import com.techcam.dto.response.order.OrderResponse;
import com.techcam.service.IOrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/24/2022
 * Project_name: Tech-cam
 */

@RestController
@RequestMapping("/vnp-pay/check-out/order")
public class CheckOutVnpay {
    @Autowired
    private IOrderService orderService;
    @GetMapping("")
    public OrderResponse checkOutVnpay(@RequestParam String vnp_TxnRef, @RequestParam String vnp_ResponseCode){
       return orderService.checkOutBank(vnp_ResponseCode,vnp_TxnRef);

    }
}
