package com.techcam.service.impl;

import com.techcam.entity.StaffEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 1/24/2022 10:50 PM
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class StaffDetailsServiceImpl implements UserDetailsService {

    private final StaffService staffService;

    @Autowired
    private final HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        StaffEntity staff = staffService.getByEmail(email);
        if (staff == null) {
            log.error("User not found! " + email);
            session.setAttribute("emailLogin", email);
            throw new UsernameNotFoundException("User " + email + " was not found in the database");
        }

        if (staff.getCountLoginFalse() >= 5) {
            throw new UsernameNotFoundException("Tài khoản " + email + " đã đăng nhập sai quá 5 lần, vui lòng nhấn quên mật khẩu để xác nhận lại tài khoản!");
        }
        staff.setCountLoginFalse(staff.getCountLoginFalse() + 1);
        staff = staffService.saveStaff(staff);

        // Add info logged user to Session
        log.info("Logged: " + staff);
        session.setAttribute("user", staff);
        session.setAttribute("username", staff.getUsername());

        List<String> roleNames = new ArrayList<>();
        assert staff != null;
        roleNames.add(staff.getRole());

        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleNames != null) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
        return new User(staff.getEmail(), staff.getPassword(), grantList);
    }
}
