package com.skx.common.download

import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * @Author： shiguotao
 * @E-mail: shiguotao-os@360os.com
 * @DATE： 2023/10/18 19:49
 * @Description： 下载管理器
 */
class Downloader {

    private var mExecutor: ThreadPoolExecutor? = null

    init {
        mExecutor = ThreadPoolExecutor(
            3, Setting.maxThreadNumber, 60,
            TimeUnit.MILLISECONDS,
            LinkedBlockingQueue<Runnable>(60)
        )
    }

    /**
     * 正在运行中的任务列表
     */
    private val mRunningList = mutableListOf<Task>()

    /**
     * 等待中的任务列表
     */
    private val mWaitingList = mutableListOf<Task>()


    fun addTask(task: Task){
        mWaitingList.add(task)
    }

    fun start(){

    }
}