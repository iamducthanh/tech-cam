package com.techcam.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SupplierDTO {

    private String name;

    private String phoneNumber;

    private String email;

    private String address;

    private String status;

    private String note;
}
