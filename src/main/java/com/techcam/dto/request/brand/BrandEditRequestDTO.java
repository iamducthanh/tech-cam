package com.techcam.dto.request.brand;

import lombok.Data;

@Data
public class BrandEditRequestDTO {
    private String id;

    private String name;

    private String phone;

    private String email;

    private String address;

    private String status;

    private String note;

    private String avatar;
}
