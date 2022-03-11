//package com.techcam.service.impl;
//
//import com.techcam.entity.StaffEntity;
//import com.techcam.util.CookieUtil;
//import com.techcam.util.SessionUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.User;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Description:
// *
// * @author: GMO_ThanhND
// * @version: 1.0
// * @since 1/24/2022 10:50 PM
// */
//
//@Service
////@RequiredArgsConstructor
//public class StaffDetailsServiceImpl implements UserDetailsService {
//    @Autowired
//    private CookieUtil cookieUtil;
//    @Autowired
//    private SessionUtil sessionUtil;
//    @Autowired
//    private StaffService staffService;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        StaffEntity staff = staffService.getByEmail(email);
//        if (staff == null) {
//            System.out.println("User not found! " + email);
//            sessionUtil.addObject("emailLogin", email);
//            throw new UsernameNotFoundException("User " + email + " was not found in the database");
//        }
//
//        sessionUtil.addObject("STAFF", staff);
//        System.out.println("Found staff: " + staff);
//
//        List<String> roleNames = new ArrayList<>();
//        roleNames.add(staff.getRole());
//
//        List<GrantedAuthority> grantList = new ArrayList<>();
//        if (roleNames != null) {
//            for (String role : roleNames) {
//                GrantedAuthority authority = new SimpleGrantedAuthority(role);
//                grantList.add(authority);
//            }
//        }
//     return new User(staff.getEmail(), staff.getPassword(), grantList);
//    }
//}
