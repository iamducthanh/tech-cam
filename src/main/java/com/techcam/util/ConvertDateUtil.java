package com.techcam.util;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 *
 * @author: POLY_DuyDVPH12712
 * @version: 1.0
 * @since: 3/24/2022
 * Project_name: Tech-cam
 */

public class ConvertDateUtil {
    private final static String DATE_TIME = "dd-MM-yyyy hh:mm:ss";

    //    private final static String DATE_UTIL="yyyy"
    public static String convertDateTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME);
        return format.format(date);
    }

    public static String convertDateTime(Date date, String patient) {
        SimpleDateFormat format = new SimpleDateFormat(patient);
        return format.format(date);
    }

    public static String generationCode(String startCode) {
        return startCode + convertDateTime(new Date(), "yyyyMMdd") + Integer.parseInt(RandomStringUtils.randomNumeric(4));
    }
}
