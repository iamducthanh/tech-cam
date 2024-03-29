package com.techcam.dto.request.staff;

import lombok.Data;

import java.util.Date;

/**
 * DTO response for staff
 *
 * @author DucBV
 * @version : 1.0
 * @since 23.2.2022
 */

@Data
public class StaffEditRequestDTO {

    private String id;

    private String fullName;

    private String phoneNumber;

    private String address;

    private String avatar;

    private Date dateOfBirth;

    private Integer staffCode;

    private String identityNumber;

    private String role;

    private String username;

    private String note;

    private int countLoginFalse;
}
