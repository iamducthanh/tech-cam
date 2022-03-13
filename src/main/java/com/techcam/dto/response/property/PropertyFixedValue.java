package com.techcam.dto.response.property;

import lombok.*;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/6/2022 9:02 AM
 * Project_name : tech-cam
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropertyFixedValue {

    private String fixedId;

    private String fixedValue;

}
