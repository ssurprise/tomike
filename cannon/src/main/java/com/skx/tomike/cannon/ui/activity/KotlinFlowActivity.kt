package com.skx.tomike.cannon.ui.activity

import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_FLOW
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * 描述 : RxJava 实现并行、串行访问数据
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/23 4:25 PM
 */
@Route(path = ROUTE_PATH_FLOW)
class KotlinFlowActivity : SkxBaseActivity<BaseViewModel<*>>(), View.OnClickListener {

    private var mRlLoading: LinearLayout? = null
    private var mTvLoadingText: TextView? = null

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig? {
        return TitleConfig.Builder().setTitleText("Kotlin flow").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_kotlin_flow
    }

    override fun initView() {
        mRlLoading = findViewById(R.id.rl_flow_loading)
        mTvLoadingText = findViewById(R.id.tv_flow_loadingText)
        findViewById<TextView>(R.id.tv_flow_single).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_flow_parallelExecute).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_flow_serialExecute).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_flow_serialAndMerge1).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_flow_serialAndMerge2).setOnClickListener(this)
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
        GlobalScope.launch {
            flow {
                Log.e(TAG, "send 1 thread:${Thread.currentThread().name}")
                emit("1") //发送数据
                Log.e(TAG, "send 2 thread:${Thread.currentThread().name}")
                emit("2") //发送数据
                Log.e(TAG, "send 3 thread:${Thread.currentThread().name}")
                emit("3") //发送数据
            }
                .flowOn(Dispatchers.Main)
                .onEmpty {
                    Log.e(TAG, "onEmpty thread:${Thread.currentThread().name}")
                }.onStart {
                    Log.e(TAG, "onStart thread:${Thread.currentThread().name}")
                }.onEach {
                    Log.e(TAG, "onEach: $it  thread:${Thread.currentThread().name}")
                }.onCompletion {
                    Log.e(TAG, "onCompletion thread:${Thread.currentThread().name}")
                }.catch { exception ->
                    exception.message?.let { Log.e(TAG, it) }
                }.collect {
                    //接收数据流
                    Log.e(TAG, "collect: $it thread:${Thread.currentThread().name}")
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
}