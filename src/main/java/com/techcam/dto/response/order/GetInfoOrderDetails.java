package com.techcam.dto.response.order;

import com.techcam.entity.OrdersEntity;
import com.techcam.entity.ProductEntity;

import java.util.Date;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/22/2022
 * Project_name: Tech-cam
 */

public class GetInfoOrderDetails {
    private String id;
    private String imei;
    private String status;
    private Date createDate;
    private String note;
    private String createBy;
    private String modifierBy;
    private Date modifierDate;
    private Boolean deleteFlag;
    private int discount;
    private int quantity;
    private OrdersEntity orders;
    private ProductEntity product;
}
