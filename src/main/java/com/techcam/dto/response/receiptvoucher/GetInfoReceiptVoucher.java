package com.techcam.dto.response.receiptvoucher;

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
 * @since: 4/24/2022
 * Project_name: Tech-cam
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetInfoReceiptVoucher {
    private Integer id;
    private String payer;
    private String payerPhone;
    private String receiptName;
    private Integer receiptValue;
    private String description;
    private String status;
    private String note;
    private Date createDate;
    private Date modifierDate;
    private String createBy;
    private String modifierBy;
    private boolean deleteFlag;
    private Integer givenMoney;
    private Integer returnMoney;
}
