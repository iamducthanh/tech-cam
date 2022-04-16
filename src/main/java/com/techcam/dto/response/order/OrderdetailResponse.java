package com.techcam.dto.response.order;

import lombok.Data;

/**
 * @author Dev
 * @version 1.0
 * @since 15.4.2022
 */
@Data
public class OrderdetailResponse {
    private String orderId;
    private String productId;
    private int discount;
    private int quantity;
    private String note;
}
