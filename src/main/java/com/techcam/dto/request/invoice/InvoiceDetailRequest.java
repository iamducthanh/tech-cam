package com.techcam.dto.request.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/26/2022 9:56 PM
 * Project_name : tech-cam
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailRequest {

    /**
     * id chi tiết nhập hàng
     */
    private String idDetail;

    /**
     * id sản phẩm
     */
    private String productId;

    /**
     * số lượng trên hoá đơn
     */
    private Integer quantity;

    /**
     * số lượng thực nhận
     */
    private Integer quantityActual;

    /**
     * giá tiền
     */
    private Double price;

}
