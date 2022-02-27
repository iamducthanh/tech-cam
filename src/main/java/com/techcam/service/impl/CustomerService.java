package com.techcam.service.impl;

import com.techcam.dto.request.Customer.CustomerRequest;
import com.techcam.dto.response.Customer.CustomerInfoResponse;
import com.techcam.dto.response.Customer.CustomerServiceResponse;
import com.techcam.entity.CustomerEntity;
import com.techcam.repo.ICustomerRepo;
import com.techcam.service.ICustomerService;
import com.techcam.type.CommonTypeMethod;
import com.techcam.type.CustomerStatus;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Description:
 *
 * @author: POLY_DuyDV
 * @version: 1.0
 * @since: 2/27/2022
 * Project_name: Tech-cam
 */
@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepo customerRepo;
    private ModelMapper modelMapper = new ModelMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    @Override
    public List<CustomerInfoResponse> getCustomers() {
        Type customersType = new TypeToken<List<CustomerInfoResponse>>() {
        }.getType();
        List<CustomerEntity> customerEntities = customerRepo.findAllByStatus(CustomerStatus.ON.name());
        if (CollectionUtils.isEmpty(customerEntities)) {
            return null;
        }
        return modelMapper.map(customerEntities, customersType);
    }

    @Override
    public CustomerInfoResponse getCustomerByEmail(String email) {
        CustomerEntity customerEntity = customerRepo.findByEmailAndStatus(email, CustomerStatus.ON.name());
        if (Objects.isNull(customerEntity)) {
            return null;
        }
        return modelMapper.map(customerEntity, CustomerInfoResponse.class);
    }

    @Override
    public CustomerInfoResponse getCustomerByPhoneNumber(String phoneNumber) {
        CustomerEntity customerEntity = customerRepo.findByPhoneNumberAndStatus(phoneNumber, CustomerStatus.ON.name());
        if (Objects.isNull(customerEntity)) {
            return null;
        }
        return modelMapper.map(customerEntity, CustomerInfoResponse.class);
    }

    @Override
    public CustomerInfoResponse getCustomerById(String id) {
        CustomerEntity customerEntity = customerRepo.findByIdAndStatus(id, CustomerStatus.ON.name());
        if (Objects.isNull(customerEntity)) {
            return null;
        }
        return modelMapper.map(customerEntity, CustomerInfoResponse.class);
    }

    @Override
    public CustomerServiceResponse saveCustomer(CustomerRequest customerRequest) {
        CustomerServiceResponse customerResponse = new CustomerServiceResponse()
                .builder()
                .saved(false)
                .existing(false)
                .build();
        CustomerEntity customerEntity = new CustomerEntity();
        if (Objects.nonNull(getCustomerByEmail(customerRequest.getEmail()))
                || Objects.nonNull(getCustomerByPhoneNumber(customerRequest.getPhoneNumber()))) {
            customerResponse.setExisting(true);
        }
        try {
            customerEntity = modelMapper.map(customerRequest, CustomerEntity.class);
            customerEntity.setId(UUID.randomUUID().toString());
            // todo thiếu  tên người thêm lấy sau.
            customerEntity = customerRepo.save(customerEntity);
            customerResponse.setSaved(true);
            customerResponse.setExisting(false);
            customerResponse.setCustomerInfoResponse(modelMapper.map(customerEntity, CustomerInfoResponse.class));
        } catch (Exception e) {
            LOGGER.error("Save customer fail Exception {}", e);
        }
        return customerResponse;
    }

    @Override
    public CustomerServiceResponse updateCustomer(CustomerRequest customerRequest, String typeMethod) {
        CustomerServiceResponse customerResponse = new CustomerServiceResponse()
                .builder()
                .saved(false)
                .existing(false)
                .build();
        CustomerEntity customerEntity = customerRepo.findByIdAndStatus(customerRequest.getId(), CustomerStatus.ON.name());
        if (Objects.nonNull(customerEntity)) {
            customerResponse.setExisting(true);
            if (StringUtils.equals(typeMethod, CommonTypeMethod.DELETE.name())) {
                customerEntity.setStatus(CustomerStatus.OFF.name());
            }
            if (StringUtils.equals(typeMethod, CommonTypeMethod.UPDATE.name())) {
                customerEntity = modelMapper.map(customerRequest, CustomerEntity.class);
            }
            // todo còn update time chưa cập nhật
            try {
                customerEntity = customerRepo.save(customerEntity);
                customerResponse.setSaved(true);
                customerResponse.setCustomerInfoResponse(modelMapper.map(customerEntity, CustomerInfoResponse.class));
            } catch (Exception e) {
                LOGGER.error("Update Customer fail Exception {} ", e);
            }
        }
        return customerResponse;
    }
}
