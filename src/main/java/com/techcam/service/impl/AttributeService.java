package com.techcam.service.impl;

import com.techcam.dto.response.property.PropertyFixedValue;
import com.techcam.dto.response.property.PropertyResponse;
import com.techcam.entity.AttributeEntity;
import com.techcam.entity.AttributeFixedValueEntity;
import com.techcam.repo.IAttributeFixedValueRepo;
import com.techcam.repo.IAttributeRepo;
import com.techcam.service.IAttributeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    private final IAttributeFixedValueRepo attributeFixedValueRepo;

    @Override
    public List<PropertyResponse> findAllByCategoryId(String categoryId) {
        List<AttributeEntity> lst = attributeRepo.findAllByCategoryIdAndDeleteFlagIsFalse(categoryId);
        return lst.stream().map(this::mapToPropertyResponse).collect(Collectors.toList());
    }

    @Override
    public List<PropertyFixedValue> findAllFixedValueByPropertyId(String propertyId) {
        return attributeFixedValueRepo.findAllByAttributeIdAndDeleteFlagIsFalse(propertyId)
                .stream().map(this::mapToPropertyFixedResponse).collect(Collectors.toList());
    }

    private <R> PropertyFixedValue mapToPropertyFixedResponse(AttributeFixedValueEntity x) {
        if (Objects.isNull(x)) return PropertyFixedValue.builder().build();
        return PropertyFixedValue.builder()
                .fixedId(x.getId())
                .fixedValue(x.getAttributeFixedVal())
                .build();
    }

    private PropertyResponse mapToPropertyResponse(AttributeEntity s) {
        if (Objects.isNull(s)) return new PropertyResponse();
        PropertyResponse x = new PropertyResponse();
        x.setPropertyId(s.getId());
        x.setPropertyName(s.getAttributeName());
        return x;
    }
}
