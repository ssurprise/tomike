package com.skx.common.base

import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver


/**
 * 描述 : 派生自 DisposableObserver，内部注入了一个Observable，用于在网络请求之前和之后发送状态通知，
 *       并完成对数据的初步处理，如错误异常等
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/5/24 12:08 上午
 */
abstract class BaseObserver<T>(private val mObservable: Observable<T>) : DisposableObserver<T>() {

    fun getObservable(): Observable<T> {
        return mObservable
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onNext(t: T) {
        doOnNext(t)
    }


    override fun onError(e: Throwable) {

    }

    override fun onComplete() {
    }

    protected fun onInterceptError(errorCode: Int, errorMsg: String?): Boolean {
        return false
    }

    abstract fun doOnNext(t: T)

//    abstract fun doOnError(e: ExceptionHandle.ResponseThrowable?)
}