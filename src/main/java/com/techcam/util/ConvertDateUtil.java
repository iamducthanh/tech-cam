package com.techcam.util;

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
    private final static String DATE_TIME="dd-M-yyyy hh:mm:ss";
    public static String convertDateTime(Date date){
        SimpleDateFormat format = new SimpleDateFormat(DATE_TIME);
        return format.format(date);
    }
}
