package com.techcam.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techcam.constants.ConstantsErrorCode;
import com.techcam.dto.request.order.*;
import com.techcam.dto.response.order.GetInfoOrder;
import com.techcam.dto.response.order.GetInfoOrderDetails;
import com.techcam.dto.response.order.OrderResponse;
import com.techcam.dto.response.order.VNPAYResponse;
import com.techcam.exception.TechCamExp;
import com.techcam.service.IOrderService;
import com.techcam.type.OrderType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/20/2022
 * Project_name: Tech-cam
 */

@RestController
@RequestMapping("/api/v1/orders")
public class OrdersApi {

    @Autowired
    private IOrderService orderService;

    private final String LOCALHOST_IPV4 = "127.0.0.1";
    private final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @CrossOrigin(origins = "http://localhost:8888")
    @PostMapping
    public ResponseEntity<?> registrationOrder(@Valid @RequestBody OrderRequest orderRequest, HttpServletRequest request) {
        System.out.println(request);
        if (orderRequest.getOrderType().equals(OrderType.ONLINE.name())) {
            String ipAddress = getDevice(request);
            orderRequest.setIpAddress(ipAddress);
            System.out.println("IP của máy là : " + ipAddress);
        }
        OrderResponse response = orderService.resgistrationOrder(orderRequest, request);
        if (Objects.nonNull(response.getVnpay())) {
            String vnpay = response.getVnpay();
            try {
                VNPAYResponse cartDto = OBJECT_MAPPER.readValue(vnpay, VNPAYResponse.class);
                return ResponseEntity.ok(cartDto);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/edit-order-details")
    public OrderResponse editOrderDetails(@Valid @RequestBody EditOrderDetailRequest request) {
        if (Objects.isNull(request.getOrderId())) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        return orderService.editOrderDetails(request);
    }

    @PostMapping("/confirm-order-sale-person")
    public OrderResponse confirmOrderSalePerson(@Valid @RequestBody ConfirmSalePersonRequest request) {
        return orderService.confirmOrderSalePerson(request);
    }

    @PostMapping("/confirm-export-order")
    public OrderResponse confirmExportOrder(@Valid @RequestBody ConfirmExportOrderRequest request) {
        return orderService.confirmExportOrder(request);
    }

    @PostMapping("/edit-order-details-confirm")
    public OrderResponse editOrderDetailsConfirm(@Valid @RequestBody EditOrderDetailsConfirmRequest request) {
        return orderService.editOrderDetailsConfirm(request);
    }

    @PostMapping("/cancel-order")
    public OrderResponse cancelOrder(@Valid @RequestBody CancelOrderRequest request) {
        if (Objects.isNull(request) || StringUtils.isBlank(request.getId())) {
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        return orderService.cancelOrder(request.getId());
    }

    @PostMapping("/pay-the-bill")
    public OrderResponse payTheBill(@Valid @RequestBody CustomerPayTheBillRequest request) {
        return orderService.payTheBill(request);
    }

    @GetMapping("/{id}")
    public GetInfoOrder findById(@PathVariable("id") String id) {
        return orderService.findOrderById(id);
    }

    public String getDevice(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
                try {
                    InetAddress inetAddress = InetAddress.getLocalHost();
                    ipAddress = inetAddress.getHostAddress();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!StringUtils.isEmpty(ipAddress)
                && ipAddress.length() > 15
                && ipAddress.indexOf(",") > 0) {
            ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
        }
        return ipAddress;
    }
}
