package com.techcam.dto.request.promotion;

import lombok.Data;
import org.mapstruct.Mapper;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class PromotionRequestDTO {

    private String name;

    private Long discount;

    private Date startDate;

    private Date endDate;

    private String description;

    private String typeDiscount;

    private Boolean status;

    private String note;

    private List<String> categoryIds;

    private List<String> productIds;

    private Boolean isAllProduct;
}
