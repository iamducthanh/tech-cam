package com.techcam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 1/24/2022 10:23 PM
 */
@Controller
public class AccountController {
 @GetMapping("/login")
 public String loginPage(){
  return "views/login";
 }

 @GetMapping("/forgot-password")
 public String forgotPasswordPage(){
  return "views/forgotpassword";
 }

 @GetMapping("/change-password")
 public String changePasswordPage(){
  return "views/changepassword";
 }
}
