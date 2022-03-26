package com.techcam.dto.response.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/24/2022
 * Project_name: Tech-cam
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VNPAYResponse {
    private String code;
    private String message;
    private String data;
}