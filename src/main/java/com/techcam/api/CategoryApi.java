package com.techcam.api;

import com.techcam.constants.ConstantsErrorCode;
import com.techcam.dto.request.CategoryReqDto;
import com.techcam.exception.TechCamExp;
import com.techcam.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 3/23/2022 10:06 PM
 */
@RestController
@RequiredArgsConstructor
public class CategoryApi {
 private final ICategoryService categoryService;

 @PostMapping("/category")
 public String saveCategory(@RequestBody CategoryReqDto categoryDto){
  categoryService.saveCategory(categoryDto);
  return "";
 }
}
