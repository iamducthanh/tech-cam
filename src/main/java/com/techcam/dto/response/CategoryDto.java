package com.techcam.dto.response;

import com.techcam.dto.request.category.AttributeReqDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description:
 *
 * @author: GMO_ThanhND
 * @version: 1.0
 * @since 3/21/2022 11:19 PM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private String categoryId;
    private String categoryName;
    private List<CategoryDto> categoryChild;
    private List<AttributeReqDto> attributes;
}
