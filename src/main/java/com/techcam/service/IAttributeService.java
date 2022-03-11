package com.techcam.service;

import com.techcam.dto.response.property.PropertyFixedValue;
import com.techcam.dto.response.property.PropertyResponse;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/6/2022 10:22 AM
 * Project_name : tech-cam
 */

public interface IAttributeService {

    List<PropertyResponse> findAllByCategoryId(String categoryId);

    List<PropertyFixedValue> findAllFixedValueByPropertyId(String propertyId);

}
