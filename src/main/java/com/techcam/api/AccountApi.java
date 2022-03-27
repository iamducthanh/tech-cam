package com.techcam.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techcam.constants.ConstantsErrorCode;
import com.techcam.constants.RequestForgotPasswordConstant;
import com.techcam.dto.request.*;
import com.techcam.dto.request.staff.StaffAddRequestDTO;
import com.techcam.dto.request.staff.StaffEditRequestDTO;
import com.techcam.entity.StaffEntity;
import com.techcam.exception.TechCamExp;
import com.techcam.service.impl.StaffService;
import com.techcam.util.EncodeUtil;
import com.techcam.util.MailerUtil;
import com.techcam.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * Description:
 *
 * @author: thanhnd
 * @version: 1.0
 * @since 2/4/2022 3:18 PM
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class AccountApi {

    @Autowired
    private HttpServletRequest req;

    private final SessionUtil sessionUtil;
    private final StaffService staffService;
    private final EncodeUtil encodeUtil;
    private final MailerUtil mailerUtil;
    private final ModelMapper modelMapper = new ModelMapper();

    private String message;

    @PostMapping("/change-password")
    public void changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        log.warn("Change Password typing...: " + changePasswordDto.toString());
        StaffEntity staffEntity = (StaffEntity) sessionUtil.getObject("STAFF");
        BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
        if (changePasswordDto.getPasswordOld().trim().length() == 0
                || changePasswordDto.getPasswordNew().trim().length() == 0
                || changePasswordDto.getPasswordComfirm().trim().length() == 0
        ) {
            throw new TechCamExp(ConstantsErrorCode.LOGIN_DATA_FAIL);
        }
        if (!pass.matches(changePasswordDto.getPasswordOld(), staffEntity.getPassword())) {
            throw new TechCamExp(ConstantsErrorCode.LOGIN_PASS_C_FAIL);
        }
        if (!changePasswordDto.getPasswordNew().equals(changePasswordDto.getPasswordComfirm())) {
            throw new TechCamExp(ConstantsErrorCode.LOGIN_PASS_M_FAIL);
        }

        String password = pass.encode(changePasswordDto.getPasswordNew());
        staffEntity.setPassword(password);

        staffService.addStaff(modelMapper.map(staffEntity, StaffAddRequestDTO.class));
    }

    @GetMapping("/request-forgot-password")
    public ResponseEntity<String> requestForgotPassword(@RequestParam("email") String email) throws JsonProcessingException, MessagingException {
        // Validate input empty
        if (email.trim().length() == 0) {
            message = String.valueOf(new TechCamExp(ConstantsErrorCode.LOGIN_EMAIL_INPUT_NULL).getErrorMessage().getVn());
            log.warn(message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        // Validate email not exists
        StaffEntity staffEntity = staffService.getByEmail(email);
        if (staffEntity == null) {
            message = String.valueOf(new TechCamExp(ConstantsErrorCode.LOGIN_EMAIL_NOT_EXITS).getErrorMessage().getVn());
            log.warn(message);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        // Validate email found
        String code = RandomStringUtils.randomAlphanumeric(10);
        ForgotPasswordDto forgotPasswordDto = ForgotPasswordDto.builder()
                .email(email)
                .code(code)
                .build();
        String json = new ObjectMapper().writeValueAsString(forgotPasswordDto);
        String token = encodeUtil.encrypt(json);
        String url = req.getRequestURL().toString();
        String domain = url.substring(0, url.indexOf("/api"));

        // Sending retrieve mail
        MailDto mailDto = MailDto.builder()
                .from("TechCam")
                .to(email)
                .subject("Lấy lại mật khẩu")
                .body("Vui lòng truy cập đường dẫn sau để đặt lại mật khẩu, đường dẫn này chỉ có hiệu lực trong vòng 10 phút: " + domain + "/reset-password?token=" + token)
                .build();
        mailerUtil.send(mailDto);

        // Get code
        RequestForgotPasswordConstant.requests.put(email, code);
        log.info("Size req " + RequestForgotPasswordConstant.requests.size());
        // Delete code expire in 10 minutes
        Thread thread = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(1000 * 60 * 10);
                RequestForgotPasswordConstant.requests.remove(email);
            }
        };
        thread.start();

        // Response success
        message = String.valueOf(new TechCamExp(ConstantsErrorCode.LOGIN_RESET_BY_EMAIL_SUCCESS).getErrorMessage().getVn());
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @PutMapping("/change-reset-password")
    public void resetPassword(@RequestBody ChangePasswordDto changePasswordDto) {
        log.warn("Reset Password typing...: " + changePasswordDto.toString());
        String email = (String) sessionUtil.getObject("resetPass");
        StaffEntity staffEntity = staffService.getByEmail(email);
        BCryptPasswordEncoder pass = new BCryptPasswordEncoder();

        // Check empty
        if (changePasswordDto.getPasswordNew().trim().length() == 0
                || changePasswordDto.getPasswordComfirm().trim().length() == 0
        ) {
            message = String.valueOf(new TechCamExp(ConstantsErrorCode.LOGIN_FAIL_FIVE).getErrorMessage().getVn());
            throw new TechCamExp(ConstantsErrorCode.LOGIN_DATA_FAIL);
        }
        // Check new password and repeat password
        if (!changePasswordDto.getPasswordNew().equals(changePasswordDto.getPasswordComfirm())) {
            message = String.valueOf(new TechCamExp(ConstantsErrorCode.LOGIN_FAIL_FIVE).getErrorMessage().getVn());
            throw new TechCamExp(ConstantsErrorCode.LOGIN_PASS_FAIL);
        }

        staffEntity.setPassword(pass.encode(changePasswordDto.getPasswordNew()));
        staffService.editStaff(modelMapper.map(staffEntity, StaffEditRequestDTO.class));
    }

    @GetMapping("/count_login_false")
    public ResponseEntity<String> getCountLoginFalse(@RequestParam("email") String email) {
        StaffEntity staff = staffService.getByEmail(email);
        if (staff != null) {
            int count = staff.getCountLoginFalse();
            if (count > 0) {
                log.error("Login failed to " + email + " for the " + count + " time");
                if (count >= 5) {
                    message = String.valueOf(new TechCamExp(ConstantsErrorCode.LOGIN_FAIL_FIVE).getErrorMessage().getVn());
                    log.error(message);
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(message);
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body("OKE");
        }
        message = String.valueOf(new TechCamExp(ConstantsErrorCode.LOGIN_EMAIL_NOT_EXITS).getErrorMessage().getVn());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
