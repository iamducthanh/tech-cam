package com.techcam.dto.response.invoiceOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}
