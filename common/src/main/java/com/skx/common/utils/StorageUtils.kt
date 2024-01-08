package com.skx.common.utils

import android.app.usage.StorageStatsManager
import android.content.Context
import android.os.Build
import android.os.Environment
import android.os.StatFs
import android.os.storage.StorageManager
import android.util.Log
import androidx.annotation.WorkerThread


/**
 * 描述 : 存储信息工具类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/6/14 12:04 上午
 */
object StorageUtils {
    const val TAG = "StorageUtils"

    /**
     * 获取设备存储空间大小（单位：字节）
     * 注：需要 android.Manifest.permission.READ_EXTERNAL_STORAGE 权限
     *
     * @return Pair对象。first：空间总大小；second：可用空间大小
     */
    @WorkerThread
    fun getDeviceStorageBytes(context: Context): Pair<Long, Long> {
        var totalSize: Long = 0
        var availSize: Long = 0

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val storageStatsManager =
                context.getSystemService(Context.STORAGE_STATS_SERVICE) as? StorageStatsManager
            try {
                storageStatsManager?.run {
                    totalSize = this.getTotalBytes(StorageManager.UUID_DEFAULT)
                    availSize = this.getFreeBytes(StorageManager.UUID_DEFAULT)
                }
                Log.d(
                    TAG,
                    "#获取设备存储空间大小. by StorageStatsManager. totalSize=$totalSize, availSize=$availSize"
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        } else {
            try {
                // 获取内部存储根目录
                val dataPath = Environment.getDataDirectory()
                // 系统的空间描述类
                val dataStat = StatFs(dataPath.path)
                totalSize = dataStat.totalBytes
                availSize = dataStat.availableBytes
                Log.d(
                    TAG,
                    "#获取设备存储空间大小. by StatFs. totalSize=$totalSize, availSize=$availSize"
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        return Pair(totalSize, availSize)
    }

    /**
     * 获取手机内部空间总大小（单位：字节）
     * android 需要 android.Manifest.permission.READ_EXTERNAL_STORAGE 权限
     * @return 空间总大小
     */
    @WorkerThread
    fun getTotalStorageBytes(context: Context): Long {
        return getDeviceStorageBytes(context).first
    }

    /**
     * 获取手机内部空间总大小
     *
     * @param unit 单位，支持：GB、MB、KB，默认为GB
     * @return 空间总大小，四舍五入保留两位小数
     */
    @WorkerThread
    fun getTotalStorageSize(context: Context, unit: String = "GB"): Float {
        return ByteFormatter.format(getTotalStorageBytes(context), unit)
    }

    /**
     * 获取手机内部可用空间大小（单位：字节）
     * 需要 android.Manifest.permission.READ_EXTERNAL_STORAGE 权限
     *
     * @return 可用空间大小
     */
    @WorkerThread
    fun getAvailStorageBytes(context: Context): Long {
        return getDeviceStorageBytes(context).second
    }

    /**
     * 获取手机内部剩余可用空间大小
     *
     * @param unit 单位，支持：GB、MB、KB，默认为GB
     * @return 剩余可用空间大小，四舍五入保留两位小数
     */
    @WorkerThread
    fun getAvailableStorageSize(context: Context, unit: String = "GB"): Float {
        return ByteFormatter.format(getAvailStorageBytes(context), unit)
    }


    /**
     * sd卡是否存在并且已经挂在成功
     *
     * @return true:存在并且已经挂载在可以读写的装载点
     */
    private fun hasSdCard(): Boolean {
        val mounted = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)
        if (!mounted) {
            Log.d(TAG, "hasSdCard=false, not found sd card!")
        }
        return mounted
    }
}