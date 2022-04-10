package com.techcam.dto.response.product;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class ProductResponseDTO {

    private String id;

    private String name;

    private String categoryId;

    private String brandId;

    private String productCode;

    private int quantity;

    private long price;

    private String detail;

    private String description;

    private String status;

    private String note;

    private String thumbnail;
}
