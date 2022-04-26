package com.techcam.service.impl;

import com.techcam.entity.StaffEntity;
import com.techcam.exception.TechCamExp;
import com.techcam.service.IAuthService;
import com.techcam.service.IStaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 2022/04/24 21:49
 * Project_name : tech-cam
 */

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final IStaffService staffService;

    @Override
    public StaffEntity getAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)) {
            String email = authentication.getName();
            return staffService.getByEmail(email);
        }
        return new StaffEntity();
    }

}
