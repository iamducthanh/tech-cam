package com.techcam.dto.request;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
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
public class StaffAddRequestDTO {

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
}
