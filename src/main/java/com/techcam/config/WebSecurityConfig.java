package com.techcam.config;

import com.techcam.dto.request.StaffAddRequestDTO;
import com.techcam.entity.StaffEntity;
import com.techcam.service.impl.StaffDetailsServiceImpl;
import com.techcam.service.impl.StaffService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final ModelMapper modelMapper = new ModelMapper();


    @Autowired
    private StaffDetailsServiceImpl staffDetailsService;
    private final StaffService staffService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(staffDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests().antMatchers(
                "/login/**",
                "/reset-password/**",
                "/assets/**",
                "/forgot-password/**",
                "/api/request-forgot-password/**",
                "/api/change-reset-password/**",
                "/api/count_login_false/**"
//                "/api/staff/**",
//                "/staff/**",
//                "/api/v1/customer/**",
//                "/customer/**"
        ).permitAll();

        http.authorizeRequests().antMatchers("/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        http.formLogin()
                .loginProcessingUrl("/login-check")
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?status=login_false")
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                        String username = userDetails.getUsername();
                        System.out.println("Đăng nhập thành công " + username);
                        StaffEntity staffEntity = staffService.getByEmail(username);
                        staffEntity.setCountLoginFalse(0);
                        staffService.saveStaff(staffEntity);
                        response.sendRedirect(request.getContextPath());
                    }
                })
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login?status=logout");

        http.authorizeRequests().and() //
                .rememberMe().rememberMeParameter("remember")
                .tokenValiditySeconds(24 * 60 * 60); // 24h

    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        return new InMemoryTokenRepositoryImpl();
    }


    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("0123456789"));
    }

}
