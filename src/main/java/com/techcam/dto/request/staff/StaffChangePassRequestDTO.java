package com.techcam.dto.request.staff;

import lombok.Data;

/**
 * DTO response for staff
 *
 * @author DucBV
 * @version : 1.0
 * @since 23.2.2022
 */

@Data
public class StaffChangePassRequestDTO {

    private String id;

    private String password;
}
