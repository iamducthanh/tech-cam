package com.techcam.dto.response.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Description:
 *
 * @author: POLY_DuyDV
 * @version: 1.0
 * @since: 2/27/2022
 * Project_name: Tech-cam
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerInfoResponse {
    private String id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String address;
    private LocalDate dateOfBirth;
    private LocalDateTime createDate;
}
