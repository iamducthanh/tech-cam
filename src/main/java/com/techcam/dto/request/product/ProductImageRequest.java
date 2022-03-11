package com.techcam.dto.request.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/3/2022 5:04 PM
 * Project_name : tech-cam
 */

@Getter
@Setter
public class ProductImageRequest {

    @JsonProperty("imageId")
    private String imageId;

    @JsonProperty("imageUrl")
    private String imageUrl;

}
