package com.techcam.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.techcam.util.ConstantsErrorCode.*;
import static com.techcam.util.ContainsFormat.*;

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
    @Pattern(regexp = REGIEX_ENGLISH,
            message = PRODUCT_CODE_ERROR_REGEX)
    @JsonProperty("productCode")
    private String productCode;

    @NotNull(message = ERROR_BLANK)
    @NotEmpty(message = ERROR_BLANK)
    @Pattern(regexp = REGEX_VIETNAM,
            message = PRODUCT_NAME_ERROR)
    @JsonProperty("productName")
    private String productName;

    @NotNull(message = ERROR_BLANK)
    @NotEmpty(message = ERROR_BLANK)
    @Pattern(regexp = REGEX_NUMBER,
            message = PRODUCT_MONEY_ERROR)
    @JsonProperty("productPrice")
    private String productPrice;

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

}
