package com.techcam.dto.response;

import com.techcam.dto.response.product.ProductResponseDTO;
import com.techcam.entity.CategoryEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PromotionResponseDTO {

    private Long id;

    private String name;

    private Long discount;

    private Date startDate;

    private Date endDate;

    private String description;

    private String typeDiscount;

    private Boolean status;

    private String note;

    List<ProductResponseDTO> products;
}
