package com.techcam.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/26/2022 4:17 PM
 * Project_name : tech-cam
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/invoice")
public class ImportInvoceController {



    @GetMapping
    public String homeInvoice(Model model) {
        model.addAttribute("lstInvoice", null);
        return "views/import-invoice/008_import_invoice";
    }

}
