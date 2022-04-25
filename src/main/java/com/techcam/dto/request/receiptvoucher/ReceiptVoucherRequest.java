package com.techcam.dto.request.receiptvoucher;

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
 * @since: 3/24/2022
 * Project_name: Tech-cam
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceiptVoucherRequest {
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
