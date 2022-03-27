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
 * @since : 3/27/2022 8:56 PM
 * Project_name : tech-cam
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceOrderDetailResponse {

    private String productId;

    private int productQuantity;

}
