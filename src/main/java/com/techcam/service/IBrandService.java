package com.techcam.service;

import com.techcam.dto.request.brand.BrandAddRequestDTO;
import com.techcam.dto.request.brand.BrandEditRequestDTO;
import com.techcam.dto.response.brand.BrandResponse;

import java.util.List;

/**
 * Project_name : SMW_TECHCAM
 *
 * @author : XuShiTa
 * @version : 1.0
 * @since : 23.1.2022
 * Description :
 */
public interface IBrandService {
    List<BrandResponse> getAllBrand();

    BrandResponse findById(String id);

    String addBrand(BrandAddRequestDTO brandAddRequestDTO);

    String editBrand(BrandEditRequestDTO brandEditRequestDTO);

    Integer findByEmailaAndDeleteFlagIsFalse(String email);

    Integer findByPhoneAndDeleteFlagIsFalse(String phone);

    // Delete brand
    boolean deleteBrand(String id);

    // Block/Unblock brand by status
    boolean changeStatusBrand(String id, String status);
}
