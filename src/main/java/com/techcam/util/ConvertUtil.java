package com.techcam.util;

import com.techcam.constants.ConstantsErrorCode;
import com.techcam.exception.TechCamExp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 8:58 PM
 * Project_name : tech-cam
 */

public class ConvertUtil {

    private static ConvertUtil convertUtil;

    private ConvertUtil() {
    }

    public static ConvertUtil get() {
        if (convertUtil == null) {
            convertUtil = new ConvertUtil();
        }
        return convertUtil;
    }

    public Date strToDate(String startDate, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(startDate);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new TechCamExp(ConstantsErrorCode.ERROR_DATA_REQUEST);
        }
    }

}
