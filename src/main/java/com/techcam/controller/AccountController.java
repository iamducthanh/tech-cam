package com.techcam.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techcam.constants.RequestForgotPasswordConstant;
import com.techcam.dto.request.ForgotPasswordDto;
import com.techcam.util.EncodeUtil;
import com.techcam.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 1/24/2022 10:23 PM
 */
@Controller
@RequiredArgsConstructor
public class AccountController {
    private final EncodeUtil encodeUtil;
    private final SessionUtil sessionUtil;

    @GetMapping("/login")
    public String loginPage() {
        return "views/login";
    }

    @GetMapping("/forgot-password")
    public String forgotPasswordPage() {
        return "views/forgotpassword";
    }

    @GetMapping("/change-password")
    public String changePasswordPage() {
        return "views/changepassword";
    }

    @GetMapping("/reset-password")
    public String resetPasswordPage(@RequestParam("token") String token, Model model) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String view = "";
        String tokenDecoded = encodeUtil.decrypt(token.replace(" ", "+"));
        ForgotPasswordDto forgotPasswordDto = objectMapper.readValue(tokenDecoded, ForgotPasswordDto.class);
        System.out.println("size req " + RequestForgotPasswordConstant.requests.size());
        System.out.println(RequestForgotPasswordConstant.requests.get(forgotPasswordDto.getEmail()));
        String codeConst = RequestForgotPasswordConstant.requests.get(forgotPasswordDto.getEmail());
        if(codeConst == null || !codeConst.equals(forgotPasswordDto.getCode())){
            model.addAttribute("message","Yêu cầu đã hết hạn vui lòng gửi lại!");
            view = "forward:/forgot-password";
        } else {
            sessionUtil.addObject("resetPass", forgotPasswordDto.getEmail());
            view = "views/resetpassword";
        }
        return view;
    }

}
