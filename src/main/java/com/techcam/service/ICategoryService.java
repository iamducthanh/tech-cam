package com.techcam.service;

import com.techcam.dto.request.category.AttributeReqDto;
import com.techcam.dto.request.category.CategoryReqDto;
import com.techcam.dto.response.CategoryDto;
import com.techcam.dto.response.category.CategoryResponse;
import com.techcam.entity.CategoryEntity;

import java.util.List;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * Description :
 */
public interface ICategoryService {

    List<CategoryResponse> getAllCategory();
    CategoryEntity findById(String id);
    List<CategoryDto> findAll();
    List<CategoryEntity> findCategoryByParent(String parentId);
    void saveCategory(CategoryReqDto categoryDto);
    void deleteCategory(CategoryEntity categoryEntity);
    List<AttributeReqDto> findAllAttribute(String categoryId);
}
