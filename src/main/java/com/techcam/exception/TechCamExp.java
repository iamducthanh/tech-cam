package com.techcam.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.connector.Response;


/**
 * Description:
 *
 * @author: GMO_DuyDV
 * @version: 1.0
 * @since: 1/23/2022
 * Project_name: GMO_QuanLyTaiSan
 */

@Data
public class TechCamExp extends RuntimeException {

    private ErrorMessage errorMessage;

    public TechCamExp(String errorCode) {
        this.errorMessage = ErrorMessageLoader.getMessage(errorCode);
    }

    public TechCamExp(String errorCode, Object... args) {
        this.errorMessage = ErrorMessageLoader.getMessage(String.format(errorCode, args));
    }

}
