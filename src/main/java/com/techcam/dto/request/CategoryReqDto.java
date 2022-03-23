package com.techcam.dto.request;

import lombok.*;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 3/23/2022 10:11 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CategoryReqDto {
 private String categoryId;
 private String categoryName;
 private String parentId;
}
