package com.techcam.controller;

import com.techcam.dto.response.product.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping
    public String productManager(Model model) {
        List<ProductResponse> lstProducts = new ArrayList<>();
        model.addAttribute("lstProducts", lstProducts);
        return "/views/product/index";
    }

}
