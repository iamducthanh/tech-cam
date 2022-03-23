package com.techcam.service.impl;

import com.techcam.constants.ConstantsErrorCode;
import com.techcam.dto.request.CategoryReqDto;
import com.techcam.dto.response.CategoryDto;
import com.techcam.dto.response.category.CategoryResponse;
import com.techcam.entity.CategoryEntity;
import com.techcam.entity.StaffEntity;
import com.techcam.exception.TechCamExp;
import com.techcam.repo.ICategoryRepo;
import com.techcam.service.ICategoryService;
import com.techcam.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
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
    private final SessionUtil sessionUtil;

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

    @Override
    public void saveCategory(CategoryReqDto categoryDto) {
        if(categoryDto.getParentId().isEmpty()) categoryDto.setParentId(null);
        if(categoryDto.getCategoryId().isEmpty()) {
            categoryDto.setCategoryId(UUID.randomUUID().toString());
        }
        if(categoryDto.getCategoryName().trim().length() == 0){
            throw new TechCamExp(ConstantsErrorCode.CATEGORY_NAME);
        } else {
            System.out.println(categoryDto.getCategoryName());
            System.out.println(categoryDto.getParentId());
            List<CategoryEntity> categoryEntity = categoryRepo.findByNameAndParent(categoryDto.getCategoryName(), categoryDto.getParentId());
            if(!categoryEntity.isEmpty()) throw new TechCamExp(ConstantsErrorCode.CATEGORY_NAME_EXIST);
        }

        CategoryEntity categoryEntity = CategoryEntity.builder()
                .id(categoryDto.getCategoryId())
                .name(categoryDto.getCategoryName())
                .parentId(categoryDto.getParentId())
                .status("")
                .createDate(new Date())
                .modifierDate(new Date())
                .createBy((String) sessionUtil.getObject("username"))
                .modifierBy((String) sessionUtil.getObject("username"))
                .deleteFlag(false)
                .build();
        categoryRepo.save(categoryEntity);
    }

}
