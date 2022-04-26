package com.techcam.dto.response.brand;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/8/2022 10:27 PM
 * Project_name : tech-cam
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandResponse {

    private String brandId;

    private String brandName;

    private String brandPhone;

    private String brandEmail;

    private String brandAddress;

    private String brandStatus;

    private String brandNote;

    private String brandAvatar;

}
