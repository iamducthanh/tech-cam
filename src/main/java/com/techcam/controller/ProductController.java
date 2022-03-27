package com.techcam.controller;

import com.techcam.dto.response.brand.BrandResponse;
import com.techcam.dto.response.category.CategoryResponse;
import com.techcam.dto.response.product.ProductPropertyResponse;
import com.techcam.dto.response.product.ProductResponse;
import com.techcam.dto.response.property.PropertyResponse;
import com.techcam.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/1/2022 8:38 PM
 * Project_name : tech-cam
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final IProductService productService;

    private final IProductPropertyService productPropertyService;

    private final IAttributeService attributeService;

    private final IBrandService brandService;

    private final ICategoryService categoryService;

    @GetMapping
    public String productManager(Model model) {
        List<ProductResponse> lstProducts = productService.getAllProduct();
        for (ProductResponse x : lstProducts) {
            x.setProperties(productService.findAllPropertyByProductId(x.getProductId()));
        }
        List<BrandResponse> lstBrands = brandService.getAllBrand();
        List<CategoryResponse> lstCategories = categoryService.getAllCategory();
        model.addAttribute("lstProducts", lstProducts);
        model.addAttribute("lstBrands", lstBrands);
        model.addAttribute("lstCategories", lstCategories);
        return "/views/product/index";
    }

    @GetMapping(value = "/property/add", params = "category")
    public String productProperty(Model model, @RequestParam("category") String categoryId) {
        List<PropertyResponse> lstPropertyResponses = attributeService.findAllByCategoryId(categoryId);
        for (PropertyResponse x : lstPropertyResponses) {
            x.setFixedValue(attributeService.findAllFixedValueByPropertyId(x.getPropertyId()));
        }
        model.addAttribute("lstProperty", lstPropertyResponses);
        return "/component/product/property";
    }

    @GetMapping(value = "/property/edit", params = {"category", "product"})
    public String productProperty(Model model, @RequestParam("category") String categoryId, @RequestParam("product") String productId) {
        List<ProductPropertyResponse> lstProductPropertyResponses = productService.findAllPropertyByProductId(productId);
        List<PropertyResponse> lstFindAllByCategoryId = attributeService.findAllByCategoryId(categoryId);
        lstFindAllByCategoryId.forEach(x -> x.setFixedValue(attributeService.findAllFixedValueByPropertyId(x.getPropertyId())));
        for (PropertyResponse x : lstFindAllByCategoryId) {
            // cờ đánh dấu sản phẩm đã có thuộc tính này chưa?
            boolean flag = false;
            for (ProductPropertyResponse s : lstProductPropertyResponses) {
                if (s.getPropertyId().equals(x.getPropertyId())) {
                    s.setFixedValue(x.getFixedValue());
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                ProductPropertyResponse z = new ProductPropertyResponse();
                z.setPropertyId(x.getPropertyId());
                z.setPropertyName(x.getPropertyName());
                z.setFixedValue(x.getFixedValue());
                lstProductPropertyResponses.add(z);
            }
        }
        if (lstFindAllByCategoryId.isEmpty()) {
            lstProductPropertyResponses = new ArrayList<>();
        }
        for (ProductPropertyResponse x : lstProductPropertyResponses) {
            x.setFixedValue(attributeService.findAllFixedValueByPropertyId(x.getPropertyId()));
        }
        model.addAttribute("lstProperty", lstProductPropertyResponses);
        return "/component/product/propertyEdit";
    }

}
