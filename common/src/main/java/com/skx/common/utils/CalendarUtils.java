package com.skx.common.utils;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarUtils {

    /**
     * desc 获取一个Calendar对象
     *
     * @param timeZone 时区
     * @param year     年
     * @param month    月
     * @param day      日
     */
    public static Calendar getCalendar(TimeZone timeZone, int year, int month, int day) {
        if (timeZone == null) {
            timeZone = TimeZone.getTimeZone("GMT+08:00");
        }
        Calendar calendar = Calendar.getInstance(timeZone);
        if (year > 0 && month > 0 && day > 0) {
            calendar.set(year, month, day);
        }
        return calendar;
    }

    /**
     * desc 获取一个Calendar对象
     *
     * @param timeZone 时区
     * @param date     传入的date
     */
    public static Calendar getCalendar(TimeZone timeZone, Date date) {
        if (timeZone == null) {
            timeZone = TimeZone.getTimeZone("GMT+08:00");
        }
        if (date == null) {
            date = new Date();
        }
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(date);
        return calendar;
    }

    /**
     * desc 获取当前时间
     *
     * @param pattern 时间格式
     * @return 返回当前的时间
     */
    public static String getCurrentTime(String pattern) {
        return formatDateToString(pattern, Locale.CHINA, null);
    }

    /**
     * desc 将date转换成string，格式为patter，Locale为locale
     *
     * @param pattern 格式，默认为"yyyy-MM-dd"
     * @param locale  Locale，默认为Locale.CHINA
     * @param date    Date对象yy-MM-dd"
     */
    public static String formatDateToString(String pattern, Locale locale, Date date) {
        if (TextUtils.isEmpty(pattern)) {
            pattern = "yyyy-MM-dd";
        }
        if (locale == null) {
            locale = Locale.CHINA;
        }
        if (date == null) {
            date = new Date();
        }
        return new SimpleDateFormat(pattern, locale).format(date);
    }

    /**
     * desc  将字符串转换成Date
     *
     * @param date    字符串date
     * @param pattern 字符串的格式
     * @param locale  Locale
     */
    public static Date formatStringToDate(String date, String pattern, Locale locale) {
        if (TextUtils.isEmpty(pattern)) {
            pattern = "yyyy-MM-dd";
        }
        if (locale == null) {
            locale = Locale.CHINA;
        }
        Date d = null;
        try {
            d = new SimpleDateFormat(pattern, locale).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * desc 将字符串日期的格式进行转换
     *
     * @param date       字符串date
     * @param oldPattern 原来的pattern
     * @param newPattern 新的pattern
     * @param locale     locale
     */
    public static String formatDateStringWithPattern(String date, String oldPattern, String newPattern, Locale locale) {
        if (TextUtils.isEmpty(newPattern)) {
            newPattern = "yyyy-MM-dd";
        }
        if (locale == null) {
            locale = Locale.CHINA;
        }
        return formatDateToString(newPattern, locale, formatStringToDate(date, oldPattern, locale));
    }

    /**
     * desc 获取传入的参数中，day of year
     *
     * @param year  年
     * @param month 月
     * @param day   日
     */
    public static int getDayOfYear(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * desc 获取传入的参数中，day of year
     */
    public static int getDayOfYear(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * desc 获取该天是所在月、周的第几天
     */
    public static int getDayOfWeek(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static int getWeekOfMonth(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * desc 获取传入月份的天数
     *
     * @param year  年份
     * @param month 月份
     * @return 天数
     */
    public static int getDaysCount(int year, int month) {
        int codeMonth = month - 1;
        switch (codeMonth) {
            case Calendar.JANUARY:
            case Calendar.MARCH:
            case Calendar.MAY:
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.OCTOBER:
            case Calendar.DECEMBER:
                return 31;
            case Calendar.APRIL:
            case Calendar.JUNE:
            case Calendar.SEPTEMBER:
            case Calendar.NOVEMBER:
                return 30;
            case Calendar.FEBRUARY:
                return isLeapYear(year) ? 29 : 28;
            default:
                throw new IllegalArgumentException("Invalid Month");
        }
    }

    /**
     * desc 是否为闰年
     *
     * @param year 年份
     */
    private static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * 根据开始时间和结束时间返回时间段内的时间集合(包含传入的start和end)
     *
     * @param beginDay 开始时间
     * @param endDay   结束时间
     * @return String类型的时间list
     */
    public static ArrayList<String> getDatesBetweenTwoDate(String beginDay, String endDay, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.CHINA);
        Date beginDate = null;
        Date endDate = null;
        try {
            beginDate = format.parse(beginDay);
            endDate = format.parse(endDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList<String> result = new ArrayList<>();
        result.add(beginDay);

        Calendar cal = Calendar.getInstance();
        cal.setTime(beginDate);
        while (true) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
            if (endDate != null && endDate.after(cal.getTime())) {
                result.add(format.format(cal.getTime()));
            } else {
                break;
            }
        }
        result.add(endDay);

        return result;
    }

}
