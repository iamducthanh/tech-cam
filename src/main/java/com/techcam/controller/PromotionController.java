package com.techcam.controller;

import com.techcam.dto.response.category.CategoryResponse;
import com.techcam.dto.response.product.ProductResponse;
import com.techcam.dto.response.PromotionResponseDTO;
import com.techcam.dto.response.product.ProductResponseDTO;
import com.techcam.service.ICategoryService;
import com.techcam.service.IProductService;
import com.techcam.service.impl.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/promotion")
public class PromotionController {
    @Autowired
    PromotionService promotionService;
    @Autowired
    ICategoryService categoryService;
    @Autowired
    IProductService productService;

    @GetMapping()
    public String getAll(Model model){
        List<PromotionResponseDTO> promotionList = promotionService.getAll();
        model.addAttribute("promotions", promotionList);

        List<CategoryResponse> lstCategory = categoryService.getAllCategory();
        model.addAttribute("categories", lstCategory);

        List<ProductResponseDTO> lstProduct = productService.getAll();
        model.addAttribute("products", lstProduct);

        return "views/promotion/promotion.html";
    }
}
