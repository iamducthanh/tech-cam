package com.techcam.dto.response.invoiceOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/27/2022 10:31 AM
 * Project_name : tech-cam
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceOrderResponse {

    private String invoiceOrderId;

    private String invoiceOrderCode;

    private String invoiceSupplierId;

    private String invoiceSupplierName;

    private String orderDate;

    private String orderStaff;

    private String orderDelivery;

    private String status;

    private String note;

    private Date dateInvoice;

    private Integer statusInvoice;

    private Date createDate;

    private List<InvoiceOrderDetailResponse> details;

}
