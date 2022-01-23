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

public class IllegalStateConfig extends RuntimeConfig {

    public IllegalStateConfig(List<ErrorRespDto> errors) {
        super(errors);
    }

    public IllegalStateConfig(String message) {
        super(message);
    }

    public IllegalStateConfig(ErrorRespDto error) {
        super(error);
    }
}
