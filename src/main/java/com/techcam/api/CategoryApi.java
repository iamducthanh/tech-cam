package com.techcam.api;

import com.techcam.constants.ConstantsErrorCode;
import com.techcam.dto.request.CategoryReqDto;
import com.techcam.dto.response.CategoryDto;
import com.techcam.entity.CategoryEntity;
import com.techcam.exception.TechCamExp;
import com.techcam.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

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
  categoryService.saveCategory(categoryDto);
  return "";
 }

 @GetMapping("/{categoryId}")
 public CategoryDto getByCategoryId(@PathVariable("categoryId") String categoryId){
  CategoryEntity categoryEntity = categoryService.findById(categoryId);
  CategoryDto categoryDto = CategoryDto.builder()
          .categoryName(categoryEntity.getName())
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


