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

    private val timer1: TextView by lazy {
        findViewById(R.id.tv_countDownTimer_timer)
    }
    private val timer2: TextView by lazy {
        findViewById(R.id.tv_countDownTimer_scheduledExecutor)
    }
    private val timer3: TextView by lazy {
        findViewById(R.id.tv_countDownTimer_countDownTimer)
    }
    private val mEtTime: EditText by lazy {
        findViewById(R.id.et_countDownTimer_time)
    }

    private var mTime: Int = 100
    private var mTime2: Int = mTime
    private val mTimer: Timer = Timer()
    private var newScheduledThreadPool: ScheduledExecutorService? = null

    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                0 -> {
                    Log.e("count-down-timer", "handleMessage $mTime")
                    formatTime(timer1, mTime)
                }
                1 -> {
                    Log.e("count-down-timer", "handleMessage $mTime2")
                    formatTime(timer2, mTime2)
                }
            }
        }
    }

    override fun initParams() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("倒计时功能").create()
    }

    override fun getLayoutId(): Int = R.layout.activity_count_down_timer

    override fun initView() {
        mEtTime.setText(mTime.toString())
    }


    fun onTimerTask(view: View) {
        mTimer.schedule(object : TimerTask() {
            override fun run() {
                if (mTime <= 0) {
                    mTimer.cancel()
                    return
                }
                mTime--
                Log.e("count-down-timer", "onTimerTask:$mTime")
                val message = mHandler.obtainMessage(0)
                mHandler.sendMessage(message)
            }
        }, 0, 1000)
    }

    fun onScheduledExecutor(view: View) {
        newScheduledThreadPool = Executors.newScheduledThreadPool(1)
        // scheduleAtFixedRate：延时设定时间（initialDelay）后执行第一次任务，每隔时间间隔（period）再次执行任务。
        newScheduledThreadPool?.scheduleAtFixedRate({
            if (mTime2 <= 0) {
                newScheduledThreadPool?.shutdownNow()
                return@scheduleAtFixedRate
            }
            mTime2--
            Log.e("count-down-timer", "newScheduledThreadPool: $mTime2")
            val message = mHandler.obtainMessage(1)
            mHandler.sendMessage(message)
        }, 0, 1, TimeUnit.SECONDS)
    }

    fun onCountDownTimer(view: View) {}


    override fun onDestroy() {
        super.onDestroy()
        mTimer.cancel()
        newScheduledThreadPool?.shutdownNow()
    }

    fun formatTime(tv: TextView, time: Int) {
        val h = time / 3600
        val m = (time % 3600) / 60
        val s = (time % 3600) % 60
        tv.text = String.format(Locale.CHINA, "剩余时间为：%02d:%02d:%02d", h, m, s)
    }

    fun onSettingCountDownTime(view: View) {
        mTime = mEtTime.text.toString().toInt()
        mTime2 = mTime
    }
}