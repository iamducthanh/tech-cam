package com.techcam.dto.response.promotion;

import com.techcam.dto.response.product.ProductResponseDTO;
import lombok.Data;

@Data
public class PromotionProductResponseDTO {

    private Long id;

    private Long promotionId;

    private Long productId;

    private ProductResponseDTO productResponseDTO;
}
