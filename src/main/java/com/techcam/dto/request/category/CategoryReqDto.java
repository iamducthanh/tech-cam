package com.techcam.dto.request.category;

import lombok.*;

import java.util.List;

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
 List<AttributeReqDto> attributes;
}
