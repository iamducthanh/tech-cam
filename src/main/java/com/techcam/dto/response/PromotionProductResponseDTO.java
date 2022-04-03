package com.techcam.dto.response;

import com.techcam.dto.response.product.ProductResponse;
import lombok.Data;

import java.util.List;

@Data
public class PromotionProductResponseDTO {

    private Long id;

    private Long promotionId;

    private Long productId;

    private List<ProductResponse> productResponseList;
}
