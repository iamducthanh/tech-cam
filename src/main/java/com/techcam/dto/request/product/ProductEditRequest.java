package com.techcam.dto.request.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techcam.dto.ProductDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/3/2022 5:00 PM
 * Project_name : tech-cam
 */

@Getter
@Setter
public class ProductEditRequest extends ProductDto {

    @JsonProperty("productId")
    private String productId;

    @JsonProperty("productImages")
    private List<String> productImages;

    @JsonProperty("productProperties")
    private List<ProductPropertyRequest> productProperties;

}
