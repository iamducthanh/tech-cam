package com.techcam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author: ThanhND
 * @version: 1.0
 * @since 2/4/2022 3:20 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDto {
    private String passwordOld;
    private String passwordNew;
    private String passwordComfirm;
}
