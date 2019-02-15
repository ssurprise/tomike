package com.skx.tomikecommonlibrary.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 描述 : 日期时间转换工具类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019/2/15 4:16 PM
 */
public class DateFormatUtils {

    /**
     * Date 转日期格式字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        String strDate = null;

        try {
            SimpleDateFormat f = new SimpleDateFormat(pattern, Locale.getDefault());
            strDate = f.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strDate;
    }

    /**
     * 日期格式字符串 转 Date
     *
     * @param dateStr
     * @param formatStr
     * @return
     */
    public static Date parse(String dateStr, String formatStr) throws ParseException {
        if (TextUtils.isEmpty(dateStr)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr, Locale.getDefault());
        return sdf.parse(dateStr);
    }


}
