package com.techcam.dto.request.invoiceOrder;

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
 * @since : 4/3/2022 11:06 PM
 * Project_name : tech-cam
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceOrderRequest {

    private String id;

    private String code;

    private String date;

    private String supplierId;

    private String note;

    private List<InvoiceOrderDetailRequest> details;

}
