package com.skx.common.utils

import android.app.usage.StorageStatsManager
import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.os.storage.StorageManager
import android.text.TextUtils
import android.util.Log
import androidx.annotation.WorkerThread
import java.io.IOException
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
     *
     *
     * @return 空间总大小
     */
    @WorkerThread
    fun getTotalStorageSize(context: Context): Long {
        var totalSize: Long = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val storageStatsManager =
                context.getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager
            try {
                totalSize = storageStatsManager.getTotalBytes(StorageManager.UUID_DEFAULT) //总空间大小
                Log.d(
                    TAG,
                    "#获取设备总存储空间大小. StorageStatsManager.getTotalBytes = $totalSize"
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

        // 获取内部存储根目录
        val path = Environment.getDataDirectory()
        // 系统的空间描述类
        val stat = StatFs(path.path)
        // 每个区块的大小，单位：字节
        val blockSize = stat.blockSizeLong
        // 区块总数
        val blockCount = stat.blockCountLong
        Log.i(TAG, "#获取设备总存储空间大小.getDataDirectory blockCount*blockSize = " + blockCount * blockSize)

        val root = Environment.getRootDirectory()
        val sf = StatFs(root.path)
        val blockSize1 = sf.blockSizeLong
        val blockCount1 = sf.blockCountLong

        Log.d(TAG, "获取设备总存储空间大小.getRootDirectory blockCount*blockSize=" + blockCount1 * blockSize1)
        return totalSize
    }


    /**
     * 获取手机内部空间总大小（单位：字节）
     *
     * @param unit 单位，支持：GB、MB、KB
     * @return 空间总大小
     */
    @WorkerThread
    fun getTotalStorageSize(context: Context, unit: String): Long {
        var totalSize = getTotalStorageSize(context)
        if (TextUtils.isEmpty(unit)) {
            return totalSize
        }
        when (unit.lowercase(Locale.getDefault())) {
            "gb" -> totalSize /= (1000 * 1000 * 1000)
            "mb" -> totalSize /= (1000 * 1000)
            "kb" -> totalSize /= 1000
            else -> {}
        }
        return totalSize
    }


    /**
     * 获取手机内部可用空间大小
     *
     * @return 大小
     */
    @WorkerThread
    fun getAvailStorageSize(context: Context): Long {
        var availSize: Long = 0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val storageStatsManager =
                context.getSystemService(Context.STORAGE_STATS_SERVICE) as StorageStatsManager
            try {
                // 剩余空间大小
                availSize = storageStatsManager.getFreeBytes(StorageManager.UUID_DEFAULT)
                Log.d(
                    TAG,
                    "#获取可用存储空间. StorageStatsManager.getFreeBytes = $availSize"
                )
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (availSize <= 0) {
            val path = Environment.getDataDirectory()
            val stat = StatFs(path.path)
//            val blockSize = stat.blockSizeLong
//            // 获取可用区块数量
//            val availableBlocks = stat.availableBlocksLong
//            Log.d(TAG, "#获取可用存储空间.data availableBlocks*blockSize = ${availableBlocks * blockSize}")
            availSize = stat.availableBytes
            Log.d(TAG, "#获取可用存储空间.data availableBytes = ${stat.availableBytes}")
        }
        return availSize
    }

    /**
     * 获取手机内部可用空间大小
     *
     * @return 大小，字节为单位
     */
    @WorkerThread
    fun getAvailableStorageSize(context: Context, unit: String): Long {
        var availStorage = getAvailStorageSize(context)
        if (TextUtils.isEmpty(unit)) {
            return availStorage
        }
        when (unit.lowercase(Locale.getDefault())) {
            "gb" -> availStorage /= (1000 * 1000 * 1000)
            "mb" -> availStorage /= (1000 * 1000)
            "kb" -> availStorage /= 1000
            else -> {}
        }
        return availStorage
    }
}