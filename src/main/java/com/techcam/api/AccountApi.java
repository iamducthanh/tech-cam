package com.techcam.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techcam.constants.RequestForgotPasswordConstant;
import com.techcam.dto.request.ChangePasswordDto;
import com.techcam.dto.request.ForgotPasswordDto;
import com.techcam.dto.request.MailDto;
import com.techcam.entity.StaffEntity;
import com.techcam.exception.IllegalStateConfig;
import com.techcam.service.impl.StaffService;
import com.techcam.util.EncodeUtil;
import com.techcam.util.MailerUtil;
import com.techcam.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
 @Autowired
 private HttpServletRequest req;

 @PostMapping("/change-password")
 public void changePassword(@RequestBody ChangePasswordDto changePasswordDto){
  System.out.println(changePasswordDto.toString());
  StaffEntity staffEntity = (StaffEntity) sessionUtil.getObject("STAFF");
  BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
  if(changePasswordDto.getPasswordOld().trim().length() == 0
  || changePasswordDto.getPasswordNew().trim().length() == 0
  || changePasswordDto.getPasswordComfirm().trim().length() == 0
  ){
   throw new IllegalStateConfig("Vui lòng nhập đầy đủ thông tin!");
  }
  if(!pass.matches(changePasswordDto.getPasswordOld(), staffEntity.getPassword())){
   throw new IllegalStateConfig("Mật khẩu cũ không chính xác!");
  }
  if(!changePasswordDto.getPasswordNew().equals(changePasswordDto.getPasswordComfirm())){
   throw new IllegalStateConfig("Mật khẩu mới và xác nhận mật khẩu phải giống nhau!");
  }

  String password = pass.encode(changePasswordDto.getPasswordNew());
  staffEntity.setPassword(password);
  staffService.saveStaff(staffEntity);
 }

 @GetMapping("/request-forgot-password")
 public void requestForgotPassword(@RequestParam("email") String email) throws JsonProcessingException, MessagingException {
  if(email.trim().length() == 0){
   throw new IllegalStateConfig("Vui lòng nhập địa chỉ email!");
  }
  StaffEntity staffEntity = staffService.getByEmail(email);
  if (staffEntity == null) {
   throw new IllegalStateConfig("Địa chỉ email này chưa được đăng kí!");
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


  Thread thread = new Thread(){
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
 public void resetPassword(@RequestBody ChangePasswordDto changePasswordDto){
  System.out.println("on change");
  System.out.println(changePasswordDto.toString());
  String email = (String) sessionUtil.getObject("resetPass");
  StaffEntity staffEntity = staffService.getByEmail(email);
  BCryptPasswordEncoder pass = new BCryptPasswordEncoder();
  if(changePasswordDto.getPasswordNew().trim().length() == 0
          || changePasswordDto.getPasswordComfirm().trim().length() == 0
  ){
   throw new IllegalStateConfig("Vui lòng nhập đầy đủ thông tin!");
  }
  if(!changePasswordDto.getPasswordNew().equals(changePasswordDto.getPasswordComfirm())){
   throw new IllegalStateConfig("Mật khẩu mới và xác nhận mật khẩu phải giống nhau!");
  }

  String password = pass.encode(changePasswordDto.getPasswordNew());
  staffEntity.setPassword(password);
  staffService.saveStaff(staffEntity);
 }

}
