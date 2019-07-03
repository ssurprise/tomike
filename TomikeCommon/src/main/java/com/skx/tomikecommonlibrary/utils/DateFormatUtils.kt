package com.skx.tomikecommonlibrary.utils

import android.text.TextUtils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * 描述 : 日期时间转换工具类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019/2/15 4:16 PM
 */
object DateFormatUtils {

    /**
     * Date 转日期格式字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    fun format(date: Date?, pattern: String): String? {
        if (date == null) {
            return ""
        }
        var strDate: String? = null

        try {
            val f = SimpleDateFormat(pattern, Locale.getDefault())
            strDate = f.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return strDate
    }

    /**
     * 日期格式字符串 转 Date
     *
     * @param dateStr
     * @param formatStr
     * @return
     */
    @Throws(ParseException::class)
    fun parse(dateStr: String, formatStr: String): Date? {
        if (TextUtils.isEmpty(dateStr)) {
            return null
        }
        val sdf = SimpleDateFormat(formatStr, Locale.getDefault())
        return sdf.parse(dateStr)
    }


}
