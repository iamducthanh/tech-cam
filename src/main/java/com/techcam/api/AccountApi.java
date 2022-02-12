package com.techcam.api;

import com.techcam.dto.request.ChangePasswordDto;
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
public class AccountApi {
 @PostMapping("/change-password")
 public void changePassword(@RequestBody ChangePasswordDto changePasswordDto){
  System.out.println(changePasswordDto.toString());


 }
}
