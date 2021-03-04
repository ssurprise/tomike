package com.skx.tomike.cannonlaboratory.ui.activity

import android.os.Handler
import android.os.Looper
import android.os.Message
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannonlaboratory.R
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
class CountDownTimerActivity : SkxBaseActivity<BaseViewModel>() {

    private val timer1: TextView by lazy {
        findViewById<TextView>(R.id.tv_countDownTimer_timer)
    }
    private val timer2: TextView by lazy {
        findViewById<TextView>(R.id.tv_countDownTimer_scheduledExecutor)
    }
    private val timer3: TextView by lazy {
        findViewById<TextView>(R.id.tv_countDownTimer_countDownTimer)
    }
    private val mEtTime: EditText by lazy {
        findViewById<EditText>(R.id.et_countDownTimer_time)
    }

    private var mTime: Int = 10000
    private val mTimer: Timer = Timer()
    private var newScheduledThreadPool: ScheduledExecutorService? = null

    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg?.what) {
                0 -> {
                    Log.e("count-down-timer", "handleMessage $mTime")
                    formatTime(timer1, mTime)
                }
                1 -> {
                    Log.e("count-down-timer", "handleMessage $mTime")
                    formatTime(timer2, mTime)
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
        mEtTime.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0?.takeIf {
                    TextUtils.isDigitsOnly(it)
                }?.run {
                    mTime = this.toString().toInt()
                }
            }

        })
        mEtTime.setText(mTime.toString())
    }


    fun onTimerTask(view: View) {
        mTimer.schedule(object : TimerTask() {
            override fun run() {
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
            mTime--
            Log.e("count-down-timer", "newScheduledThreadPool: $mTime")
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
}