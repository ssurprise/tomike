package com.skx.tomike.cannon.ui.activity

import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_JOB_SCHEDULER


/**
 * 描述 : 定时任务-JobSchedule
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/8/30 5:00 下午
 */
@Route(path = ROUTE_PATH_JOB_SCHEDULER)
class JobSchedulerActivity : SkxBaseActivity<BaseViewModel<*>>(), View.OnClickListener {

    private val mTvLogcat by lazy {
        findViewById<TextView>(R.id.tv_jobSchedule_logcat)
    }

    override fun initParams() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("JobSchedule").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_job_schedule
    }

    override fun initView() {
        findViewById<TextView>(R.id.btn_jobSchedule_start).setOnClickListener(this)
        findViewById<TextView>(R.id.btn_jobSchedule_finish).setOnClickListener(this)
        findViewById<TextView>(R.id.btn_jobSchedule_cancel).setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_jobSchedule_start -> {
                startJobSchedule()
            }
            R.id.btn_jobSchedule_finish -> {
                finishJobSchedule()
            }
            R.id.btn_jobSchedule_cancel -> {
                stopJobSchedule()
            }
        }
    }

    private fun startJobSchedule() {
        val mJobScheduler: JobScheduler? =
            getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler?
        val builder = JobInfo.Builder(
            JobSchedulerService.JOB_ID,
            ComponentName(packageName, JobSchedulerService::class.java.name)
        )

        // 是否系统重启后继续该Job执行，清单文件要添加权限android.permission.RECEIVE_BOOT_COMPLETED
//        builder.setPersisted(true)

        // 指定Job多少毫秒之后执行，与setPeriodic()互斥，同时使用会抛出异常
//        builder.setMinimumLatency(5000)
        builder.setPeriodic(5 * 60 * 1000)

        // 指定启动Job时的网络类型
        // JobInfo.NETWORK_TYPE_NONE:启动Job时不需要任何网络连接
        // JobInfo.NETWORK_TYPE_ANY:启动Job时只要有网络就可以
        // JobInfo.NETWORK_TYPE_NETWORK_TYPE_UNMETERED:启动Job时要连接wifi
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)

        // 指定启动Job时是否需要连接电源
//            jb.setRequiresCharging(false)

        // 指定启动Job时是否需要设别处于空闲状态
//            jb.setRequiresDeviceIdle(false)

        Log.e(TAG, "按下'开始'按钮")
        mJobScheduler?.schedule(builder.build())
    }


    private fun finishJobSchedule() {

    }


    private fun stopJobSchedule() {
        val mJobScheduler: JobScheduler? =
            getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler?
        Log.e(TAG, "按下'停止'按钮")
        mJobScheduler?.cancel(JobSchedulerService.JOB_ID)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

class JobSchedulerService : JobService() {

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate...")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand...")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        val jobId = params?.jobId
        Log.e(TAG, "onStartJob，jobId=${jobId}")
        /*
        如果为 false，那么说明Job执行完毕。
        如果为 true，说明Job还没有执行完成，此时的任务是放在另一个线程执行的耗时任务，当这个耗时任务执行完成时，应该主动
            调用jobFinished方法，告诉系统该Job已经执行完成。
         */
        Thread(Runnable {
            Thread.sleep(8000)
            jobFinished(params, false)
        }).start()
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Log.e(TAG, "onStopJob，jobId=${params?.jobId}")
        /*
        当执行 jobScheduler.cancel 或者 jobScheduler.cancelAll 时，并且该Job还没有执行完，那么 onStopJob 方法被执行。
        onStopJob 的返回值如果为false时，表示不需要重试，为true时，表示需要重试。
         */
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy...")
    }

    companion object {
        const val TAG = "JobSchedulerService"
        const val JOB_ID = 236
    }
}