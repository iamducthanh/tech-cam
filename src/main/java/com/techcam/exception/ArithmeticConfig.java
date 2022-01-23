package com.techcam.exception;

import com.techcam.dto.error.ErrorRespDto;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/16/2022 5:06 PM
 * Project_name : HTTool
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
