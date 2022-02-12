package com.techcam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 2/12/2022 5:24 PM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordDto {
 private String email;
 private String code;
}
