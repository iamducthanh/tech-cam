package com.techcam.api;

import com.techcam.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 *
 * Description : receive and send all requests or responses about staff
 */
@RestController
@RequestMapping("/api")
public class StaffAPI {

    @Autowired
    private IStaffService staffService;
}
