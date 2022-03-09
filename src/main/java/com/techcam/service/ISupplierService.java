package com.techcam.service;

import com.techcam.dto.request.SupplierDTO;
import com.techcam.dto.response.SupplierResponseDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * Description :
 */
public interface ISupplierService {
    List<SupplierResponseDTO> findAllByDeleteFlagFalse();

    @Transactional(rollbackFor = Exception.class)
    SupplierResponseDTO create(SupplierDTO supplierDTO);

    @Transactional(rollbackFor = Exception.class)
    SupplierResponseDTO update(SupplierDTO supplierDTO, String id);

    SupplierResponseDTO findById(String id);

    void delete(String id);
}
