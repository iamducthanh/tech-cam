package com.techcam.dto.response.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/1/2022 8:43 PM
 * Project_name : tech-cam
 */

@Getter
@Setter
@Builder
public class ProductResponse {

    private String id;

    private String productCode;

    private String productName;

    private String description;

    private List<ProductDetailResponse> details;

}
