package com.techcam.dto.request.category;

import lombok.*;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 3/27/2022 4:37 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AttributeReqDto {
 private String id;
 private String name;
 private String value;
}
