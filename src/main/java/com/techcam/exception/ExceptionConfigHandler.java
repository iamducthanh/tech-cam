package com.techcam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Description : config trả về exception tuỳ ý
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 9:09 PM
 * Project_name : tech-cam
 */

@ControllerAdvice
public class ExceptionConfigHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DuplicateKeyConfig.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> onDuplicateKey(DuplicateKeyConfig e) {
        if (e.getErrors() == null    || e.getErrors().isEmpty()) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(e.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateConfig.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<?> onIllegalState(IllegalStateConfig e) {
        if (e.getErrors() == null || e.getErrors().isEmpty()) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(e.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArithmeticConfig.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<?> onIllegalArgument(ArithmeticConfig e) {
        if (e.getErrors() == null || e.getErrors().isEmpty()) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(e.getErrors(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(TechCamExp.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<?> techCamExp(TechCamExp e){
        return new ResponseEntity<>(e.getErrorMessage(),HttpStatus.NOT_FOUND);
    }

}
