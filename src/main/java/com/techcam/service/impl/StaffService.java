package com.techcam.service.impl;

import com.techcam.entity.StaffEntity;
import com.techcam.repo.IStaffRepo;
import com.techcam.service.IStaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    private final IStaffRepo repo;

    @Override
    public StaffEntity getByEmail(String email) {
        List<StaffEntity> list = repo.findByEmail(email);
        return list.isEmpty() ? null : list.get(0);
    }
}
