package com.techcam.service;

import com.techcam.dto.response.staff.StaffResponseDTO;
import com.techcam.entity.StaffEntity;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 2022/04/24 21:48
 * Project_name : tech-cam
 */

public interface IAuthService {
    StaffEntity getAuth();
}
