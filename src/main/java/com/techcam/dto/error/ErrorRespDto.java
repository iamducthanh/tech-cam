package com.techcam.dto.error;

import lombok.*;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 9:09 PM
 * Project_name : tech-cam
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ErrorRespDto {

    private String message;

    private String code;

    private String field;

}
