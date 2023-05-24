package com.skx.common.base

import com.skx.common.net.exception.ExceptionHandle
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers


class IO_MAIN<T> : ObservableTransformer<T, T> {
    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
}

class IO<T> : ObservableTransformer<T, T> {
    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.subscribeOn(Schedulers.io())
    }
}


class Main<T> : ObservableTransformer<T, T> {
    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.observeOn(AndroidSchedulers.mainThread())
    }
}

class HandlerException<T> : Function<Throwable, Observable<T>> {
    override fun apply(t: Throwable): Observable<T> {
        t.printStackTrace()
        val responeThrowable = ExceptionHandle.handleException(t)
        return Observable.error(responeThrowable)
    }
}



