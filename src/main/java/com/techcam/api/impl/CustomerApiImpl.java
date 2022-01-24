package com.techcam.api.impl;

import com.techcam.api.CustomerApi;
import com.techcam.dto.request.CustomerDto;
import com.techcam.exception.ErrorMessage;
import com.techcam.exception.ErrorMessageLoader;
import com.techcam.exception.TechCamExp;
import com.techcam.type.ErrorCodeConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author: GMO_DuyDV
 * @version: 1.0
 * @since: 1/23/2022
 * Project_name: GMO_QuanLyTaiSan
 */

@RestController
public class CustomerApiImpl implements CustomerApi {
    @Override
    public CustomerDto postSaveCustomer() {
        System.out.println(ErrorMessageLoader.getMessage("TC-004-001"));
        return null;
    }

    @Override
    public ErrorMessage test() {
        throw  new TechCamExp(ErrorCodeConstant.TEST_ERROR);
    }
}
