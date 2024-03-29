package com.techcam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static com.techcam.constants.ConstantsErrorCode.ERROR_BLANK;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/6/2022 11:05 AM
 * Project_name : tech-cam
 */

@Getter
@Setter
public class ProductDto {

    @NotNull(message = ERROR_BLANK)
    @NotEmpty(message = ERROR_BLANK)
    @JsonProperty("productName")
    private String productName;

    @NotNull(message = ERROR_BLANK)
    @NotEmpty(message = ERROR_BLANK)
    @JsonProperty("productPrice")
    private String productPrice;

    private Integer productQuantity;

    @NotNull(message = ERROR_BLANK)
    @NotEmpty(message = ERROR_BLANK)
    @JsonProperty("productBrand")
    private String productBrand;

    @NotNull(message = ERROR_BLANK)
    @NotEmpty(message = ERROR_BLANK)
    @JsonProperty("productStatus")
    private String productStatus;

    @NotNull(message = ERROR_BLANK)
    @NotEmpty(message = ERROR_BLANK)
    @JsonProperty("productCategory")
    private String productCategory;

    @NotNull(message = ERROR_BLANK)
    @NotEmpty(message = ERROR_BLANK)
    @JsonProperty("productDescription")
    private String productDescription;

//    @NotNull(message = ERROR_BLANK)
//    @NotEmpty(message = ERROR_BLANK)
//    @JsonProperty("promotion")
    private String promotion;

}
