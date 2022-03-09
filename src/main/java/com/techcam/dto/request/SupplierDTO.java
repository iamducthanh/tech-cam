package com.techcam.dto.request;

import lombok.Data;

@Data
public class SupplierDTO {

    private String name;

    private String phoneNumber;

    private String email;

    private String address;

    private String status;

    private String note;
}
