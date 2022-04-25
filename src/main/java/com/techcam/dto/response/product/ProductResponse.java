package com.techcam.dto.response.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techcam.dto.ProductDto;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

import static com.techcam.constants.ConstantsErrorCode.ERROR_BLANK;

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
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse extends ProductDto {

    private String productId;

    private String productCode;

    private String createBy;

    private Date createDate;

    private Date modifierDate;

    private String thumbnail;

    private List<ProductPropertyResponse> properties;

    private List<String> lstImages;


}
