package com.techcam.dto.request.order;

import com.techcam.dto.request.Customer.CustomerRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;
import java.sql.Timestamp;
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
public class OrderRequest {
    private CustomerRequest customer;
    private String voucherId;
    private List<OrderProductDetailsRequest> productDetails;
    @NotBlank
    private String paymentMethod;
    @NotBlank
    private String orderMethod;
    private String note;
    private int tax;
    private String orderType;
    private String recipientName;
    private String recipientPhone;
    private String recipientAddress;
    private String shipmentStatus;
    private String salesperson;
    private String accounting;
    private String ipAddress;
    private int totalAmount;
    private int totalDiscount;

}