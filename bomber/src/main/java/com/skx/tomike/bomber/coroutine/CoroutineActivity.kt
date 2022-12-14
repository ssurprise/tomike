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
import kotlin.coroutines.Continuation
import kotlin.coroutines.createCoroutine
import kotlin.coroutines.resume

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

    override fun layoutId(): Int = R.layout.activity_coroutine

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("kotlin-协程").create()
    }

    override fun initView() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        test0()
//        test1()
//        test2()
        test3()
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

        /*
 E/CoroutineActivity: 协程 - thread:main
 E/CoroutineActivity: 协程 - print: Hello,
 E/CoroutineActivity: 协程 - thread:DefaultDispatcher-worker-1
 E/CoroutineActivity: 协程 - print: word
         */

    }

    /**
     * 知识点：GlobalScope.launch、runBlocking
     */
    private fun test1() {
        GlobalScope.launch { // 在后台启动一个新的协程并继续
            Log.e(TAG, "1 - GlobalScope.launch所在线程:" + Thread.currentThread().name)
            delay(1000L) // 非阻塞的等待 1 秒钟（默认时间单位是毫秒）
            Log.e(TAG, "3 - 'word'")
        }
        runBlocking {     // 调用了 runBlocking 的主线程会一直 阻塞 直到 runBlocking 内部的协程执行完毕。
            Log.e(TAG, "2 - runBlocking 所在线程:" + Thread.currentThread().name)
            delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
            Log.e(TAG, "4 - 'Hello-1'")
        }
        Log.e(TAG, "5 - thread:" + Thread.currentThread().name)
        Log.e(TAG, "6 - 'Hello-2'")

        /*
 E/CoroutineActivity: 1 - GlobalScope.launch所在线程:DefaultDispatcher-worker-1
 E/CoroutineActivity: 2 - runBlocking 所在线程:main

 (注：1、2的顺序不一定，可能是1-2 也可能是2-1)


 E/CoroutineActivity: 3 - 'word'
 E/CoroutineActivity: 4 - 'Hello-1'
 E/CoroutineActivity: 5 - thread:main
 E/CoroutineActivity: 6 - 'Hello-2'
         */
    }

    /**
     * 知识点：join
     */
    private fun test2() {
        runBlocking {
            val job = GlobalScope.launch { // 启动一个新协程并保持对这个作业的引用
                Log.e(TAG, "协程 - thread:" + Thread.currentThread().name)
                delay(1000L)
                Log.e(TAG, "协程 - print: word")
            }
            Log.e(TAG, "协程 - print: Hello，")
            Log.e(TAG, "协程 - print: end?")
            job.join() // 等待直到子协程执行结束
            Log.e(TAG, "协程 - print: end!")

            /*
            输出：
 E/CoroutineActivity: 协程 - print: Hello，
 E/CoroutineActivity: 协程 - print: end?
 E/CoroutineActivity: 协程 - thread:DefaultDispatcher-worker-1
 E/CoroutineActivity: 协程 - print: word
 E/CoroutineActivity: 协程 - print: end!   // 注：在join 的子协程完毕之后执行。
             */
        }
    }

    /**
     * 取消协程的执行
     */
    private fun test3() {
        GlobalScope.launch {
            // 该 launch 函数返回了一个可以被用来取消运行中的协程的 Job
            val job = GlobalScope.launch {
                repeat(1000) { i ->
                    Log.e(TAG, "job: I'm sleeping $i ...")
                    delay(500L)
                }
            }
            delay(1300L) // delay a bit
            Log.e(TAG, "main: I'm tired of waiting!")
            job.cancel() // cancels the job
            job.join() // waits for job's completion
//            job.cancelAndJoin() // 取消该作业并等待它结束

            Log.e(TAG, "main: Now I can quit.")

            /*
2022-10-13 15:05:06.401 25464-25518/com.skx.tomike E/CoroutineActivity: job: I'm sleeping 1 ...

2022-10-13 15:05:06.905 25464-25518/com.skx.tomike E/CoroutineActivity: job: I'm sleeping 2 ...
2022-10-13 15:05:07.204 25464-25518/com.skx.tomike E/CoroutineActivity: main: I'm tired of waiting!
2022-10-13 15:05:07.205 25464-25517/com.skx.tomike E/CoroutineActivity: main: Now I can quit.
             */
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