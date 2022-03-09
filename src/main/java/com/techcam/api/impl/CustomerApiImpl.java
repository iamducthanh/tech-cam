package com.techcam.api.impl;

import com.techcam.api.CustomerApi;
import com.techcam.dto.request.Customer.CustomerRequest;
import com.techcam.dto.response.Customer.CustomerInfoResponse;
import com.techcam.dto.response.Customer.CustomerResponse;
import com.techcam.dto.response.Customer.CustomerServiceResponse;
import com.techcam.exception.TechCamExp;
import com.techcam.service.ICustomerService;
import com.techcam.type.CommonTypeMethod;
import com.techcam.type.CustomerStatus;
import com.techcam.repo.util.ConstantsErrorCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author: GMO_DuyDV
 * @version: 1.0
 * @since: 1/23/2022
 * Project_name: GMO_QuanLyTaiSan
 */

@RestController
public class CustomerApiImpl implements CustomerApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerApiImpl.class);
    @Autowired
    private ICustomerService customerService;

    @Override
    public List<CustomerInfoResponse> findCustomers(String keyWord) {
        List<CustomerInfoResponse> customerInfoResponses = new ArrayList<>();
        if (StringUtils.isBlank(keyWord)){
            customerInfoResponses= customerService.getCustomers();
        }
        customerInfoResponses= customerService.findCustomers(keyWord);
       return customerInfoResponses;
    }

    @Override
    public CustomerResponse postSaveCustomer(@Valid @RequestBody CustomerRequest customerRequest, BindingResult bindingResult) {
        CustomerResponse customerResponse = new CustomerResponse().builder().status(CustomerStatus.FAILED.name()).build();
        if (bindingResult.hasErrors()) {
            LOGGER.error("Create Customer : Input data is incorrect");
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        CustomerServiceResponse customerServiceResponse = customerService.saveCustomer(customerRequest);
        if (customerServiceResponse.getExisting()) {
            LOGGER.error("Create Customer : Customer exist in Tech-Cam");
            throw new TechCamExp(ConstantsErrorCode.CUSTOMER_EXIST);
        }
        if (customerServiceResponse.getSaved()) {
            customerResponse.setStatus(CustomerStatus.SUCCESS.name());
        }
        return customerResponse;
    }

    @Override
    public CustomerResponse postUpdateCustomer(@Valid @RequestBody CustomerRequest customerRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOGGER.error("Update Customer : Input data is incorrect");
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
        return updateCustomer(customerRequest, CommonTypeMethod.UPDATE.name());
    }


    @Override
    public CustomerResponse deleteCustomer(String id) {
        CustomerRequest customerRequest = new CustomerRequest().builder().id(id).build();
        return updateCustomer(customerRequest, CommonTypeMethod.DELETE.name());
    }

    private CustomerResponse updateCustomer(CustomerRequest customerRequest, String typeMethod) {
        CustomerResponse customerResponse = new CustomerResponse().builder().status(CustomerStatus.FAILED.name()).build();
        CustomerServiceResponse customerServiceResponse = customerService.updateCustomer(customerRequest, typeMethod);
        if (!customerServiceResponse.getExisting()) {
            LOGGER.error("Update Customer: Customer not exist");
            throw new TechCamExp(ConstantsErrorCode.CUSTOMER_NOT_EXIST);
        }
        if (StringUtils.equals(typeMethod, CommonTypeMethod.UPDATE.name())) {
            if (customerServiceResponse.getEmailExisting()) {
                LOGGER.error("Update Customer: email exist");
                throw new TechCamExp(ConstantsErrorCode.EMAIL_EXIST);
            }
            if (customerServiceResponse.getPhoneNumberExisting()) {
                LOGGER.error("Update Customer: phone number exist");
                throw new TechCamExp(ConstantsErrorCode.PHONE_NUMBER_EXIST);
            }
        }
        if (customerServiceResponse.getSaved()) {
            customerResponse.setStatus(CustomerStatus.SUCCESS.name());
        }
        return customerResponse;
    }

}
