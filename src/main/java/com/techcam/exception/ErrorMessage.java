package com.techcam.exception;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author: GMO_DuyDV
 * @version: 1.0
 * @since: 1/23/2022
 * Project_name: GMO_QuanLyTaiSan
 */

@Data
public class ErrorMessage {
    private String vn;
    private String en;
    private String code;
}
