package com.techcam.dto.response;

import lombok.Data;

@Data
public class SupplierResponseDTO {
    private String name;

    private String phoneNumber;

    private String email;

    private String address;

    private String status;

    private String note;
}
