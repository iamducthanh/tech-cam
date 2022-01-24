package com.techcam.api;

import com.techcam.dto.request.CustomerDto;
import com.techcam.exception.ErrorMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author: GMO_DuyDV
 * @version: 1.0
 * @since: 1/23/2022
 * Project_name: GMO_QuanLyTaiSan
 */

@RestController
@RequestMapping("api/v1/customer")
public interface CustomerApi {
    @PostMapping("save")
    CustomerDto postSaveCustomer();
    @GetMapping
    ErrorMessage test();

}
