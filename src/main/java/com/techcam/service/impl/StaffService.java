package com.techcam.service.impl;

import com.techcam.dto.request.staff.StaffAddRequestDTO;
import com.techcam.dto.request.staff.StaffChangePassRequestDTO;
import com.techcam.dto.request.staff.StaffEditRequestDTO;
import com.techcam.dto.response.staff.StaffResponseDTO;
import com.techcam.entity.StaffEntity;
import com.techcam.repo.IStaffRepo;
import com.techcam.service.IStaffService;
import com.techcam.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
@Slf4j
public class StaffService implements IStaffService {

    private static final String DEFAULT_PASSWORD = "$2a$10$VvjMsczCmbmSM3L7usA85.e1pmfaeJERrSY1swgFBqe6h.sN3EKqa"; // default 0123456789

    @Autowired
    private IStaffRepo staffRepo;

    @Autowired
    private final HttpSession session;

    private final CookieUtil cookieUtil;

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
        if (staffRepo.findByEmail(staff.getEmail()).size() != 0) {
            return "Email đã tồn tại";
        }

        entity.setFullName(staff.getFullName());
        entity.setEmail(staff.getEmail());
        entity.setPassword(DEFAULT_PASSWORD);
        entity.setRole(staff.getRole());
        entity.setPhoneNumber(staff.getPhoneNumber());
        entity.setAddress(staff.getAddress());
        entity.setAvatar(staff.getAvatar());

        // Generate random StaffCode
        int staffCode;
        do {
            staffCode = Integer.parseInt(RandomStringUtils.randomNumeric(4));
        } while (staffRepo.findByStaffCode(staffCode) != null);

        entity.setStaffCode(staffCode);
        entity.setDateOfBirth(staff.getDateOfBirth());
        entity.setStatus("1");
        entity.setNote(staff.getNote());
        entity.setCountLoginFalse(0);
        entity.setUsername(staff.getUsername());
        entity.setIdentityNumber(staff.getIdentityNumber());

        try {
            staffRepo.save(entity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return "ok";
    }

    @Override
    public String editStaff(StaffEditRequestDTO staff) {
        StaffEntity entity = staffRepo.getById(staff.getId());
        entity.setFullName(staff.getFullName());
        entity.setRole(staff.getRole());
        entity.setPhoneNumber(staff.getPhoneNumber());
        entity.setAddress(staff.getAddress());
        entity.setAvatar(staff.getAvatar());
        entity.setStaffCode(staff.getStaffCode());
        entity.setDateOfBirth(staff.getDateOfBirth());
        entity.setIdentityNumber(staff.getIdentityNumber());
        entity.setUsername(staff.getUsername());
        entity.setNote(staff.getNote());

        try {
            entity = staffRepo.save(entity);
            if (entity.getEmail().equals(cookieUtil.get("email"))) {
                session.setAttribute("user", entity);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return "ok";
    }

    @Override
    public String changePasswordStaff(StaffChangePassRequestDTO staff) {
        StaffEntity entity = staffRepo.getById(staff.getId());
        entity.setPassword(staff.getPassword());

        try {
            entity = staffRepo.save(entity);
            session.setAttribute("user", entity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

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

            try {
                staffRepo.save(entity);
            } catch (Exception e) {
                log.error(e.getMessage());
            }

            return true;
        }
        return false;
    }

    @Override
    public Integer findByEmail(String email) {
        List<StaffEntity> list = staffRepo.findByEmail(email);
        if (list.isEmpty()) return 0;
        else return list.size();
    }

    @Override
    public Integer findByPhone(String phoneNumber) {
        List<StaffEntity> list = staffRepo.findByPhoneNumber(phoneNumber);
        if (list.isEmpty()) return 0;
        else return list.size();
    }

    // Convert Staff from Entity to DTO
    private StaffResponseDTO entityToDto(StaffEntity entity) {
        StaffResponseDTO staff = new StaffResponseDTO();
        staff.setId(entity.getId());
        staff.setFullName(entity.getFullName());
        staff.setDateOfBirth(entity.getDateOfBirth());
        staff.setStaffCode(entity.getStaffCode());
        staff.setPhoneNumber(entity.getPhoneNumber());
        staff.setIdentityNumber(entity.getIdentityNumber());
        staff.setUsername(entity.getUsername());
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
    public StaffEntity saveStaff(StaffEntity staffEntity) {
        return repo.save(staffEntity);
    }
}
