package com.techcam.dto.request.product;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 3/6/2022 12:01 PM
 * Project_name : tech-cam
 */

@Getter
@Setter
public class ProductSearchRequest {

    private List<String> lstProperties;

    private List<String> lstFixedValues;

    private List<String> lstBrand;

    private List<String> lstSupplier;

    private String productMinMoney;

    private String productMaxMoney;

    private String productStatus;

}
