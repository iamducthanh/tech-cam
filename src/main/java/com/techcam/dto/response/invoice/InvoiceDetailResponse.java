package com.techcam.dto.response.invoice;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/26/2022 10:19 PM
 * Project_name : tech-cam
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetailResponse {

    /**
     * id chi tiết nhập hàng
     */
    private String idDetail;

    /**
     * id sản phẩm
     */
    private String productId;

    /**
     * tên sản phẩm
     */
    private String productName;

    /**
     * mã sản phẩm
     */
    private String productCode;

    /**
     * loại sản phẩm
     */
    private String categoryName;

    /**
     * thương hiệu
     */
    private String brandName;

    /**
     * số lượng trên hoá đơn
     */
    private Integer quantity;

    /**
     * số lượng nhập thực tế
     */
    private Integer quantityActual;

    /**
     * giá tiền
     */
    private Double price;

}
