package com.techcam.dto.request.brand;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;

@Data
public class BrandAddRequestDTO {

    private String name;

    private String phone;

    private String email;

    private String address;

    private String status;

    private String note;

    private String avatar;
}
