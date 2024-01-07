package com.skx.common.utils

import android.os.Build
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*

/**
 * 描述 : 字节转换其他单位计量单位工具类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2024/1/7 11:49 下午
 */
object ByteFormatter {

    /**
     * 格式转换
     * @param bytes 字节大小
     * @param unit 单位，支持：GB、MB、KB，默认为GB
     * @return 格式转换后的大小，四舍五入保留两位小数
     */
    fun format(bytes: Long, unit: String = "GB"): Float {
        // 区分不同进制是因为在8.0以后google提供了用于面向用户展示的方法，返回的数是虚数。如：32000000000
        val k = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) 1000L else 1024L
        val n: Long = when (unit.lowercase(Locale.getDefault())) {
            "gb" -> k * k * k
            "mb" -> k * k
            "kb" -> k
            else -> 1
        }
        return BigDecimal(bytes)
            .divide(BigDecimal(n), 2, RoundingMode.HALF_UP)
            .toFloat()
    }
}