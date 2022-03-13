package com.techcam.service;

import com.techcam.dto.response.category.CategoryResponse;

import java.util.List;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * Description :
 */
public interface ICategoryService {

    List<CategoryResponse> getAllCategory();

}
