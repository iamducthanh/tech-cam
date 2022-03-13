package com.techcam.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techcam.constants.ConstantsErrorCode;
import com.techcam.constants.RequestForgotPasswordConstant;
import com.techcam.dto.request.ChangePasswordDto;
import com.techcam.dto.request.ForgotPasswordDto;
import com.techcam.dto.request.MailDto;
import com.techcam.dto.request.StaffAddRequestDTO;
import com.techcam.entity.StaffEntity;
import com.techcam.exception.TechCamExp;
import com.techcam.service.impl.StaffService;
import com.techcam.util.EncodeUtil;
import com.techcam.util.MailerUtil;
import com.techcam.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

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
public class AccountApi {
    private final SessionUtil sessionUtil;
    private final StaffService staffService;
    private final EncodeUtil encodeUtil;
    private final MailerUtil mailerUtil;
    private final ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private HttpServletRequest req;

    @PostMapping("/change-password")
    public void changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        System.out.println(changePasswordDto.toString());
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
    public void requestForgotPassword(@RequestParam("email") String email) throws JsonProcessingException, MessagingException {
        if (email.trim().length() == 0) {
            throw new TechCamExp(ConstantsErrorCode.LOGIN_EMAIL_INPUT_NULL);
        }
        StaffEntity staffEntity = staffService.getByEmail(email);
        if (staffEntity == null) {
            throw new TechCamExp(ConstantsErrorCode.LOGIN_EMAIL_NOT_EXITS);
        }

        String code = UUID.randomUUID().toString();

        ObjectMapper objectMapper = new ObjectMapper();
        ForgotPasswordDto forgotPasswordDto = ForgotPasswordDto.builder()
                .email(email)
                .code(code)
                .build();
        String json = objectMapper.writeValueAsString(forgotPasswordDto);
        String token = encodeUtil.encrypt(json);
        String url = req.getRequestURL().toString();
        String domain = url.substring(0, url.indexOf("/api"));

        MailDto mailDto = MailDto.builder()
                .from("TechCam")
                .to(email)
                .subject("Lấy lại mật khẩu")
                .body("Vui lòng truy cập đường dẫn sau để đặt lại mật khẩu, đường dẫn này chỉ có hiệu lực trong vòng 10 phút: " + domain + "/reset-password?token=" + token)
                .build();

        mailerUtil.send(mailDto);

        RequestForgotPasswordConstant.requests.put(email, code);

        System.out.println("size req " + RequestForgotPasswordConstant.requests.size());


        Thread thread = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(1000 * 60 * 10);
                RequestForgotPasswordConstant.requests.remove(email);
            }
        };
        thread.start();
    }

    @PutMapping("/change-reset-password")
    public void resetPassword(@RequestBody ChangePasswordDto changePasswordDto) {
        System.out.println("on change");
        System.out.println(changePasswordDto.toString());
        String email = (String) sessionUtil.getObject("resetPass");
        StaffEntity staffEntity = staffService.getByEmail(email);
        BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
        if (changePasswordDto.getPasswordNew().trim().length() == 0
                || changePasswordDto.getPasswordComfirm().trim().length() == 0
        ) {
            throw new TechCamExp(ConstantsErrorCode.LOGIN_DATA_FAIL);
        }
        if (!changePasswordDto.getPasswordNew().equals(changePasswordDto.getPasswordComfirm())) {
            throw new TechCamExp(ConstantsErrorCode.LOGIN_PASS_FAIL);
        }

        String password = pass.encode(changePasswordDto.getPasswordNew());
        staffEntity.setPassword(password);

        staffService.addStaff(modelMapper.map(staffEntity, StaffAddRequestDTO.class));
    }

    @GetMapping("/count_login_false")
    public String getCountLoginFalse(@RequestParam("email") String email) {
        System.out.println("get " + email);
        StaffEntity staff = staffService.getByEmail(email);
        if (staff != null) {
            Integer count = staff.getCountLoginFalse();
            if (count >= 5) {
                System.out.println("lỗi cmnr");
                throw new TechCamExp(ConstantsErrorCode.LOGIN_FAIL_FIVE);
            }
        }
        return "done";
    }

}
