package com.techcam.controller;

import com.techcam.dto.response.SupplierResponseDTO;
import com.techcam.service.impl.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    @GetMapping("")
    public String index(Model model) {

        List<SupplierResponseDTO> supplierResponseDTOS = supplierService.findAllByDeleteFlagFalse();
        model.addAttribute("suppliers", supplierResponseDTOS);

        return "views/supplier/supplier";
    }
}
