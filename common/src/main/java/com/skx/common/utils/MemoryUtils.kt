package com.skx.common.utils

import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.content.Context
import android.util.Log
import androidx.annotation.WorkerThread
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.util.*

/**
 * 描述 : 内存信息工具类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/6/14 12:04 上午
 */
object MemoryUtils {
    const val TAG = "MemoryUtils"

    /**
     * 获取设备的总内存大小，单位：Kb
     * 注：此方法读取的是系统文件中记录的大小（/proc/meminfo）
     *
     * @return 设备的总内存大小
     */
    @WorkerThread
    fun getTotalMem(): Long {
        /*
        注：该方法获取的不全
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memInfo);
         return memInfo.totalMem;
         */
        val arrayOfString: Array<String>
        var bufferedReader: BufferedReader? = null
        var totalMem: Long = 0
        try {
            val fileReader = FileReader("/proc/meminfo")
            bufferedReader = BufferedReader(fileReader, 4096)
            val str = bufferedReader.readLine() // 读取meminfo文件的第一行，系统总内存大小
            Log.w(TAG, "#getTotalMemory, meminfo file, content=$str")
            // 读取到的数据示例-> "MemTotal:        1894960 kB"
            arrayOfString =
                str.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            for (num in arrayOfString) {
                Log.w(TAG, "${num}\t")
            }
            // 获得系统总内存，单位是KB
            totalMem = arrayOfString[1].toInt().toLong()
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                bufferedReader?.close()
            } catch (ex: IOException) {
                ex.printStackTrace()
            }
        }
        return totalMem
    }

    /**
     * 获取设备的全部内存。
     * 注：考虑到内存厂商在生产过程中的计量单位常为1000，因此在不同的单位之间换算时取的单位为：1000 而非1024.
     *
     * @param unit 单位：GB、MB、KB。默认：GB
     * @return 对应单位返回的值
     */
    fun getTotalMemory(unit: String = "GB"): Float {
        var totalMemory = getTotalMem() * 1.00f
        when (unit.lowercase(Locale.getDefault())) {
            "gb" -> totalMemory /= (1000 * 1000)
            "mb" -> totalMemory /= 1000
            else -> {}
        }
        return totalMemory
    }


    /**
     * 获取设备的可用内存大小，单位：字节(byte)
     *
     * @param context 上下文
     * @return 可用内存大小
     */
    fun getAvailMem(context: Context): Long {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memInfo)
        return memInfo.availMem
    }

    /**
     * 获取可用内存大小
     *
     * @param context 上下文
     * @param unit    单位：GB、MB、KB。默认：GB
     * @return 对应单位返回的值
     */
    fun getAvailMemory(context: Context, unit: String = "GB"): Float {
        var availMemory = getAvailMem(context) * 1.00f
        availMemory = when (unit.lowercase(Locale.getDefault())) {
            "gb" -> availMemory / (1000 * 1000 * 1000)
            "mb" -> availMemory / (1000 * 1000)
            else -> availMemory / 1000
        }
        return availMemory
    }

    fun getAppMemorySize(context: Context): Long {
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val appProcessInfo: List<RunningAppProcessInfo>? = am.runningAppProcesses
        if (appProcessInfo == null || appProcessInfo.isEmpty()) {
            return 0
        }
        var totalMemSize = 0
        for (item in appProcessInfo) {
            var content = ""
            item.pkgList?.forEach {
                content = "$it, "
            }
            Log.w(
                TAG,
                "#getAppMemorySize,pid=${item.pid} processName=${item.processName} pkg=${content}"
            )
        }
        return 0
    }
}