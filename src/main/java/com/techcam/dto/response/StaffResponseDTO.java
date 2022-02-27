package com.techcam.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * DTO response for staff
 *
 * @author DucBV
 * @version : 1.0
 * @since 23.2.2022
 */

@Data
public class StaffResponseDTO {

    private String id;

    private String fullName;

    private String phoneNumber;

    private String email;

    private String address;

    private Integer staffCode;

    private Date dateOfBirth;

    private String avatar;

    private String role;

    private String note;

    private String status;

    private LocalDateTime createDate;
}
