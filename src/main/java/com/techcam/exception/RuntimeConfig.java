package com.techcam.exception;

import com.techcam.dto.error.ErrorRespDto;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/21/2022 12:42 PM
 * Project_name : HTTool
 */

@Getter
public class RuntimeConfig extends RuntimeException {

    private List<ErrorRespDto> errors = new ArrayList<>();

    public RuntimeConfig(String message) {
        super(message);
    }

    public RuntimeConfig(List<ErrorRespDto> errors) {
        this.errors = errors;
    }

    public RuntimeConfig(ErrorRespDto error) {
        this.errors.add(error);
    }

}
