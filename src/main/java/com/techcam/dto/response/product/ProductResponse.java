package com.techcam.dto.response.product;

import com.techcam.dto.ProductDto;
import lombok.*;

import java.util.Date;
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
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse extends ProductDto {

    private String productId;

    private String createBy;

    private Date createDate;

    private Date modifierDate;

    private List<ProductPropertyResponse> properties;

}
