package com.techcam.service.impl;

import com.techcam.dto.response.property.PropertyFixedValue;
import com.techcam.dto.response.property.PropertyResponse;
import com.techcam.entity.AttributeEntity;
import com.techcam.repo.IAttributeRepo;
import com.techcam.service.IAttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/6/2022 10:22 AM
 * Project_name : tech-cam
 */

@Service
@RequiredArgsConstructor
public class AttributeService implements IAttributeService {

    private final IAttributeRepo attributeRepo;

    @Override
    public List<PropertyResponse> findAllByCategoryId(String categoryId) {
        List<AttributeEntity> lst = attributeRepo.findAllByCategoryIdAndDeleteFlagIsFalse(categoryId);
        return lst.stream().map(this::mapToPropertyResponse).collect(Collectors.toList());
    }

    @Override
    public List<PropertyFixedValue> findAllFixedValueByPropertyId(String propertyId) {
        // TODO get list fixed value
        return new ArrayList<>();
    }

    private PropertyResponse mapToPropertyResponse(AttributeEntity s) {
        if (Objects.isNull(s)) return new PropertyResponse();
        PropertyResponse x = new PropertyResponse();
        x.setPropertyId(s.getId());
        x.setPropertyName(s.getAttributeName());
        return x;
    }
}
