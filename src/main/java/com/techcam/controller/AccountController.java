package com.techcam.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techcam.constants.RequestForgotPasswordConstant;
import com.techcam.dto.request.ForgotPasswordDto;
import com.techcam.entity.NotificationEntity;
import com.techcam.repo.INotificationRepo;
import com.techcam.util.EncodeUtil;
import com.techcam.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.UUID;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 1/24/2022 10:23 PM
 */
@Controller
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final EncodeUtil encodeUtil;
    private final SessionUtil sessionUtil;
    private final INotificationRepo notificationRepo;

    @GetMapping("/login")
    public String loginPage() {
        NotificationEntity notificationEntity = NotificationEntity.builder()
                .id(UUID.randomUUID().toString())
                .productId("eeeeeeeeeeeeeee")
                .content("hello")
                .createDate(new Date())
                .modifyDate(new Date())
                .createBy("System")
                .modifyBy("System")
                .deleteFlag(false)
                .type("ORDER")
                .read(false)
                .build();
        System.out.println(notificationEntity.toString());
        notificationRepo.save(notificationEntity); // lỗi khi save
        return "views/login";
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "views/forgotpassword";
    }

    @GetMapping("/reset-password")
    public String resetPasswordPage(@RequestParam("token") String token, Model model) throws JsonProcessingException {
        log.warn("Request reset password " + RequestForgotPasswordConstant.requests.size());

        String tokenDecoded = encodeUtil.decrypt(token.replace(" ", "+"));
        ForgotPasswordDto forgotPasswordDto = new ObjectMapper().readValue(tokenDecoded, ForgotPasswordDto.class);
        String resetPassToken = RequestForgotPasswordConstant.requests.get(forgotPasswordDto.getEmail());
        log.warn("Reset password token = " + resetPassToken);
        if(resetPassToken == null || !resetPassToken.equals(forgotPasswordDto.getCode())){
            model.addAttribute("message","Yêu cầu đã hết hạn vui lòng gửi lại!");
            return "forward:/forgot-password";
        } else {
            sessionUtil.addObject("resetPass", forgotPasswordDto.getEmail());
            return "views/resetpassword";
        }
    }

}
