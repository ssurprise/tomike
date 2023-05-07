package com.skx.tomike.cannon.ui.activity

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_COUNT_DOWN_TIMER
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

/**
 * 描述 : 倒计时
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/12/8 1:35 PM
 */
@Route(path = ROUTE_PATH_COUNT_DOWN_TIMER)
class CountDownTimerActivity : SkxBaseActivity<BaseViewModel>() {

    companion object {
        const val VALUE_WHAT_HANDLER = 1
        const val VALUE_WHAT_TIMER_TASK = 2
        const val VALUE_WHAT_SCHEDULED_THREAD = 3
    }

    private val mTvTimer1: TextView by lazy {
        findViewById(R.id.tv_countDownTimer_handler)
    }

    private val mTvTimer2: TextView by lazy {
        findViewById(R.id.tv_countDownTimer_timer)
    }
    private val mTvTimer3: TextView by lazy {
        findViewById(R.id.tv_countDownTimer_scheduledExecutor)
    }
    private val mTvTimer4: TextView by lazy {
        findViewById(R.id.tv_countDownTimer_countDownTimer)
    }
    private val mEtTime: EditText by lazy {
        findViewById(R.id.et_countDownTimer_time)
    }

    private var mTime1: Int = 100
    private var mTime2: Int = mTime1
    private var mTime3: Int = mTime1

    private val mTimer: Timer = Timer()
    private var newScheduledThreadPool: ScheduledExecutorService? = null
    private var mRunnable: Runnable = object : Runnable {
        override fun run() {
            if (mTime1 <= 0) {
                mHandler.removeCallbacks(this)
                return
            }
            mTime1--
            val message = mHandler.obtainMessage(VALUE_WHAT_HANDLER)
            message.arg1 = mTime1
            mHandler.sendMessage(message)
            mHandler.postDelayed(this, 1000)
        }
    }

    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                VALUE_WHAT_HANDLER -> {
                    Log.e("count-down-timer", "handleMessage $mTime1")
                    formatTime(mTvTimer1, mTime1)
                }
                VALUE_WHAT_TIMER_TASK -> {
                    Log.e("count-down-timer", "handleMessage $mTime2")
                    formatTime(mTvTimer2, mTime2)
                }
                VALUE_WHAT_SCHEDULED_THREAD -> {
                    Log.e("count-down-timer", "handleMessage $mTime3")
                    formatTime(mTvTimer3, mTime3)
                }
            }
        }
    }

    override fun initParams() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("倒计时功能").create()
    }

    override fun layoutId(): Int = R.layout.activity_count_down_timer

    override fun initView() {
        mEtTime.setText(mTime1.toString())
    }

    fun onSettingCountDownTime(view: View) {
        mTime1 = mEtTime.text.toString().toInt()
        mTime2 = mTime1
        mTime3 = mTime1
    }

    fun onHandler(view: View) {
        mHandler.removeCallbacks(mRunnable)
        mHandler.postDelayed(mRunnable, 1000)
    }

    fun onTimerTask(view: View) {
        mTimer.schedule(object : TimerTask() {
            override fun run() {
                if (mTime2 <= 0) {
                    mTimer.cancel()
                    return
                }
                mTime2--
                val message = mHandler.obtainMessage(VALUE_WHAT_TIMER_TASK)
                message.arg1 = mTime2
                mHandler.sendMessage(message)
            }
        }, 0, 1000)
    }

    fun onScheduledExecutor(view: View) {
        newScheduledThreadPool = Executors.newScheduledThreadPool(1)
        // scheduleAtFixedRate：延时设定时间（initialDelay）后执行第一次任务，每隔时间间隔（period）再次执行任务。
        newScheduledThreadPool?.scheduleAtFixedRate({
            if (mTime3 <= 0) {
                newScheduledThreadPool?.shutdownNow()
                return@scheduleAtFixedRate
            }
            mTime3--
            val message = mHandler.obtainMessage(VALUE_WHAT_SCHEDULED_THREAD)
            message.arg1 = mTime3
            mHandler.sendMessage(message)
        }, 0, 1, TimeUnit.SECONDS)
    }

    fun onCountDownTimer(view: View) {}

    fun formatTime(tv: TextView, time: Int) {
        val h = time / 3600
        val m = (time % 3600) / 60
        val s = (time % 3600) % 60
        tv.text = String.format(Locale.CHINA, "剩余时间为：%02d:%02d:%02d", h, m, s)
    }

    override fun onDestroy() {
        super.onDestroy()
        mTimer.cancel()
        newScheduledThreadPool?.shutdownNow()
    }
}