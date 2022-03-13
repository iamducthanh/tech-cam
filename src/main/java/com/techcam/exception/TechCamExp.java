package com.techcam.exception;

import lombok.*;
import org.apache.catalina.connector.Response;
import org.springframework.stereotype.Component;


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
        this.errorMessage = ErrorMessageLoader.getMessage(errorCode);
    }

    public TechCamExp(String errorCode, Object... args) {
        this.errorMessage = ErrorMessageLoader.getMessage(errorCode, args);
    }

}
