package com.techcam.dto.request.order;

import lombok.Data;

import java.util.List;

/**
 * @author DucBV
 * @version 1.0
 * @since 15.4.2022
 */
@Data
public class OrderdetailRequest {
    private String id;
    private String orderId;
    private String productId;
    private List<String> imei;
    private int discount;
    private int quantity;
    private String note;
}
