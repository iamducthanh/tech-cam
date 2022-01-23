package com.techcam.exception;

import com.techcam.dto.error.ErrorRespDto;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 9:09 PM
 * Project_name : tech-cam
 */

public class ArithmeticConfig extends RuntimeConfig {


    public ArithmeticConfig(String message) {
        super(message);
    }

    public ArithmeticConfig(List<ErrorRespDto> errors) {
        super(errors);
    }

    public ArithmeticConfig(ErrorRespDto error) {
        super(error);
    }
}
