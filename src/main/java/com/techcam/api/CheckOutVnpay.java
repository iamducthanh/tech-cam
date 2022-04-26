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
import org.springframework.web.servlet.view.RedirectView;

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
    public RedirectView checkOutVnpay(@RequestParam String vnp_TxnRef, @RequestParam String vnp_ResponseCode){
       OrderResponse response = orderService.checkOutBank(vnp_ResponseCode,vnp_TxnRef);
        RedirectView redirectView = new RedirectView();
        redirectView.addStaticAttribute("checkOutCart", response.getStatus());
        redirectView.setUrl("http://localhost:8888/check-out/result");
        return redirectView;
    }
}
