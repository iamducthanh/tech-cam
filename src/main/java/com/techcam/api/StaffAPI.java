package com.techcam.api;

import com.techcam.dto.request.StaffAddRequestDTO;
import com.techcam.dto.response.StaffResponseDTO;
import com.techcam.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * <p>
 * Description : receive and send all requests or responses about staff
 */
@RestController
@RequestMapping("/api/staff")
public class StaffAPI {

    @Autowired
    private IStaffService staffService;

    @GetMapping("/{id}")
    public StaffResponseDTO findById(@PathVariable("id") String id) {
        return staffService.findById(id);
    }

//    @PostMapping
//    public StaffAddRequestDTO addStaff(StaffAddRequestDTO staff) {
//        staffService
//    }
}
