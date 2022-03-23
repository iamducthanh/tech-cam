package com.techcam.service.impl;

import com.techcam.dto.response.CategoryDto;
import com.techcam.dto.response.category.CategoryResponse;
import com.techcam.entity.CategoryEntity;
import com.techcam.repo.ICategoryRepo;
import com.techcam.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<CategoryResponse> getAllCategory() {
        return categoryRepo.findAllByDeleteFlagIsFalse().stream()
                .map(this::mapToCategoryResp)
                .collect(Collectors.toList());
    }

    private <R> CategoryResponse mapToCategoryResp(CategoryEntity categoryEntity) {
        if (categoryEntity == null) return new CategoryResponse();
        return CategoryResponse.builder()
                .categoryId(categoryEntity.getId())
                .categoryName(categoryEntity.getName())
                .build();
    }

    @Override
    public CategoryEntity findById(String id) {
        return categoryRepo.getById(id);
    }

    @Override
    public List<CategoryDto> findAll() {
        List<CategoryDto> categoryDtos = new ArrayList<>();
        List<CategoryEntity> categoryParents = categoryRepo.findCategoryParent();
        categoryParents.forEach(o -> {
            CategoryDto categoryDto = CategoryDto.builder()
                    .categoryName(o.getName())
                    .categoryId(o.getId())
                    .categoryChild(new ArrayList<>())
                    .build();
            getAllChildCategory(categoryDto);
            categoryDtos.add(categoryDto);

        });
        return categoryDtos;
    }

    public void getAllChildCategory(CategoryDto categoryDto){
        List<CategoryEntity> categoryChildrens = findCategoryByParent(categoryDto.getCategoryId());

        if(!categoryChildrens.isEmpty()){
            categoryChildrens.forEach(o1 -> {
                CategoryDto categoryDto1 = CategoryDto.builder()
                        .categoryName(o1.getName())
                        .categoryId(o1.getId())
                        .categoryChild(new ArrayList<>())
                        .build();
                getAllChildCategory(categoryDto1);
                categoryDto.setCategoryChild(categoryDto.getCategoryChild());
                categoryDto.getCategoryChild().add(categoryDto1);
            });
        }
    }

    @Override
    public List<CategoryEntity> findCategoryByParent(String parentId) {
        return categoryRepo.findCategoryByParent(parentId);
    }

}
