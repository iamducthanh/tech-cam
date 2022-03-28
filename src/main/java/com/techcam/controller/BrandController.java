package com.techcam.controller;


import com.techcam.dto.response.brand.BrandResponse;
import com.techcam.service.IBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private IBrandService iBrandService;


    @GetMapping
    public String index(Model model) {

        List<BrandResponse> brands = iBrandService.getAllBrand();
        for (BrandResponse b: brands
             ) {
            System.out.println(b.getBrandId());
        }
        model.addAttribute("brands", brands);
        return "views/brand/brand";
    }
}
