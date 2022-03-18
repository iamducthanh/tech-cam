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
import java.sql.Timestamp;
import java.util.*;

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
    private final ModelMapper MODEL_MAPPER = new ModelMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
    private static final String NUMBER = "[0-9]*";


    @Override
    public List<CustomerInfoResponse> getCustomers() {
        List<CustomerInfoResponse> customerInfoResponses = new ArrayList<>();
        Type customersType = new TypeToken<List<CustomerInfoResponse>>() {
        }.getType();
        List<CustomerEntity> customerEntities = customerRepo.findAllByStatus(CustomerStatus.ON.name());
        if (!CollectionUtils.isEmpty(customerEntities)) {
            customerInfoResponses = MODEL_MAPPER.map(customerEntities, customersType);
        }
        return customerInfoResponses;
    }

    @Override
    public List<CustomerInfoResponse> getCustomersByCreateDate(Date startDate, Date endDate) {
        System.out.println("1111111111111111");
        List<CustomerInfoResponse> customerInfoResponses = new ArrayList<>();
        Type customersType = new TypeToken<List<CustomerInfoResponse>>() {
        }.getType();
        List<CustomerEntity> customerEntities = customerRepo.findAllByCreateDateBetweenAndStatus(startDate,endDate,CustomerStatus.ON.name());
        if(!CollectionUtils.isEmpty(customerEntities)){
            customerInfoResponses = MODEL_MAPPER.map(customerEntities,customersType);
        }
        return customerInfoResponses;
    }

    @Override
    public List<CustomerInfoResponse> findCustomers(String keyWord) {
        List<CustomerEntity> customerEntities;
        List<CustomerInfoResponse> customerInfoResponses = new ArrayList<>();
        Type customersType = new TypeToken<List<CustomerInfoResponse>>() {
        }.getType();
        if (!keyWord.matches(NUMBER)) {
            customerEntities = customerRepo.findAllByFullNameStartingWithAndStatus(keyWord, CustomerStatus.ON.name());
        } else {
            customerEntities = customerRepo.findAllByPhoneNumberStartsWithAndStatus(keyWord, CustomerStatus.ON.name());
        }
        if (!CollectionUtils.isEmpty(customerEntities)) {
            customerInfoResponses = MODEL_MAPPER.map(customerEntities, customersType);
        }
        return customerInfoResponses;
    }

    @Override
    public CustomerInfoResponse getCustomerByEmail(String email) {
        CustomerEntity customerEntity = customerRepo.findByEmailAndStatus(email, CustomerStatus.ON.name());
        if (Objects.isNull(customerEntity)) {
            return null;
        }
        return  MODEL_MAPPER.map(customerEntity, CustomerInfoResponse.class);
    }

    @Override
    public CustomerInfoResponse getCustomerByPhoneNumber(String phoneNumber) {
        CustomerEntity customerEntity = customerRepo.findByPhoneNumberAndStatus(phoneNumber, CustomerStatus.ON.name());
        if (Objects.isNull(customerEntity)) {
            return null;
        }
        return  MODEL_MAPPER.map(customerEntity, CustomerInfoResponse.class);
    }

    @Override
    public CustomerInfoResponse getCustomerById(String id) {
        CustomerEntity customerEntity = customerRepo.findByIdAndStatus(id, CustomerStatus.ON.name());
        if (Objects.isNull(customerEntity)) {
            return null;
        }
        return  MODEL_MAPPER.map(customerEntity, CustomerInfoResponse.class);
    }

    @Override
    public CustomerServiceResponse saveCustomer(CustomerRequest customerRequest) {
        CustomerServiceResponse customerResponse = CustomerServiceResponse
                .builder()
                .saved(false)
                .existing(false)
                .build();
        new CustomerEntity();
        CustomerEntity customerEntity;
        if (Objects.nonNull(getCustomerByEmail(customerRequest.getEmail()))
                || Objects.nonNull(getCustomerByPhoneNumber(customerRequest.getPhoneNumber()))) {
            customerResponse.setExisting(true);
        } else {
            try {
                customerEntity = MODEL_MAPPER.map(customerRequest, CustomerEntity.class);
                customerEntity.setId(UUID.randomUUID().toString());
                customerEntity.setStatus(CustomerStatus.ON.name());
                customerEntity.setCreateDate(new Date());
                // todo thiếu  tên người thêm lấy sau.
                customerEntity = customerRepo.save(customerEntity);
                customerResponse.setSaved(true);
                customerResponse.setExisting(false);
                customerResponse.setCustomerInfoResponse(MODEL_MAPPER.map(customerEntity, CustomerInfoResponse.class));
            } catch (Exception e) {
                LOGGER.error("Save customer fail Exception {}", e);
            }
        }
        return customerResponse;
    }

    @Override
    public CustomerServiceResponse updateCustomer(CustomerRequest customerRequest, String typeMethod) {
        CustomerServiceResponse customerResponse = CustomerServiceResponse
                .builder()
                .saved(false)
                .existing(false)
                .emailExisting(false)
                .phoneNumberExisting(false)
                .build();
        CustomerEntity customerEntity = customerRepo.findByIdAndStatus(customerRequest.getId(), CustomerStatus.ON.name());
        if (Objects.nonNull(customerEntity)) {
            customerResponse.setExisting(true);
            if (StringUtils.equals(typeMethod, CommonTypeMethod.DELETE.name())) {
                customerEntity.setStatus(CustomerStatus.OFF.name());
            }
            if (StringUtils.equals(typeMethod, CommonTypeMethod.UPDATE.name())) {
                if (checkEmailCustomerUpdate(customerRequest, customerEntity)) {
                    customerResponse.setEmailExisting(true);
                    return customerResponse;
                }
                if (checkPhoneNumberCustomerUpdate(customerRequest, customerEntity)) {
                    customerResponse.setPhoneNumberExisting(true);
                    return customerResponse;
                }
                customerEntity.setPhoneNumber(customerRequest.getPhoneNumber());
                customerEntity.setEmail(customerRequest.getEmail());
                customerEntity.setAddress(customerRequest.getAddress());
                customerEntity.setDateOfBirth(customerRequest.getDateOfBirth());
                customerEntity.setFullName(customerRequest.getFullName());
//                customerEntity.setModifierDate(new Timestamp(new Date().getTime()));

                // todo còn update time chưa cập nhật người sửa người tạo
            }
            try {
                customerEntity = customerRepo.save(customerEntity);
                customerResponse.setSaved(true);
                customerResponse.setCustomerInfoResponse(MODEL_MAPPER.map(customerEntity, CustomerInfoResponse.class));
            } catch (Exception e) {
                LOGGER.error("Update Customer fail Exception {} ", e);
            }
        }
        return customerResponse;
    }

    private boolean checkEmailCustomerUpdate(CustomerRequest customerRequest, CustomerEntity customerEntity) {
        return !StringUtils.equalsIgnoreCase(customerEntity.getEmail(), customerRequest.getEmail())
                && Objects.nonNull(getCustomerByEmail(customerRequest.getEmail()));
    }

    private boolean checkPhoneNumberCustomerUpdate(CustomerRequest customerRequest, CustomerEntity customerEntity) {
        return !StringUtils.equalsIgnoreCase(customerEntity.getPhoneNumber(), customerRequest.getPhoneNumber())
                && Objects.nonNull(getCustomerByPhoneNumber(customerRequest.getPhoneNumber()));
    }
}
