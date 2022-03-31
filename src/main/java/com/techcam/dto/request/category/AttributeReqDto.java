package com.techcam.dto.request.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class AttributeReqDto {
 private String id;
 private String name;
 private String value;
}
