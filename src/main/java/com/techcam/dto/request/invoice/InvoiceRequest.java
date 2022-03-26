package com.techcam.dto.request.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
     * mã nhập hàng
     */
    private String invoiceCode;

    /**
     * mã đặt hàng
     */
    private String orderInvoiceCode;

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
