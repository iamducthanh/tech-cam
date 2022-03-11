package com.techcam.dto.response.product;

import com.techcam.dto.response.property.PropertyResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/6/2022 9:15 AM
 * Project_name : tech-cam
 */

@Getter
@Setter
public class ProductPropertyResponse extends PropertyResponse {

    private String productPropertyId;

    private String fixedValueId;

    private String inputValue;

}
