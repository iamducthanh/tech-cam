package com.techcam.controller;

import com.techcam.dto.response.CategoryDto;
import com.techcam.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 3/21/2022 10:55 PM
 */

@Controller
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final ICategoryService categoryService;
    @GetMapping("/category")
    public String categoryPage(Model model){
        List<CategoryDto> categoryDtoList = categoryService.findAll();
        model.addAttribute("categories", categoryDtoList);
        return "views/category/category";
    }
}
