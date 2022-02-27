package com.techcam.service;

import com.techcam.dto.request.Customer.CustomerRequest;
import com.techcam.dto.response.Customer.CustomerInfoResponse;
import com.techcam.dto.response.Customer.CustomerServiceResponse;

import java.util.List;

/**
 * Description:
 *
 * @author: POLY_DuyDV
 * @version: 1.0
 * @since: 2/27/2022
 * Project_name: Tech-cam
 */
public interface ICustomerService {
    List<CustomerInfoResponse> getCustomers();

    CustomerInfoResponse getCustomerByEmail(String email);

    CustomerInfoResponse getCustomerByPhoneNumber(String phoneNumber);

    CustomerInfoResponse getCustomerById(String id);

    CustomerServiceResponse saveCustomer(CustomerRequest customerRequest);

    CustomerServiceResponse updateCustomer(CustomerRequest customerRequest, String typeMethod);
}
