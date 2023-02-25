package com.skx.common.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * 描述 : 日期时间工具类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019/2/15 4:16 PM
 */
object DataTimeUtils {


    /**
     * 获取制定时区下的当前的日期
     *
     * @return 当前日期
     */
    fun getNowDate(timeZone: TimeZone = TimeZone.getDefault()): Date {
        return Calendar.getInstance(timeZone).time
    }


    /**
     * 获得目标时区的当前时间，返回指定格式的字符串形式.
     * 注：默认为当前时区
     *
     * @param format - 转换格式（例如：yyyy-MM-dd）
     * @param timeZone - 时区
     * @return
     */
    fun getNowDateText(format: String,
                       timeZone: TimeZone = TimeZone.getDefault(),
                       locale: Locale = Locale.getDefault()
    ): String? {
        val date = getNowDate(timeZone)
        val fmt = SimpleDateFormat(format, locale)
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
    fun getDiffBetween2Dates(startDate: Date, endDate: Date): Int {
        val millionSecondsStart = startDate.time // 毫秒
        val millionSecondsEnd = endDate.time // 毫秒
        return ((millionSecondsEnd - millionSecondsStart) / 1000 / (60 * 60 * 24)).toInt()
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
    @Throws(ParseException::class)
    fun formateTime(time: String, patternFrom: String, patternTo: String): String {
        val simpleDateFormat = SimpleDateFormat(patternFrom, Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getDefault()
        val date = simpleDateFormat.parse(time)
        simpleDateFormat.applyPattern(patternTo)
        return date?.run {
            simpleDateFormat.format(date)
        } ?: kotlin.run { time }
    }

    /**
     * Date 转 字符串日期
     * @param date 目标日期
     * @param format 格式
     */
    @Throws(ParseException::class)
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
        val time = System.currentTimeMillis()
        val cal = Calendar.getInstance()
        cal.timeInMillis = time
        val apm = cal[Calendar.AM_PM]
        return apm == 1
    }

    /**
     * desc 获取传入月份的天数
     *
     * @param year  年份
     * @param month 月份
     * @return 天数
     */
    fun getDaysCount(year: Int, month: Int): Int {
        return when (month - 1) {
            Calendar.JANUARY,
            Calendar.MARCH,
            Calendar.MAY,
            Calendar.JULY,
            Calendar.AUGUST,
            Calendar.OCTOBER,
            Calendar.DECEMBER -> 31
            Calendar.APRIL,
            Calendar.JUNE,
            Calendar.SEPTEMBER,
            Calendar.NOVEMBER -> 30
            Calendar.FEBRUARY -> if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) 29 else 28
            else -> throw IllegalArgumentException("Invalid Month")
        }
    }

    /**
     * 获取该天是所在月、周的第几天
     *
     * @param year
     * @param month
     * @param day
     */
    fun getDayOfWeek(year: Int, month: Int, day: Int): Int {
        val calendar = Calendar.getInstance()
        calendar[year, month - 1] = day
        return calendar[Calendar.DAY_OF_WEEK]
    }


    fun getDayOfWeek(date: Date): Int {
        val cal = Calendar.getInstance()
        cal.time = date
        return cal[Calendar.DAY_OF_WEEK]
    }

    @Throws(ArrayIndexOutOfBoundsException::class)
    fun getDayOfWeek(date: Date,
                     weekOfDays: Array<String>,
                     timeZone: TimeZone = TimeZone.getDefault()
    ): String {
        val cal = Calendar.getInstance(timeZone)
        cal.time = date

        var w = cal[Calendar.DAY_OF_WEEK] - 1
        if (w < 0) {
            w = 0
        }
        return weekOfDays[w]
    }


    fun getWeekOfMonth(year: Int, month: Int, day: Int): Int {
        val calendar = Calendar.getInstance()
        calendar[year, month - 1] = day
        return calendar[Calendar.WEEK_OF_MONTH]
    }

    /**
     * 获取传入的参数中，day of year
     *
     * @param year  年
     * @param month 月
     * @param day   日
     */
    fun getDayOfYear(year: Int, month: Int, day: Int): Int {
        val calendar = Calendar.getInstance()
        calendar[year, month - 1] = day
        return calendar[Calendar.DAY_OF_YEAR]
    }

    /**
     * 获取传入的参数中，day of year
     */
    fun getDayOfYear(calendar: Calendar): Int {
        return calendar[Calendar.DAY_OF_YEAR]
    }

    /**
     * 获取本月第一天
     *
     * @param timeFormat
     * @return
     */
    fun getFirstDayOfMonth(timeFormat: String?,
                           zone: TimeZone = TimeZone.getDefault(),
                           locale: Locale = Locale.getDefault()
    ): String? {
        val calendar = Calendar.getInstance(zone)
        val format = SimpleDateFormat(timeFormat, locale)
        format.timeZone = zone
        calendar[Calendar.DAY_OF_MONTH] = 1
        return format.format(calendar.time)
    }

    /**
     * 获取目标所在月份的最后一天
     *
     * @param date 目标日期
     * @return 目标日期最后月份的最后一天是几号
     */
    fun getLastDayOfMonth(date: Date, zone: TimeZone = TimeZone.getDefault()): Int {
        val cal = Calendar.getInstance(zone)
        cal.time = date
        // 设置为当前月第一天
        cal[Calendar.DATE] = 1
        // 月份加一，得到下个月的一号
        cal.add(Calendar.MONTH, 1)
        // 下一个月减一为本月最后一天
        cal.add(Calendar.DATE, -1)
        return cal[Calendar.DAY_OF_MONTH] // 获得月末是几号
    }


    /**
     * 获取当前时间之后第n个月的最后一天
     *
     *
     * attention！！！当前月时间要算，使用时注意n-1
     *
     * @param timeFormat    - 转换格式（yyyy-MM-dd）
     * @param n             - n个月
     * @param timeZone      - 时区
     * @return
     */
    fun getLastDayOfOneMonth(timeFormat: String,
                             n: Int,
                             timeZone: TimeZone = TimeZone.getDefault(),
                             locale: Locale = Locale.getDefault()
    ): String? {
        val format = SimpleDateFormat(timeFormat, locale)
        format.timeZone = timeZone

        val calendar = Calendar.getInstance(timeZone)
        // 设置为当前月第一天
        calendar[Calendar.DAY_OF_MONTH] = 1
        // n+1个月的第一天
        calendar.add(Calendar.MONTH, n + 1)
        calendar.add(Calendar.DATE, -1)
        return format.format(calendar.time)
    }

    /**
     * 往指定日期添加天数
     *
     * @param date
     * @param days
     * @return
     */
    fun addDay(date: Date, days: Int, zone: TimeZone = TimeZone.getDefault()): Date {
        val cal = Calendar.getInstance(zone)
        cal.time = date
        cal.add(Calendar.DATE, days)
        return cal.time
    }

    /**
     * 往指定日期添加月数
     *
     * @param date
     * @param months
     * @return
     */
    fun addMonth(date: Date, months: Int, zone: TimeZone = TimeZone.getDefault()): Date {
        val cal = Calendar.getInstance(zone)
        cal.time = date
        cal.add(Calendar.MONTH, months)
        return cal.time
    }

    /**
     * 判断是否为今天
     *
     * @param date 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true：是今天 false：不是今天
     */
    @Throws(ParseException::class)
    fun isToday(date: String,
                format: String = "yyyy-MM-dd",
                timeZone: TimeZone = TimeZone.getDefault()
    ): Boolean {
        val fmt = SimpleDateFormat(format, Locale.getDefault())
        fmt.timeZone = timeZone

        //当前时间
        val nowCal = Calendar.getInstance(timeZone)

        // 目标日期
        val targetCal = Calendar.getInstance(timeZone)
        targetCal.time = fmt.parse(date)

        return nowCal.compareTo(targetCal) == 0
    }


    /**
     * 判断是否早于今天
     *
     * @param date 目标日期
     * @return true今天 false不是
     */
    @Throws(ParseException::class)
    fun isBeforeToday(date: String,
                      format: String = "yyyy-MM-dd",
                      timeZone: TimeZone = TimeZone.getDefault()
    ): Boolean {
        val fmt = SimpleDateFormat(format, Locale.getDefault())
        fmt.timeZone = timeZone

        // 当前时间
        val nowCal = Calendar.getInstance(timeZone)

        // 目标日期
        val targetCal = Calendar.getInstance(timeZone)
        targetCal.time = fmt.parse(date)

        return targetCal < nowCal
    }


    /**
     * 比较两个时间的先后.
     *
     * @param date1 日期1
     * @param date2 日期2
     * @return  如果参数date1 表示的时间等于date2表示的时间，则值为 0；
     *          如果参数date1 表示的时间在date2表示的时间之前，则值小于0；
     *          如果参数date1 表示的时间在date2表示的时间之后，则值大于0。
     */
    @Throws(ParseException::class)
    fun compareDate(date1: String,
                    date2: String,
                    format: String = "yyyy-MM-dd",
                    timeZone: TimeZone = TimeZone.getDefault()
    ): Int {
        val fmt: DateFormat = SimpleDateFormat(format, Locale.getDefault())
        val c1 = Calendar.getInstance(timeZone)
        val c2 = Calendar.getInstance(timeZone)

        fmt.timeZone = timeZone
        c1.time = fmt.parse(date1)
        c2.time = fmt.parse(date2)

        return c1.compareTo(c2)
    }

}