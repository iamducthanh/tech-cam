package com.techcam.service.impl;

import com.techcam.constants.ConstantsErrorCode;
import com.techcam.dto.request.SupplierDTO;
import com.techcam.dto.response.SupplierResponseDTO;
import com.techcam.entity.SupplierEntity;
import com.techcam.exception.SupplierException;
import com.techcam.repo.ISupplierRepo;
import com.techcam.service.ISupplierService;
import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * Description :
 */
@Service
public class SupplierService implements ISupplierService {
    @Autowired
    private ISupplierRepo supplierRepository;

    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public List<SupplierResponseDTO> findAllByDeleteFlagFalse(){
        List<SupplierEntity> supplierEntities = supplierRepository.findAllByDeleteFlagFalse();
        List<SupplierResponseDTO> supplierResponseDTOS = new ArrayList<>();
        supplierResponseDTOS = (List<SupplierResponseDTO>) modelMapper.map(supplierEntities, SupplierResponseDTO.class);
        return supplierResponseDTOS;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SupplierResponseDTO create(SupplierDTO supplierDTO){

        if(supplierRepository.findByEmail(supplierDTO.getEmail()).isPresent()){
            throw new SupplierException(ConstantsErrorCode.EMAIL_EXIST);
        }
        if(supplierRepository.findByPhoneNumber(supplierDTO.getPhoneNumber()).isPresent()){
            throw new SupplierException(ConstantsErrorCode.PHONE_NUMBER_EXIST);
        }
        SupplierEntity supplierEntity = modelMapper.map(supplierDTO, SupplierEntity.class);
        supplierEntity.setId(UUID.randomUUID().toString());
        supplierRepository.save(supplierEntity);
        return modelMapper.map(supplierEntity, SupplierResponseDTO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SupplierResponseDTO update(SupplierDTO supplierDTO, String id){
        if(!supplierRepository.findById(id).isPresent()){
            throw new SupplierException(ConstantsErrorCode.SUPPLIER_NOT_EXIST);
        }
        SupplierEntity supplierEntity = supplierRepository.findById(id).get();
        if(Strings.isNotBlank(supplierDTO.getEmail()) && !supplierDTO.getEmail().equals(supplierEntity.getEmail())
                && supplierRepository.findByEmail(supplierDTO.getEmail()).isPresent()){
            throw new SupplierException(ConstantsErrorCode.EMAIL_EXIST);
        }
        if(Strings.isNotBlank(supplierDTO.getPhoneNumber()) && !supplierDTO.getPhoneNumber().equals(supplierEntity.getPhoneNumber())
                && supplierRepository.findByPhoneNumber(supplierDTO.getPhoneNumber()).isPresent()){
            throw new SupplierException(ConstantsErrorCode.PHONE_NUMBER_EXIST);
        }
        BeanUtils.copyProperties(supplierDTO, supplierEntity);
        return modelMapper.map(supplierEntity, SupplierResponseDTO.class);
    }

    @Override
    public SupplierResponseDTO findById(String id){
        if(!supplierRepository.findById(id).isPresent()){
            throw new SupplierException(ConstantsErrorCode.SUPPLIER_NOT_EXIST);
        }
        SupplierEntity supplierEntity = supplierRepository.findByIdAndDeleteFlagFalse(id).get();
        return modelMapper.map(supplierEntity, SupplierResponseDTO.class);
    }

    @Override
    public void delete(String id){
        if(!supplierRepository.findById(id).isPresent()){
            throw new SupplierException(ConstantsErrorCode.SUPPLIER_NOT_EXIST);
        }
        SupplierEntity supplierEntity = supplierRepository.findByIdAndDeleteFlagFalse(id).get();
        supplierEntity.setDeleteFlag(true);
    }

}
