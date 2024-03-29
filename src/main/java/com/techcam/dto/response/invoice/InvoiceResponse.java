package com.techcam.dto.response.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/26/2022 10:16 PM
 * Project_name : tech-cam
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponse {

    private String invoiceId;

    private String invoiceCode;

    /**
     * id nhà cung cấp
     */
    private String supplierId;

    /**
     * tên nhà cungg cấp
     */
    private String supplierName;

    /**
     * id đặt hàng
     */
    private String orderInvoiceId;

    /**
     * mã đặt hàng
     */
    private String orderInvoiceCode;

    private String status;

    /**
     * tổng tiền hàng
     */
    private Long totalMoney;

    /**
     * giảm giá
     */
    private Long discount;

    /**
     * số tiền đã trả = tổng tiền hoá đươn thanh toán nhập hàng
     */
    private Long paid;

    /**
     * ghi chú
     */
    private String note;

    /**
     * người giao hàng
     */
    private String shipper;

    private Date createDate;

    /**
     * chi tiết nhập hàng
     */
    private List<InvoiceDetailResponse> details;

}
