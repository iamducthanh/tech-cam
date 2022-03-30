package com.techcam.dto.response.order;

import lombok.Data;

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
public class GetInfoOrderDetails {

    private String id;
    private String imei;
    private Date createDate;
    private String note;
    private String createBy;
    private String modifierBy;
    private Date modifierDate;
    private Boolean deleteFlag;
    private Integer discount;
    private Integer quantity;
    private GetProductByOrderId product;

}
