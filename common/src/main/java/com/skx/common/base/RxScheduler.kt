package com.skx.common.base

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
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

//    public static class HandlerException<T> implements Function<Throwable, Observable<T>> {
//        @Override
//        public Observable<T> apply(Throwable throwable) throws Exception {
//            throwable.printStackTrace();
//            ExceptionHandle.ResponseThrowable responeThrowable = ExceptionHandle.handleException(throwable);
//            return Observable.error(responeThrowable);
//        }
//    }