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
 * @since : 3/1/2022 8:45 PM
 * Project_name : tech-cam
 */

@Getter
@Setter
@Builder
public class ProductDetailResponse {

    private String id;

    private String price;

    private String quantity;

    private List<ProductProperty> properties;

}
