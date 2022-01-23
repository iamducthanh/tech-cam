package com.techcam.techcam.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * Description :
 *
 * @author : quang
 * @version : 1.0
 * @since : 1/23/2022 8:30 PM
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
            // TODO throw new exception lỗi thời gian không đúng
            e.printStackTrace();
        }
    }
}
