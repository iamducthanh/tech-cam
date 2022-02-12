package com.techcam.api;

import com.techcam.dto.request.ChangePasswordDto;
import com.techcam.entity.StaffEntity;
import com.techcam.exception.IllegalStateConfig;
import com.techcam.service.impl.StaffService;
import com.techcam.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
}
