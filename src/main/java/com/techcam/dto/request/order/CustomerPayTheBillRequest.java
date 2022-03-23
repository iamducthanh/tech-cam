package com.techcam.dto.request.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/22/2022
 * Project_name: Tech-cam
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerPayTheBillRequest {
    private String id;
    private BigInteger customerMoney;
    private String paymentMethod;
    private Integer givenMoney;
    private Integer returnMoney;
    private Integer receiptValue;
}
