package com.techcam.dto.request.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class OrderProductRequest {
   private  List<OrderProductDetailsRequest> productDetailsRequests;
    private int totalAmount;
    private int totalDiscount;
}
