package com.techcam.config;

import com.techcam.entity.StaffEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author Dev
 * @version 1.0
 * @since 30.3.2022
 */
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Autowired
    private HttpSession session;

    @Override
    public Optional<String> getCurrentAuditor() {

        // Can use Spring Security to return currently logged in user
        StaffEntity staff = (StaffEntity) session.getAttribute("user");
        if (staff == null) {
            return Optional.ofNullable("ADMIN");
        }
        return Optional.ofNullable(staff.getUsername());
    }
}