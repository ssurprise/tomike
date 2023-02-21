package com.skx.common.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class XZDateTimeUtils {

    public static final String TIME_ZONE_GMT8 = "GMT+08:00";
    private static SimpleDateFormat format = null;

    public static long getNowTimeInMillis() {
        return Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00")).getTimeInMillis();
    }

    public static Date getNow() {
        return Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00")).getTime();
    }

    public strictfp Date getNow(TimeZone timeZone) {
        return Calendar.getInstance(timeZone).getTime();
    }

    public static Date getNowDefault() {
        return Calendar.getInstance().getTime();
    }


    /**
     * 获得默认时区（北京时区）的当前时间
     *
     * @param timeFormat - 转换格式（yyyy-MM-dd）
     * @return
     */
    public static String getNowByDefaultTimeZone(String timeFormat) {
        return getNowByTimeZone("GMT+08:00", timeFormat);
    }

    /**
     * 转换日期格式，注，不涉及时区
     *
     * @param time        时间 例如：2020-09-08
     * @param patternFrom 输入格式：如 yy-MM-dd
     * @param patternTo   输出格式：如 yy.MM.dd
     * @return 转换格式后的日期
     */
    public static String formateTime(String time, String patternFrom, String patternTo) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternFrom);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        try {
            Date date = simpleDateFormat.parse(time);
            simpleDateFormat.applyPattern(patternTo);
            return simpleDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return time;
        }
    }

    /**
     * 根据传入的要求获取手机当前时间
     *
     * @param timeZoneId - 时区号（如：悉尼的GMT+11:00）
     * @param timeFormat - 转换格式（yyyy-MM-dd）
     * @return
     */
    public static String getNowByTimeZone(String timeZoneId, String timeFormat) {
        Calendar cd = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
        if (timeZone != null) {
            cd.setTimeZone(timeZone);
        }
        Date date = Calendar.getInstance(timeZone).getTime();
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        format.setTimeZone(timeZone);
        return format.format(date);
    }

    /**
     * 获取本月第一天
     *
     * @param timeZoneId
     * @param timeFormat
     * @return
     */
    public static String getThisMonthByTimeZone(String timeZoneId, String timeFormat) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
        if (timeZone != null) {
            calendar.setTimeZone(timeZone);
            format.setTimeZone(timeZone);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(calendar.getTime());
    }


    /**
     * 获取当前时间之后第n个月的最后一天
     * <p>
     * attention！！！当前月时间要算，使用时注意n-1
     *
     * @param timeZoneId - 时区号（如：悉尼的GMT+11:00）
     * @param timeFormat - 转换格式（yyyy-MM-dd）
     * @return
     */
    public static String getEndDayOfOneMonth(String timeZoneId, String timeFormat, int n) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        SimpleDateFormat format = new SimpleDateFormat(timeFormat);
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
        if (timeZone != null) {
            calendar.setTimeZone(timeZone);
            format.setTimeZone(timeZone);
        }
        // 设置为当前月第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //n+1个月的第一天
        calendar.add(Calendar.MONTH, n + 1);
        calendar.add(Calendar.DATE, -1);
        return format.format(calendar.getTime());
    }

    /**
     * 往指定日期添加天数
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDay(Date date, int days) {
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 往指定日期添加月数
     *
     * @param date
     * @param months
     * @return
     */
    public static Date addMonth(Date date, int months) {
        if (date == null) {
            return null;
        }
        // Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * 指定日期是星期几
     *
     * @param date
     * @param weekOfDays 自定义星期的显示名称
     * @return
     */
    public static String getDayOfWeek(Date date, String[] weekOfDays) {
        return getDayOfWeek(date, weekOfDays, TimeZone.getTimeZone("GMT+08:00"));
    }

    public static String getDayOfWeek(Date date, String[] weekOfDays, TimeZone timeZone) {
        Calendar cal = Calendar.getInstance(timeZone);
        if (date != null) {
            cal.setTime(date);
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekOfDays[w];
    }

    /**
     * 指定日期是星期几,使用默认显示
     *
     * @param date
     * @return
     */
    public static String getDayOfWeekDefault(Date date) {
        String[] weekOfDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        if (date != null) {
            cal.setTime(date);
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekOfDays[w];
    }


    /**
     * 得到本月中的最后一天
     *
     * @param dateStr
     * @param formatStr
     * @return
     */
    public static int getLastDayOfMonth(String dateStr, String formatStr) {
        Date date = StringToDate(dateStr, formatStr);
        return getLastDayOfMonth(date);
    }

    public static Date StringToDate(String dateStr, String formatStr) {
        if (TextUtils.isEmpty(dateStr)) {
            return null;
        }
        DateFormat sdf = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期转换成Java字符串
     *
     * @param date
     * @return str
     */
    public static String DateToStr(Date date, String formatStr) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        String str = format.format(date);
        return str;
    }

    /**
     * date 转换为年月日格式
     *
     * @param date
     * @return
     */
    public static String getDayString(Date date) {
        return getDayString(date, "yyyy-MM-dd");
    }

    /**
     * date 转换为年月日时分秒格式
     *
     * @param date
     * @return
     */
    public static String getDayTimeString(Date date) {
        return getDayString(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getDayString(Date date, String formatter) {
        if (date == null) {
            return "";
        }
        format = new SimpleDateFormat(formatter);
        // format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return format.format(date);
    }

    /**
     * 日期字符串格式转换
     *
     * @param date
     * @param bformatter 输出前格式
     * @param fformatter 输出后格式
     * @return
     */
    public static String dateStrFormat(String date, String bformatter, String fformatter) {

        return XZDateTimeUtils.getDayString(XZDateTimeUtils.StringToDate(date, bformatter), fformatter);
    }

    /**
     * 计算日期差值 (date)
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int DateDiff(Date d1, Date d2) {
        int diff = 0;
        if (d1 != null && d2 != null) {
            diff = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
        }
        return diff;
    }

    /**
     * 计算日期差值 (String)
     *
     * @param date1
     * @param date2
     * @param formatStr
     * @return
     */
    public static int DateDiff(String date1, String date2, String formatStr) {
        Date d1 = StringToDate(date1, formatStr);
        Date d2 = StringToDate(date2, formatStr);

        int diff = 0;
        if (d1 != null && d2 != null) {
            diff = (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
        }
        return diff;
    }

    /**
     * 比较两个时间的先后
     *
     * @param date1
     * @param date2
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static boolean compareDate(String date1, String date2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        try {
            c1.setTime(df.parse(date1));
            c2.setTime(df.parse(date2));
        } catch (ParseException e) {
            System.err.println("格式不正确");
        }

        int result = c1.compareTo(c2);

        if (result == 0) {
            // System.out.println("c1相等c2");
            return false;

        } else if (result < 0) {
            // System.out.println("c1小于c2");
            return true;
        } else {
            return false;
        }
    }

    /**
     * 比较两个时间的先后
     *
     * @param date1
     * @param date2
     * @return true :date1 < date2 false :date1 >= date2
     */
    @SuppressLint("SimpleDateFormat")
    public static boolean compareDate(String date1, String date2, String formatter) {

        DateFormat df = new SimpleDateFormat(formatter);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        try {
            c1.setTime(df.parse(date1));
            c2.setTime(df.parse(date2));
        } catch (ParseException e) {
            System.err.println("格式不正确");
        }

        int result = c1.compareTo(c2);// c1 > c2 返回1 说明 c1 时间靠后 c1 = c2 返回0

        if (result == 0) {
            // System.out.println("c1相等c2");
            return false;

        } else if (result < 0) {
            // System.out.println("c1<c2");c2时间靠后
            return true;
        } else {
            return false;
        }
    }

    /**
     * 和当前时间进行对比, true表示小于当前时间，false为大于等于
     *
     * @param date
     * @param formatter
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static boolean compareToToday(String date, String formatter) {

        DateFormat df = new SimpleDateFormat(formatter);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        try {
            c1.setTime(df.parse(date));
            c2.setTime(df.parse(getNowByTimeZone("GMT+08:00", formatter)));
        } catch (ParseException e) {
            System.err.println("格式不正确");
        }

        int result = c1.compareTo(c2);// c1 > c2 返回1 说明 c1 时间靠后 c1 = c2 返回0

        if (result == 0) {
            // System.out.println("c1相等c2");
            return false;

        } else if (result < 0) {
            // System.out.println("c1小于c2");
            return true;
        } else {
            return false;
        }
    }

    /**
     * 和当前时间进行对比, true表示小于当前时间，false为大于等于
     *
     * @param date
     * @param timeZone  时区
     * @param formatter 格式化
     * @return
     */
    public static boolean compareToToday(String date, String timeZone, String formatter) {
        SimpleDateFormat df = new SimpleDateFormat(formatter);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        try {
            c1.setTime(df.parse(date));
            if (TextUtils.isEmpty(timeZone)) {
                c2.setTime(df.parse(getNowByTimeZone("GMT+08:00", formatter)));
            } else {
                c2.setTime(df.parse(getNowByTimeZone(timeZone, formatter)));
            }
        } catch (ParseException e) {
        }

        // c1 > c2 返回 1 说明 c1 时间靠后
        // c1 = c2 返回 0 说明 c1 c2 时间为同一天
        // c1 < c2 返回负数 说明 c2 时间靠后
        int result = c1.compareTo(c2);

        if (result == 0) {
            // System.out.println("c1相等c2");
            return false;

        } else if (result < 0) {
            // System.out.println("c1小于c2");
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据出生日期计算年龄
     *
     * @param birth 格式为yyyy-MM-dd timeStamp 格式是系统时间戳(以秒为单位)
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static int getAge(String birth, String timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            long millionSeconds_birth = sdf.parse(birth).getTime();// 毫秒
            int age = (int) ((Long.parseLong(timeStamp) - millionSeconds_birth / 1000) / (60 * 60 * 24 * 365));
            return age;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算两个日期之间相差的自然日天数
     *
     * @param timeStart ---yyyy-MM-dd
     * @param timeEnd   ---yyyy-MM-dd
     * @return
     */
    public static int getDaysCountBetweenDate(String timeStart, String timeEnd) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            long millionSecondsStart = sdf.parse(timeStart).getTime();// 毫秒
            long millionSecondsEnd = sdf.parse(timeEnd).getTime();// 毫秒
            return (int) ((millionSecondsEnd - millionSecondsStart) / 1000 / (60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 计算两个日期之间相差的自然日天数
     */
    public static int getDaysCountBetweenDate(long timeStart, long timeEnd) {
        Date startDate = new Date(timeStart);
        Date endDate = new Date(timeEnd);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String startTime = sdf.format(startDate);
        String endTime = sdf.format(endDate);
        try {
            long millionSecondsStart = sdf.parse(startTime).getTime();// 毫秒
            long millionSecondsEnd = sdf.parse(endTime).getTime();// 毫秒
            return (int) ((millionSecondsEnd - millionSecondsStart) / 1000 / (60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * 计算两个日期之间相差的自然日天数
     */
    public static int getDaysCountBetweenDate(Date timeStart, Date timeEnd) {
        if (timeStart != null && timeEnd != null) {
            long millionSecondsStart = timeStart.getTime();// 毫秒
            long millionSecondsEnd = timeEnd.getTime();// 毫秒
            return (int) ((millionSecondsEnd - millionSecondsStart) / 1000 / (60 * 60 * 24));
        }
        return 0;
    }

    /**
     * @return 同时区内两个时间自然日差
     */
    public static int getNaturalDaysCount(TimeZone timeZone, Date end, Date start) {
        if (end == null || start == null) {
            return 0;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        sdf.setTimeZone(timeZone);
        String endTime = sdf.format(end);
        String startTime = sdf.format(start);
        try {
            long millionSecondsEnd = sdf.parse(endTime).getTime();// 毫秒
            long millionSecondsStart = sdf.parse(startTime).getTime();// 毫秒
            return (int) ((millionSecondsEnd - millionSecondsStart) / 1000 / (60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 判断是否为今天
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     */
    public static boolean isToday(String timeZoneId, String day) {
        try {
            TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            sdf.setTimeZone(timeZone);

            Calendar pre = Calendar.getInstance(timeZone);
            Date predate = new Date(System.currentTimeMillis());
            pre.setTime(predate);
            Calendar cal = Calendar.getInstance();
            Date date = sdf.parse(day);
            cal.setTime(date);
            if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
                int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                        - pre.get(Calendar.DAY_OF_YEAR);

                if (diffDay == 0) {
                    return true;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 字符串转date
     */
    @Nullable
    public static Date stringToDate(String dateStr, String format, @Nullable TimeZone timeZone) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
        if (timeZone != null) {
            sdf.setTimeZone(timeZone);
        }
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断是否早于今天
     *
     * @param selectDay 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     */
    public static boolean isBeforeToday(String timeZoneId, String selectDay) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            //当前时间
            Calendar nowCal = Calendar.getInstance();
            Calendar selectCal = Calendar.getInstance();

            //时区设置
            TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
            if (timeZone != null) {
                nowCal.setTimeZone(timeZone);
                selectCal.setTimeZone(timeZone);
                sdf.setTimeZone(timeZone);
            }

            // 已选择日期
            Date date = sdf.parse(selectDay);
            selectCal.setTime(date);

            if (selectCal.get(Calendar.YEAR) == (nowCal.get(Calendar.YEAR))) {
                int diffDay = selectCal.get(Calendar.DAY_OF_YEAR) - nowCal.get(Calendar.DAY_OF_YEAR);
                if (diffDay < 0) {
                    return true;
                }
            } else if (selectCal.get(Calendar.YEAR) < nowCal.get(Calendar.YEAR)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 是否超过12点
     *
     * @return 是否超过12点
     */
    public static boolean isOverNoon() {
        long time = System.currentTimeMillis();
        final Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(time);
        int apm = mCalendar.get(Calendar.AM_PM);
        return apm == 1;
    }

    /**
     * 格式化时区
     *
     * @param timeZone 时区
     * @return 格林时区
     */
    public static String formatTimeZone(String timeZone) {
        String newTimeZone;
        if (TextUtils.isEmpty(timeZone)) {
            newTimeZone = "GMT+08:00";
        } else {
            if (timeZone.contains("GMT")) {
                newTimeZone = timeZone;
            } else {
                newTimeZone = "GMT" + timeZone;
            }
        }
        return newTimeZone;
    }

    private static int getLastDayOfMonth(Date d) {
        if (d == null) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        // 日，设为一号
        cal.set(Calendar.DATE, 1);
        // 月份加一，得到下个月的一号
        cal.add(Calendar.MONTH, 1);
        // 下一个月减一为本月最后一天
        cal.add(Calendar.DATE, -1);
        return cal.get(Calendar.DAY_OF_MONTH); // 获得月末是几号
    }


    public static int getDayOfWeek(Date date) {
        if (date == null) {
            return 0;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    public static int getDayOfWeek(String dateStr, String formatStr) {
        Date date = StringToDate(dateStr, formatStr);
        return getDayOfWeek(date);
    }

    public static String getDateString(Date date, String timeZone) {
        return getDateString(date, "yyyy-MM-dd", timeZone);
    }

    public static String getDateString(Date date) {
        return getDateString(date, "yyyy-MM-dd", null);
    }

    public static String getDateString(Date date, String formatter, String timeZone) {
        if (date == null) {
            return "";
        }
        if (TextUtils.isEmpty(timeZone)) {
            timeZone = TIME_ZONE_GMT8;
        }
        SimpleDateFormat format = (SimpleDateFormat) SimpleDateFormat.getInstance();
        format.setTimeZone(TimeZone.getTimeZone(timeZone));
        format.applyPattern(formatter);
        return format.format(date);
    }

    /**
     * 字符串转日期
     *
     * @param timeZone
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date getDateByStr(TimeZone timeZone, String dateStr, String pattern) {
        if (TextUtils.isEmpty(dateStr)) {
            return null;
        }
        DateFormat sdf = new SimpleDateFormat(pattern);
        if (timeZone != null) {
            sdf.setTimeZone(timeZone);
        }
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @param formatter
     * @param timeZone
     * @return
     */
    public static String getStrByDate(TimeZone timeZone, Date date, String formatter) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat format = (SimpleDateFormat) SimpleDateFormat.getInstance();
        if (timeZone != null) {
            format.setTimeZone(timeZone);
        }
        format.applyPattern(formatter);
        return format.format(date);
    }
}
