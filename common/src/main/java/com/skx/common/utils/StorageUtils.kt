package com.skx.common.utils

import android.app.usage.StorageStatsManager
import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.os.storage.StorageManager
import android.util.Log
import androidx.annotation.WorkerThread
import java.io.IOException
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.*


/*
 * 描述 : 存储信息工具类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/6/14 12:04 上午
 */

object StorageUtils {
    const val TAG = "StorageUtils"

    /**
     * 获取手机内部空间总大小（单位：字节）
     * android 需要 android.Manifest.permission.READ_EXTERNAL_STORAGE 权限
     * @return 空间总大小
     */
    @WorkerThread
    fun getTotalStorageBytes(context: Context): Long {
        var totalSize: Long = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val storageStatsManager =
                context.getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager
            try {
                totalSize = storageStatsManager.getTotalBytes(StorageManager.UUID_DEFAULT)
                Log.d(TAG, "#获取设备总存储空间大小. StorageStatsManager.getTotalBytes=$totalSize")
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            try {
                // 获取内部存储根目录
                val dataPath = Environment.getDataDirectory()
                // 系统的空间描述类
                val dataStat = StatFs(dataPath.path)
                totalSize = dataStat.totalBytes
                Log.d(TAG, "#获取设备总存储空间大小. StatFs.totalBytes=$totalSize")

            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
        return totalSize
    }

    /**
     * 获取手机内部空间总大小
     *
     * @param unit 单位，支持：GB、MB、KB
     * @return 空间总大小，四舍五入保留两位小数
     */
    @WorkerThread
    fun getTotalStorageSize(context: Context, unit: String = "GB"): Float {
        return format(getTotalStorageBytes(context), unit)
    }

    /**
     * 获取手机内部可用空间大小（单位：字节）
     * 需要 android.Manifest.permission.READ_EXTERNAL_STORAGE 权限
     *
     * @return 可用空间大小
     */
    @WorkerThread
    fun getAvailStorageBytes(context: Context): Long {
        var availSize: Long = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val storageStatsManager =
                context.getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager
            try {
                // 剩余空间大小
                availSize = storageStatsManager.getFreeBytes(StorageManager.UUID_DEFAULT)
                Log.d(
                    TAG,
                    "#获取可用存储空间. StorageStatsManager.getFreeBytes=$availSize"
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }
        } else {
            try {
                val path = Environment.getDataDirectory()
                val stat = StatFs(path.path)
//            val blockSize = stat.blockSizeLong
//            // 获取可用区块数量
//            val availableBlocks = stat.availableBlocksLong
                availSize = stat.availableBytes
                Log.d(TAG, "#获取可用存储空间. StatFs.availableBytes=$availSize")
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return availSize
    }

    /**
     * 获取手机内部可用空间大小
     *
     * @return 大小，字节为单位
     */
    @WorkerThread
    fun getAvailableStorageSize(context: Context, unit: String): Float {
        return format(getAvailStorageBytes(context), unit)
    }

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