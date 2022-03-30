package com.techcam.dto.response.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DucBV
 * @version 1.0
 * @since 30.3.2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetProductByOrderId {

    private String name;
    private String productCode;
    private int quantity;
    private long price;
    private String status;

}
