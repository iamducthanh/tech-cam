package com.techcam.dto.response.product;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/1/2022 8:49 PM
 * Project_name : tech-cam
 */

@Getter
@Setter
@Builder
public class ProductProperty {

    private String id;

    private String attributeId;

    private String value;

}
