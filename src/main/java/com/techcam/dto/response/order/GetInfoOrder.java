package com.techcam.dto.response.order;

import com.techcam.dto.response.Customer.CustomerInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/22/2022
 * Project_name: Tech-cam
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetInfoOrder {
    private String id;
    private Date orderDate;
    private Integer tax;
    private String transactionStatus;
    private Date paymentDate;
    private Integer itemQuantity;
    private Integer totalAmount;
    private String orderType;
    private String stockKeeper;
    private String recipientName;
    private String recipientPhone;
    private String paymentMethod;
    private String recipientAddress;
    private String shipmentStatus;
    private String salesPerson;
    private String accounting;
    private String shipmentId;
    private String status;
    private String note;
    private Date deliveryDate;
    private Date createDate;
    private Date modifierDate;
    private String createBy;
    private String modifierBy;
    private Boolean deleteFlag;
    private String ipAddress;
    private String bankTransaction;
    private CustomerInfoResponse customer;
    private String voucherCustomerId;
}
