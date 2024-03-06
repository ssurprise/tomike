package com.skx.tomike.cannon.ui.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.work.*
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.common.rwriter.getInstance
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_WORK_MANAGER
import java.util.concurrent.TimeUnit


/**
 * 描述 : 后台任务-WorkManager
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/8/30 5:00 下午
 */
@Route(path = ROUTE_PATH_WORK_MANAGER)
class WorkManagerActivity : SkxBaseActivity<BaseViewModel<*>>(), View.OnClickListener {

    private val mTvLogcat by lazy {
        findViewById<TextView>(R.id.tv_workManager_logcat)
    }

    override fun initParams() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("WorkManager").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_work_manager
    }

    override fun initView() {
        findViewById<TextView>(R.id.btn_workManager_oneTime).setOnClickListener(this)
        findViewById<TextView>(R.id.btn_workManager_periodic).setOnClickListener(this)
        findViewById<TextView>(R.id.btn_workManager_cancel).setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_workManager_oneTime -> {
                startOneTimeWork()
            }
            R.id.btn_workManager_periodic -> {
                startPeriodicWork()
            }
            R.id.btn_workManager_cancel -> {
                stopWork()
            }
        }
    }

    private fun startOneTimeWork() {
        Log.e(TAG, "按下'一次性任务'按钮")

        // 创建约束条件（非必需）
        val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresStorageNotLow(true) // 如果设置为 true，那么当用户设备上的存储空间不足时，工作不会运行
//            .setRequiresDeviceIdle(true)
            .build()

        val uploadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<TestWorker>()
            .setConstraints(constraints)
            .setInitialDelay(5, TimeUnit.SECONDS)
            .addTag("OneTimeWorkTest")
            .build()
        WorkManager.getInstance(applicationContext).enqueue(uploadWorkRequest)
    }

    private fun startPeriodicWork() {
        Log.e(TAG, "按下'周期任务'按钮")
        val testRequest = PeriodicWorkRequestBuilder<TestWorker>(15, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
            "PeriodicWorkTest",
            ExistingPeriodicWorkPolicy.KEEP,
            testRequest
        )
    }

    private fun stopWork() {
        Log.e(TAG, "按下'取消'按钮")
        WorkManager.getInstance(applicationContext).cancelAllWork()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    class TestWorker(context: Context, workerParams: WorkerParameters) :
        Worker(context, workerParams) {

        override fun doWork(): Result {

            // 运行在WorkManager 的子线程
            Log.e(
                "WorkManagerActivity",
                "doWork, tags=${tags}, running thread=${Thread.currentThread().name}"
            )
            return Result.success()
        }
    }
}