package com.techcam.dto.request.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/22/2022
 * Project_name: Tech-cam
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CancelOrderRequest {
    private String id;
}
