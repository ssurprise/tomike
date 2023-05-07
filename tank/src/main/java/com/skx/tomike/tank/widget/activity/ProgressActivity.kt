package com.skx.tomike.tank.widget.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.ProgressBar
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_PROGRESS_BAR
import java.util.*

/**
 * 描述 : CheckBox demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020-03-23 23:23
 */
@Route(path = ROUTE_PATH_PROGRESS_BAR)
class ProgressActivity : SkxBaseActivity<BaseViewModel?>() {

    private val tv_reset_btn: TextView by lazy {
        findViewById(R.id.tv_reset_btn)
    }

    private val pb_progressBar_1: ProgressBar by lazy {
        findViewById(R.id.pb_progressBar_1)
    }

    private val pb_progressBar_2: ProgressBar by lazy {
        findViewById(R.id.pb_progressBar_2)
    }

    private var mProgress: Int = 0


    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                0 -> {
                    pb_progressBar_2.progress = msg.arg1
                }
            }
        }
    }

    private val mRunnable: Runnable = object : Runnable {
        override fun run() {
            ++mProgress
            if (mProgress > 100) {
                mHandler.removeCallbacks(this)
                return
            }
            val msg = mHandler.obtainMessage(0)
            msg.arg1 = mProgress
            mHandler.sendMessage(msg)
            mHandler.postDelayed(this, 200)
        }
    }


    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("ProgressBar").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_progress_bar
    }

    override fun initView() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tv_reset_btn.setOnClickListener {
            startLoad()
        }
        startLoad()
    }

    private fun startLoad() {
        mProgress = 0
        mHandler.removeCallbacks(mRunnable)
        mHandler.postDelayed(mRunnable, 1000)
    }

    override fun onDestroy() {
        super.onDestroy()
        mHandler.removeCallbacks(mRunnable)
    }
}