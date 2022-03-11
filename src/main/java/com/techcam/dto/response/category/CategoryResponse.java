package com.techcam.dto.response.category;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 2/27/2022 4:08 PM
 * Project_name : tech-cam
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {

    private String categoryId;

    private String categoryName;

}
