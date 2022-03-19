package com.techcam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/11/2022
 * Project_name: Tech-cam
 */

@ControllerAdvice
public class TechCamExpHandler {

    @ExceptionHandler(TechCamExp.class)
    @ResponseBody
    ResponseEntity<?> techCamExpNotFound(TechCamExp e){
       return new ResponseEntity(e.getErrorMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
