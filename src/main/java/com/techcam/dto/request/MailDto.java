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
 * @since 2/12/2022 5:32 PM
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailDto {
 private String from;
 private String to;
 private String subject;
 private String body;
}
