package com.techcam.dto.request.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/20/2022
 * Project_name: Tech-cam
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDetailsRequest {
    private String id;
    private String productId;
    private List<String> imei;
    private int quantity;
    private String note;
    private int discount;

}