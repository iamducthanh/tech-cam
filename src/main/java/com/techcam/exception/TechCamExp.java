package com.techcam.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;


/**
 * Description:
 *
 * @author: GMO_DuyDV
 * @version: 1.0
 * @since: 1/23/2022
 * Project_name: GMO_QuanLyTaiSan
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TechCamExp extends RuntimeException {

    private final ErrorMessage errorMessage;

    public TechCamExp(String errorCode) {
        ErrorMessage message = ErrorMessageLoader.getMessage(errorCode);
        if (Objects.isNull(message)) {
            message = new ErrorMessage();
            message.setVn(errorCode);
        }
        this.errorMessage = message;
    }

    public TechCamExp(String errorCode, Object... args) {
        this.errorMessage = ErrorMessageLoader.getMessage(errorCode, args);
    }

}
