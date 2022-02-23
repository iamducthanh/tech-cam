package com.techcam.service.impl;

import com.techcam.dto.request.StaffAddRequestDTO;
import com.techcam.dto.response.StaffResponseDTO;
import com.techcam.entity.StaffEntity;
import com.techcam.repo.IStaffRepo;
import com.techcam.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * Description :
 */
@Service
public class StaffService implements IStaffService {

    @Autowired
    private IStaffRepo staffRepo;

    @Override
    public List<StaffResponseDTO> findAll() {
        // Init return list
        List<StaffResponseDTO> list = new ArrayList<>();
        List<StaffEntity> entities = staffRepo.findAll();

        // Convert Entity to DTO
        entities.forEach(e -> {
            StaffResponseDTO dto = new StaffResponseDTO();
            entityToDto(e, dto);
            list.add(dto);
        });

        return list;
    }

    @Override
    public StaffResponseDTO findById(String id) {
        // Init return
        StaffResponseDTO staff = new StaffResponseDTO();

        // Convert Entity to DTO
        StaffEntity entity =  staffRepo.getById(id);
        entityToDto(entity, staff);

        return staff;
    }

    @Override
    public List<StaffResponseDTO> findAllByDeleteFlagIsTrue() {
        // Init return list
        List<StaffResponseDTO> list = new ArrayList<>();
        List<StaffEntity> entities = staffRepo.findAllByDeleteFlagIsTrue();

        // Convert Entity to DTO
        entities.forEach(e -> {
            StaffResponseDTO dto = new StaffResponseDTO();
            entityToDto(e, dto);
            list.add(dto);
        });

        return list;
    }

    @Override
    public List<StaffResponseDTO> findAllByDeleteFlagIsFalse() {
        // Init return list
        List<StaffResponseDTO> list = new ArrayList<>();
        List<StaffEntity> entities = staffRepo.findAllByDeleteFlagIsFalse();

        // Convert Entity to DTO
        entities.forEach(e -> {
            StaffResponseDTO dto = new StaffResponseDTO();
            entityToDto(e, dto);
            list.add(dto);
        });

        return list;
    }

    @Override
    public List<StaffResponseDTO> findAllByStatusIsTrueAndDeleteFlagIsTrue() {
        // Init return list
        List<StaffResponseDTO> list = new ArrayList<>();
        List<StaffEntity> entities = staffRepo.findAllByStatusIsTrueAndDeleteFlagIsTrue();

        // Convert Entity to DTO
        entities.forEach(e -> {
            StaffResponseDTO dto = new StaffResponseDTO();
            entityToDto(e, dto);
            list.add(dto);
        });

        return list;
    }

    @Override
    public List<StaffResponseDTO> findAllByStatusIsFalseAndDeleteFlagIsTrue() {
        // Init return list
        List<StaffResponseDTO> list = new ArrayList<>();
        List<StaffEntity> entities = staffRepo.findAllByStatusIsFalseAndDeleteFlagIsTrue();

        // Convert Entity to DTO
        entities.forEach(e -> {
            StaffResponseDTO dto = new StaffResponseDTO();
            entityToDto(e, dto);
            list.add(dto);
        });

        return list;
    }

    @Override
    public StaffAddRequestDTO addStaff(StaffResponseDTO staff) {
//        StaffEntity entity = new StaffEntity();
//        entity.set
        return null;
    }

    // Convert Staff from Entity to DTO
    private void entityToDto(StaffEntity entity, StaffResponseDTO staff) {
        staff.setId(entity.getId());
        staff.setFullName(entity.getFullName());
        staff.setDateOfBirth(entity.getDateOfBirth());
        staff.setStaffCode(entity.getStaffCode());
        staff.setPhoneNumber(entity.getPhoneNumber());
        staff.setEmail(entity.getEmail());
        staff.setAddress(entity.getAddress());
        staff.setAvatar(entity.getAvatar());
        staff.setRole(entity.getRole());
        staff.setCreateDate(entity.getCreateDate());
        staff.setStatus(entity.getStatus());
    }
}
