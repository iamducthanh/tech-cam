package com.techcam.api;

import com.techcam.dto.request.category.AttributeReqDto;
import com.techcam.dto.request.category.CategoryReqDto;
import com.techcam.dto.response.CategoryDto;
import com.techcam.entity.CategoryEntity;
import com.techcam.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 3/23/2022 10:06 PM
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryApi {
 private final ICategoryService categoryService;

 @PostMapping("")
 public String saveCategory(@RequestBody CategoryReqDto categoryDto){
  System.out.println(categoryDto.toString());
  categoryService.saveCategory(categoryDto);
  return "";
 }

 @PutMapping("")
 public String updateCategory(@RequestBody CategoryDto categoryDto){
  System.out.println(categoryDto.toString());
  categoryService.updateCategory(categoryDto);
  return "";
 }

 @GetMapping("/{categoryId}")
 public CategoryDto getByCategoryId(@PathVariable("categoryId") String categoryId){
  CategoryEntity categoryEntity = categoryService.findById(categoryId);
  List<AttributeReqDto> attributes = categoryService.findAllAttribute(categoryId);
  CategoryDto categoryDto = CategoryDto.builder()
          .categoryId(categoryId)
          .categoryName(categoryEntity.getName())
          .attributes(attributes)
          .build();
  return categoryDto;
 }

 @DeleteMapping("/{categoryId}")
 public void deleteByCategoryId(@PathVariable("categoryId") String categoryId){
  CategoryEntity categoryEntity = categoryService.findById(categoryId);
  categoryEntity.setDeleteFlag(true);
  categoryService.deleteCategory(categoryEntity);
 }
}


