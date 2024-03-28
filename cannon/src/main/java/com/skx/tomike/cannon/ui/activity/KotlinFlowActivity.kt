package com.skx.tomike.cannon.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.common.loudspeaker.LoudSpeaker
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_FLOW
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*

/**
 * 描述 : kotlin flow 实现并行、串行访问数据
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/23 4:25 PM
 */
@Route(path = ROUTE_PATH_FLOW)
class KotlinFlowActivity : SkxBaseActivity<BaseViewModel<*>>(), View.OnClickListener, Observer {

    private var mTvLogcat: TextView? = null

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig? {
        return TitleConfig.Builder().setTitleText("Kotlin flow").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_kotlin_flow
    }

    override fun initView() {
        mTvLogcat = findViewById(R.id.tv_flow_logcat)
        findViewById<TextView>(R.id.tv_flow_single).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_flow_parallelExecute).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_flow_serialExecute).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_flow_serialAndMerge1).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_flow_serialAndMerge2).setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoudSpeaker.addObserver(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_flow_single -> {
                single()
            }
            R.id.tv_flow_parallelExecute -> {
                parallelExecute()
            }
            R.id.tv_flow_serialExecute -> {
                serialExecute()
            }
            R.id.tv_flow_serialAndMerge1 -> {
                serialAndMerge1()
            }
            R.id.tv_flow_serialAndMerge2 -> {
                serialAndMerge2()
            }
        }
    }

    private fun single() {
        mTvLogcat?.text = ""
        GlobalScope.launch {
            flow {
                for (i in 1..3) {
                    delay(3000)//可以使用挂起函数
                    emit(i)//发射元素
                    LoudSpeaker.sendMsg("send $i thread:${Thread.currentThread().name}")
                }
            }
                .flowOn(Dispatchers.IO)
                .onStart {
                    LoudSpeaker.sendMsg("onStart thread:${Thread.currentThread().name}")
                }
                .onEach {
                    //上游向下游发送数据之前调用，每一个上游数据发送后都会经过onEach()
                    LoudSpeaker.sendMsg("onEach: $it  thread:${Thread.currentThread().name}")
                }.onCompletion {
                    LoudSpeaker.sendMsg("onCompletion.  thread:${Thread.currentThread().name}")
                }.catch { exception ->
                    exception.message?.let { LoudSpeaker.sendMsg("catch. thread:${Thread.currentThread().name}") }
                }
                .collect {
                    //接收数据流
                    LoudSpeaker.sendMsg("collect: $it thread:${Thread.currentThread().name}")
                }
        }
    }

    /**
     * 并行执行
     *
     *
     * 案例：同时请求接口A和接口B，最终返回接口A 和接口B的合并数据。
     */
    private fun parallelExecute() {

    }

    /**
     * 串行执行。
     *
     *
     * 案例：比如先请求接口A，根据接口A 返回的数据再请求接口B，最终返回接口B的数据
     */
    private fun serialExecute() {

    }

    /**
     * 串行
     *
     *
     * 案例：请求接口A，根据接口A 的返回值再去请求接口B，返回接口A 和接口B 的合并数据。
     */
    private fun serialAndMerge1() {

    }

    /**
     * 串行执行.
     *
     *
     * 案例：请求接口A，根据接口A 的返回值再去请求接口B，返回接口A 和接口B 的合并数据。
     * 问题：这种方式有问题，当第一个错误的时候，直接就崩掉了
     */
    private fun serialAndMerge2() {

    }

    override fun update(o: Observable?, arg: Any?) {
        if (arg is String) {
            logPrint(arg)
        }
    }

    private fun logPrint(s: String) {
        Log.d(TAG, s)
//        mTvLogcat?.append("\n$s")
    }

    override fun onDestroy() {
        super.onDestroy()
        LoudSpeaker.deleteObserver(this)
    }
    /*
    冷流：主动需要即是主动收集才会提供发射数据
    热流：不管你需不需要一上来数据全都发射给你。

    好文推荐：Kotlin协程：Flow 异步流 https://blog.csdn.net/qq_32955807/article/details/128579310

     flow 默认是冷流，
     */
}