package com.techcam.dto.response.property;

import lombok.*;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/1/2022 8:49 PM
 * Project_name : tech-cam
 */

@Getter
@Setter
public class PropertyResponse {

    private String propertyId;

    private String propertyName;

    private List<PropertyFixedValue> fixedValue;

}
