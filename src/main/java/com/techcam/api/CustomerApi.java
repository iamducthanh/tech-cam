package com.techcam.api;

import com.techcam.dto.request.Customer.CustomerRequest;
import com.techcam.dto.response.Customer.CustomerInfoResponse;
import com.techcam.dto.response.Customer.CustomerResponse;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @PostMapping("/registration-customer")
    CustomerResponse postSaveCustomer(@Valid  @RequestBody CustomerRequest customerRequest , BindingResult bindingResult);
    @PostMapping("/update-customer")
    CustomerResponse postUpdateCustomer(@Valid @RequestBody CustomerRequest customerRequest, BindingResult bindingResult);
    @DeleteMapping("delete-customer/{id}")
    CustomerResponse deleteCustomer(@PathVariable String id);

}