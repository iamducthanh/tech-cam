package com.techcam.controller;

import com.techcam.dto.response.StaffResponseDTO;
import com.techcam.service.IStaffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * Description :
 */
@Slf4j
@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private IStaffService staffService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public String index(Model model) {
        List<StaffResponseDTO> staffs = staffService.findAllByDeleteFlagIsFalse();
        model.addAttribute("staffs", staffs);

        return "views/staff/001_Staff";
    }
}
