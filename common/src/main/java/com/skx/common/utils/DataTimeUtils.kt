package com.skx.common.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * 描述 : 日期时间工具类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/2/23 1:29 上午
 */
object DataTimeUtils {


    /**
     * 获取默认时区下的当前的日期
     *
     * @return 当前日期
     */
    fun getNow(): Date {
        return getNow(TimeZone.getDefault())
    }

    /**
     * 获取制定时区下的当前的日期
     *
     * @return 当前日期
     */
    fun getNow(timeZone: TimeZone): Date {
        return Calendar.getInstance(timeZone).time
    }

    /**
     * 获得默认时区的当前时间
     *
     * @param format - 转换格式（yyyy-MM-dd）
     * @return
     */
    fun getNowByDefaultTz(format: String): String? {
        val date = getNow()
        val fmt = SimpleDateFormat(format, Locale.getDefault())
        fmt.timeZone = TimeZone.getDefault()
        return fmt.format(date)
    }

    /**
     * 获得指定时区的当前时间,返回指定格式的字符串形式
     *
     * @param timeZoneId - 时区id（例如：GMT-8:00）
     * @param format - 转换格式（例如：yyyy-MM-dd）
     * @return
     */
    fun getDateTextByTz(timeZoneId: String, format: String): String? {
        val timeZone = TimeZone.getTimeZone(timeZoneId)
        val date = getNow(timeZone)
        val fmt = SimpleDateFormat(format, Locale.getDefault())
        fmt.timeZone = timeZone
        return fmt.format(date)
    }


    /**
     * 计算两个日期之间相差的自然日天数
     * 注：默认采用相同时区！！！
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @param format   日期格式，如：yyyy-MM-dd
     * @return
     */
    fun getDiffBetween2Dates(startDate: String, endDate: String, format: String): Int {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        try {
            val millionSecondsStart = sdf.parse(startDate).time // 毫秒
            val millionSecondsEnd = sdf.parse(endDate).time // 毫秒
            return ((millionSecondsEnd - millionSecondsStart) / 1000 / (60 * 60 * 24)).toInt()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return 0
    }

    /**
     * 计算两个日期之间相差的自然日天数。
     * 注：默认采用相同时区！！！
     * the milliseconds since January 1, 1970, 00:00:00 GMT.
     * @param   timeStart  开始时间
     * @param   timeEnd  结束时间
     */
    fun getDiffBetween2Dates(timeStart: Long, timeEnd: Long): Int {
        val startDate = Date(timeStart)
        val endDate = Date(timeEnd)
        try {
            val millionSecondsStart = startDate.time // 毫秒
            val millionSecondsEnd = endDate.time // 毫秒
            return ((millionSecondsEnd - millionSecondsStart) / 1000 / (60 * 60 * 24)).toInt()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return 0
    }

    /**
     * 计算两个日期之间相差的自然日天数
     */
    fun getDiffBetween2Dates(startDate: Date?, endDate: Date?): Int {
        if (startDate != null && endDate != null) {
            val millionSecondsStart = startDate.time // 毫秒
            val millionSecondsEnd = endDate.time // 毫秒
            return ((millionSecondsEnd - millionSecondsStart) / 1000 / (60 * 60 * 24)).toInt()
        }
        return 0
    }


    /**
     * 转换日期格式。
     * 注：采用默认时区
     *
     * @param time        时间 例如：2020-09-08
     * @param patternFrom 输入格式：如 yy-MM-dd
     * @param patternTo   输出格式：如 yy.MM.dd
     * @return 转换格式后的日期
     */
    fun formateTime(time: String, patternFrom: String?, patternTo: String?): String? {
        val simpleDateFormat = SimpleDateFormat(patternFrom, Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getDefault()
        return try {
            val date = simpleDateFormat.parse(time)
            simpleDateFormat.applyPattern(patternTo)
            date?.run {
                simpleDateFormat.format(date)
            } ?: kotlin.run { time }
        } catch (e: ParseException) {
            e.printStackTrace()
            time
        }
    }

    /**
     * Date 转 字符串日期
     * @param date 目标日期
     * @param format 格式
     */
    fun convert2Date(date: String, format: String): Date? {
        val fmt = SimpleDateFormat(format, Locale.getDefault())
        fmt.timeZone = TimeZone.getTimeZone("GMT")
        return fmt.parse(date)
    }


    /**
     * Date 转 字符串日期。
     * 注：采用默认时区
     *
     * @param date 目标日期
     * @param format 格式
     */
    fun convert2String(date: Date, format: String): String? {
        val fmt = SimpleDateFormat(format, Locale.getDefault())
        fmt.timeZone = TimeZone.getTimeZone("GMT")
        return fmt.format(date)
    }

    /**
     * 是否超过12点
     *
     * @return 是否超过12点
     */
    fun isOverNoon(): Boolean {
        // todo 待完善
        val time = System.currentTimeMillis()
        val mCalendar = Calendar.getInstance()
        mCalendar.timeInMillis = time
        val apm = mCalendar[Calendar.AM_PM]
        return apm == 1
    }


}