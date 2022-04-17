package com.techcam.dto.request.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 4/17/2022
 * Project_name: Tech-cam
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingOrderRequest {
    private Integer id;
    private String note;
    private Integer feeDelivery;
    private String shipperName;
    private String shipperPhone;
}
