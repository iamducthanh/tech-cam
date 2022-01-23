package com.techcam.exception;

import com.techcam.dto.error.ErrorRespDto;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/5/2022 2:43 PM
 * Project_name : HTTool
 */

public class DuplicateKeyConfig extends RuntimeConfig {

    public DuplicateKeyConfig(String message) {
        super(message);
    }

    public DuplicateKeyConfig(List<ErrorRespDto> errors) {
        super(errors);
    }

    public DuplicateKeyConfig(ErrorRespDto error) {
        super(error);
    }

}
