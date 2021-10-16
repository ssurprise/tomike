package com.skx.tomike.bomber.coroutine

import android.os.Bundle
import android.util.Log
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.bomber.R
import kotlinx.coroutines.*

/**
 * 描述 : 协程demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/1/7 3:27 PM
 */
class CoroutineActivity : SkxBaseActivity<BaseViewModel>() {

    override fun initParams() {
    }

    override fun getLayoutId(): Int = R.layout.activity_coroutine

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("kotlin-协程").create()
    }

    override fun initView() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        test2()
    }

    //---------------------------------分割线-----------------------------------
    private fun test2() {
        GlobalScope.launch(Dispatchers.Main) {
            val result1 = async {
                Log.e(TAG, "协程 - result1 thread:" + Thread.currentThread().name)
                requestResult1()
            }
            val result2 = async {
                Log.e(TAG, "协程 - result2 thread:" + Thread.currentThread().name)
                requestResult2()

            }
            val and = result1.await() + (result2.await())
            Log.e(TAG, "协程 - result1 + result2 :$and")
        }
    }

    private suspend fun requestResult1(): Int {
        delay(500L) // 假设我们在这里做了一些有用的事
        return 1
    }

    private suspend fun requestResult2(): Int {
        delay(1000L) // 假设我们在这里也做了一些有用的事
        return 29
    }


    //---------------------------------分割线-----------------------------------

    private fun test1() {
        GlobalScope.launch(Dispatchers.Main) {
            ioFun()
            uiFun()
        }
    }

    private suspend fun ioFun() {
        withContext(Dispatchers.IO) {
            Thread.sleep(2000)
            Log.e(TAG, "协程 - thread:" + Thread.currentThread().name)
        }
    }

    private fun uiFun() {
        Log.e(TAG, "协程 - thread:" + Thread.currentThread().name)
    }
}