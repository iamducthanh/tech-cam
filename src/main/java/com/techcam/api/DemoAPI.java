package com.techcam.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * Description :
 */
@RestController
@RequestMapping("demo")
public class DemoAPI {
    @GetMapping
    public ResponseEntity<?> find(){
        return ResponseEntity.ok("oke");
    }

}
