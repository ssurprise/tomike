package com.skx.tomike.bomber.coroutine

import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.bomber.R
import com.skx.tomike.bomber.ROUTE_PATH_COROUTINE
import kotlinx.coroutines.*

/**
 * 描述 : 协程demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/1/7 3:27 PM
 */
@Route(path = ROUTE_PATH_COROUTINE)
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
//        test0()
//        test1()
        test2()
    }

    private fun test0() {
        GlobalScope.launch { // 在后台启动一个新的协程并继续
            Log.e(TAG, "协程 - thread:" + Thread.currentThread().name)
            delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
            Log.e(TAG, "协程 - print: word")
        }
        Log.e(TAG, "协程 - thread:" + Thread.currentThread().name)
        Log.e(TAG, "协程 - print: Hello,")
        Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
    }

    /**
     * 知识点：GlobalScope.launch、runBlocking
     */
    private fun test1() {
        GlobalScope.launch { // 在后台启动一个新的协程并继续
            Log.e(TAG, "协程 - thread:" + Thread.currentThread().name)
            delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
            Log.e(TAG, "协程 - print: word")
        }
        Log.e(TAG, "协程 - thread:" + Thread.currentThread().name)
        Log.e(TAG, "协程 - print: Hello,")
        runBlocking {     // 调用了 runBlocking 的主线程会一直 阻塞 直到 runBlocking 内部的协程执行完毕。
            delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
        }
    }
   /**
     * 知识点：join
     */
    private fun test2() {
       runBlocking{
           val job = GlobalScope.launch { // 启动一个新协程并保持对这个作业的引用
               Log.e(TAG, "协程 - thread:" + Thread.currentThread().name)
               delay(1000L)
               Log.e(TAG, "协程 - print: word")
           }
           Log.e(TAG, "协程 - print: Hello，")
           job.join() // 等待直到子协程执行结束
       }
    }


    //---------------------------------分割线-----------------------------------

//    private fun test1() {
//        GlobalScope.launch(Dispatchers.Main) {
//            ioFun()
//            uiFun()
//        }
//    }
//
//    private suspend fun ioFun() {
//        withContext(Dispatchers.IO) {
//            Thread.sleep(2000)
//            Log.e(TAG, "协程 - thread:" + Thread.currentThread().name)
//        }
//    }
//
//    private fun uiFun() {
//        Log.e(TAG, "协程 - thread:" + Thread.currentThread().name)
//    }

    //---------------------------------分割线-----------------------------------
//    private fun test2() {
//        GlobalScope.launch(Dispatchers.Main) {
//            val result1 = async {
//                Log.e(TAG, "协程 - result1 thread:" + Thread.currentThread().name)
//                requestResult1()
//            }
//            val result2 = async {
//                Log.e(TAG, "协程 - result2 thread:" + Thread.currentThread().name)
//                requestResult2()
//
//            }
//            val and = result1.await() + (result2.await())
//            Log.e(TAG, "协程 - result1 + result2 :$and")
//        }
//    }
//
//    private suspend fun requestResult1(): Int {
//        delay(500L) // 假设我们在这里做了一些有用的事
//        return 1
//    }
//
//    private fun requestResult2(): Int {
////        delay(1000L) // 假设我们在这里也做了一些有用的事
//        return 29
//    }

}