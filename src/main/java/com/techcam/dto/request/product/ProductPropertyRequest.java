package com.techcam.dto.request.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/3/2022 5:06 PM
 * Project_name : tech-cam
 */

@Getter
@Setter
public class ProductPropertyRequest {

    @JsonProperty("productPropertyId")
    private String productPropertyId;

    @JsonProperty("propertyId")
    private String propertyId;

    @JsonProperty("fixedValueId")
    private String fixedValueId;

    @JsonProperty("inputValue")
    private String inputValue;

}
