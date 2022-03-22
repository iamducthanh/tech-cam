package com.techcam.dto.response;

import lombok.Data;

import java.util.Date;

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
}
