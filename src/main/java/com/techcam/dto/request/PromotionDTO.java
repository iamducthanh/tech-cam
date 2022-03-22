package com.techcam.dto.request;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class PromotionDTO {

    private String name;

    private Long discount;

    private Date startDate;

    private Date endDate;

    private String description;

    private String typeDiscount;

    private Boolean status;

    private String note;
}
