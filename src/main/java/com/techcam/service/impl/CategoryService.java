package com.techcam.service.impl;

import com.techcam.constants.ConstantsErrorCode;
import com.techcam.dto.request.category.AttributeReqDto;
import com.techcam.dto.request.category.CategoryReqDto;
import com.techcam.dto.response.CategoryDto;
import com.techcam.dto.response.category.CategoryResponse;
import com.techcam.entity.AttributeEntity;
import com.techcam.entity.AttributeFixedValueEntity;
import com.techcam.entity.CategoryEntity;
import com.techcam.exception.TechCamExp;
import com.techcam.repo.IAttributeFixedValueRepo;
import com.techcam.repo.IAttributeRepo;
import com.techcam.repo.ICategoryRepo;
import com.techcam.service.ICategoryService;
import com.techcam.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final IAttributeRepo attributeRepo;
    private final IAttributeFixedValueRepo attributeFixedValueRepo;
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
        if(categoryDto.getCategoryName().trim().length() == 0){
            throw new TechCamExp(ConstantsErrorCode.CATEGORY_NAME);
        } else {
            List<CategoryEntity> categoryEntity = categoryRepo.findByNameAndParent(categoryDto.getCategoryName(), categoryDto.getParentId());
            if(!categoryEntity.isEmpty()) throw new TechCamExp(ConstantsErrorCode.CATEGORY_NAME_EXIST);
        }

        CategoryEntity categoryEntity = CategoryEntity.builder()
                .id(UUID.randomUUID().toString())
                .name(categoryDto.getCategoryName())
                .parentId(categoryDto.getParentId())
                .status("")
                .createDate(new Date())
                .modifierDate(new Date())
                .createBy((String) sessionUtil.getObject("username"))
                .modifierBy((String) sessionUtil.getObject("username"))
                .deleteFlag(false)
                .build();

        List<AttributeEntity> attributes = new ArrayList<>();
        List<AttributeFixedValueEntity> attributeFixvalues = new ArrayList<>();

        List<String> atbNameExist = new ArrayList<>();

        categoryDto.getAttributes().forEach(o -> {
            if(atbNameExist.contains(o.getName())){
                throw new TechCamExp(ConstantsErrorCode.ATB_NAME);
            }
            atbNameExist.add(o.getName());
            String id = UUID.randomUUID().toString();
            attributes.add(AttributeEntity.builder()
                    .id(id)
                    .attributeName(o.getName())
                    .categoryId(categoryEntity.getId())
                    .status("1")
                    .createDate(new Date())
                    .modifierDate(new Date())
                    .createBy((String) sessionUtil.getObject("username"))
                    .modifierBy((String) sessionUtil.getObject("username"))
                    .deleteFlag(false)
                    .build());

            if(!o.getValue().isEmpty()){
                System.out.println(o.getValue());
                List<String> values = new ArrayList<>();
                String props = o.getValue();
                while (props.contains(";")) {
                    values.add(props.substring(0, props.indexOf(";")));
                    props = props.substring(props.indexOf(";") + 1, props.length());
                }
                values.add(props);
                values.forEach(a -> {
                    attributeFixvalues.add(AttributeFixedValueEntity.builder()
                            .id(UUID.randomUUID().toString())
                            .attributeId(id)
                            .attributeFixedVal(a.trim())
                            .status("1")
                            .note("1")
                            .createDate(new Date())
                            .modifierDate(new Date())
                            .createBy((String) sessionUtil.getObject("username"))
                            .modifierBy((String) sessionUtil.getObject("username"))
                            .deleteFlag(false)
                            .build());
                });

            }
        });

        categoryRepo.save(categoryEntity);
        attributeRepo.saveAll(attributes);
        if(!attributeFixvalues.isEmpty()){
            attributeFixedValueRepo.saveAll(attributeFixvalues);
        }

    }

    @Override
    public void deleteCategory(CategoryEntity categoryEntity) {
        categoryRepo.save(categoryEntity);
    }

    @Override
    public List<AttributeReqDto> findAllAttribute(String categoryId) {
        List<AttributeReqDto> attributeReq = new ArrayList<>();
        List<AttributeEntity> attributes = attributeRepo.findAllByCategoryIdAndDeleteFlagIsFalse(categoryId);

        if(!attributes.isEmpty()){
            attributes.forEach(o -> {
                AttributeFixedValueEntity attributeFixedValueEntity = attributeFixedValueRepo.findByAttributeId(o.getId());
                String valueDefault = "";
                if(attributeFixedValueEntity != null){
                    valueDefault = attributeFixedValueEntity.getAttributeFixedVal();
                }
                attributeReq.add(AttributeReqDto.builder()
                        .name(o.getAttributeName())
                        .value(valueDefault)
                        .build());
            });
        }
        return attributeReq;
    }

}
