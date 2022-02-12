package com.techcam.service;

import com.techcam.entity.StaffEntity;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * Description :
 */
public interface IStaffService {
    StaffEntity getByEmail(String email);
}
