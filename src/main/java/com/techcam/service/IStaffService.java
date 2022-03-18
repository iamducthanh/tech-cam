package com.techcam.service;

import com.techcam.dto.request.StaffAddRequestDTO;
import com.techcam.dto.request.StaffEditRequestDTO;
import com.techcam.dto.response.StaffResponseDTO;
import com.techcam.entity.StaffEntity;

import java.util.List;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * Description :
 */
public interface IStaffService {
    // All staffs
    List<StaffResponseDTO> findAll();

    // Staff by ID
    StaffResponseDTO findById(String id);

    // Staffs deleted
    List<StaffResponseDTO> findAllByDeleteFlagIsTrue();

    // Staffs not deleted
    List<StaffResponseDTO> findAllByDeleteFlagIsFalse();

    // Staffs active & not delete
    List<StaffResponseDTO> findAllByStatusIsTrueAndDeleteFlagIsTrue();

    // Staffs inactive & not delete
    List<StaffResponseDTO> findAllByStatusIsFalseAndDeleteFlagIsTrue();

    // Add staff
    String addStaff(StaffAddRequestDTO staff);

    // Edit staff
    String editStaff(StaffEditRequestDTO staff);

    // Delete staff
    boolean deleteStaff(String id);

    // Block/Unblock staff by status
    boolean changeStatusStaff(String id, String status);

    // Find by email
    Integer findByEmail(String email);

    // Find by phone number
    Integer findByPhone(String phoneNumber);

    // Get staff by email
    StaffEntity getByEmail(String email);

    // save staff
    StaffEntity saveStaff(StaffEntity staffEntity);
}
