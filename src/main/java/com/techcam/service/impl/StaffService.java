package com.techcam.service.impl;

import com.techcam.dto.request.StaffAddRequestDTO;
import com.techcam.dto.request.StaffEditRequestDTO;
import com.techcam.dto.response.StaffResponseDTO;
import com.techcam.entity.StaffEntity;
import com.techcam.repo.IStaffRepo;
import com.techcam.entity.StaffEntity;
import com.techcam.repo.IStaffRepo;
import com.techcam.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
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
@RequiredArgsConstructor
public class StaffService implements IStaffService {

    private static final String DEFAULT_PASSWORD = "0123456789";

    @Autowired
    private IStaffRepo staffRepo;

    @Override
    public List<StaffResponseDTO> findAll() {
        // Init return list
        List<StaffResponseDTO> list = new ArrayList<>();
        List<StaffEntity> entities = staffRepo.findAll();

        // Convert Entity to DTO
        entities.forEach(e -> {
            StaffResponseDTO dto = entityToDto(e);
            list.add(dto);
        });

        return list;
    }

    @Override
    public StaffResponseDTO findById(String id) {
        StaffEntity entity = staffRepo.getById(id);
        return entityToDto(entity);
    }

    @Override
    public List<StaffResponseDTO> findAllByDeleteFlagIsTrue() {
        // Init return list
        List<StaffResponseDTO> list = new ArrayList<>();
        List<StaffEntity> entities = staffRepo.findAllByDeleteFlagIsTrue();

        // Convert Entity to DTO
        entities.forEach(e -> {
            StaffResponseDTO dto = entityToDto(e);
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
            StaffResponseDTO dto = entityToDto(e);
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
            StaffResponseDTO dto = entityToDto(e);
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
            StaffResponseDTO dto = entityToDto(e);
            list.add(dto);
        });

        return list;
    }

    @Override
    public String addStaff(StaffAddRequestDTO staff) {
        StaffEntity entity = new StaffEntity();
        if (staffRepo.findByEmail(staff.getEmail()) != null) {
            return "Email đã tồn tại";
        }

        entity.setFullName(staff.getFullName());
        entity.setEmail(staff.getEmail());
        entity.setPassword(DEFAULT_PASSWORD);
        entity.setRole(staff.getRole());
        entity.setPhoneNumber(staff.getPhoneNumber());
        entity.setAddress(staff.getAddress());
        if (staff.getAvatar() == null) {
            entity.setAvatar("none");
        }

        Integer staffCode;
        for (; ; ) {
            int min = 1;
            int max = 9999;
            staffCode = (int) Math.floor(Math.random() * (max - min + 1) + min);
            if (staffRepo.findByStaffCode(staffCode) == null) {
                break;
            }
        }
        entity.setStaffCode(staffCode);
        entity.setDateOfBirth(staff.getDateOfBirth());
        entity.setStatus("1");
        entity.setNote(staff.getNote());
        entity.setCountLoginFalse(0);

        staffRepo.save(entity);

        return "ok";
    }

    @Override
    public String editStaff(StaffEditRequestDTO staff) {
        StaffEntity entity = staffRepo.getById(staff.getId());
        entity.setFullName(staff.getFullName());
        entity.setRole(staff.getRole());
        entity.setPhoneNumber(staff.getPhoneNumber());
        entity.setAddress(staff.getAddress());
        entity.setStaffCode(staff.getStaffCode());
        entity.setDateOfBirth(staff.getDateOfBirth());
        entity.setNote(staff.getNote());

        staffRepo.save(entity);
        return "ok";
    }

    @Override
    public boolean deleteStaff(String id) {
        Optional<StaffEntity> optionalStaff = staffRepo.findById(id);

        if (optionalStaff.isPresent()) {
            StaffEntity entity = optionalStaff.get();
            entity.setDeleteFlag(true);
            staffRepo.save(entity);
            return true;
        }
        return false;
    }

    @Override
    public boolean changeStatusStaff(String id, String status) {
        Optional<StaffEntity> optionalStaff = staffRepo.findById(id);

        if (optionalStaff.isPresent()) {
            StaffEntity entity = optionalStaff.get();
            entity.setStatus(status);
            staffRepo.save(entity);
            return true;
        }
        return false;
    }

    // Convert Staff from Entity to DTO
    private StaffResponseDTO entityToDto(StaffEntity entity) {
        StaffResponseDTO staff = new StaffResponseDTO();
        staff.setId(entity.getId());
        staff.setFullName(entity.getFullName());
        staff.setDateOfBirth(entity.getDateOfBirth());
        staff.setStaffCode(entity.getStaffCode());
        staff.setPhoneNumber(entity.getPhoneNumber());
        staff.setEmail(entity.getEmail());
        staff.setAddress(entity.getAddress());
        staff.setAvatar(entity.getAvatar());
        staff.setRole(entity.getRole());
        staff.setNote(entity.getNote());
        staff.setCreateDate(entity.getCreateDate());
        staff.setStatus(entity.getStatus());
        return staff;
    }
    private final IStaffRepo repo;

    @Override
    public StaffEntity getByEmail(String email) {
        List<StaffEntity> list = repo.findByEmail(email);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void saveStaff(StaffEntity staffEntity) {
        repo.save(staffEntity);
    }


    public static void main(String[] args)  {
//        String secretKey = "TVDqqqqqqqq";
//        String originalString = "teamvietdev.com";
//
//        String encryptedString = encrypt(originalString, secretKey);
//        System.out.println("Encrypt: " + encryptedString);
//        String decryptedString = decrypt(encryptedString, secretKey);
//        System.out.println("Decrypt: " + decryptedString);
    }
}
