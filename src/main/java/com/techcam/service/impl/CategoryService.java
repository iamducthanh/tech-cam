package com.techcam.service.impl;

import com.techcam.dto.response.CategoryRespDto;
import com.techcam.repo.ICategoryRepo;
import com.techcam.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * Description :
 */
@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final ICategoryRepo categoryRepo;

    @Override
    public List<CategoryRespDto> getAllCategory() {
        return categoryRepo.findAllByDeleteFlagIsFalse().stream()
                .map(this::mapToCategoryResp)
                .collect(Collectors.toList());
    }

    private <R> CategoryRespDto mapToCategoryResp(CategoryEntity categoryEntity) {
        if (categoryEntity == null) return new CategoryRespDto();
        return CategoryRespDto.builder()
                .id(categoryEntity.getId())
                .categoryName(categoryEntity.getName())
                .build();
    }

}
