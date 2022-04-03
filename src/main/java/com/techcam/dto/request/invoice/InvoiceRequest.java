package com.techcam.dto.request.invoice;

import lombok.*;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/26/2022 9:51 PM
 * Project_name : tech-cam
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InvoiceRequest {

    /**
     * id nhập hàng
     */
    private String invoiceId;

    /**
     * id nhà ucng cấp
     */
    private String supplierId;

    /**
     * người giao hàng
     */
    private String shipper;

    /**
     * mã nhập hàng
     */
    private String invoiceCode;

    /**
     * mã đặt hàng
     */
    private String invoiceOrderId;

    /**
     * trạng thái
     */
    private Boolean status;

    /**
     * giảm giá
     */
    private Long discount;

    /**
     * số tiền đã trả nhà cung cấp
     */
    private Long paid;

    /**
     * ghi chú
     */
    private String note;

    /**
     * chi tiết nhập hàng
     */
    private List<InvoiceDetailRequest> details;

}
