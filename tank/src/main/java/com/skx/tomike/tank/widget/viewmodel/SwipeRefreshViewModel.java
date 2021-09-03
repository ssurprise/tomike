package com.skx.tomike.tank.widget.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.skx.common.base.BaseViewModel;

import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * 描述 : 滑动刷新viewmodel
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020-03-18 22:17
 */
public class SwipeRefreshViewModel extends BaseViewModel {

    private final MutableLiveData<List<String>> mTestDataLiveData = new MutableLiveData<>();

    public SwipeRefreshViewModel(@NonNull Application application) {
        super(application);
    }


    public MutableLiveData<List<String>> getTestDataLiveData() {
        return mTestDataLiveData;
    }


    public void loadData() {
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) {
                try {
                    Thread.currentThread().sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<String> strings = Arrays.asList("Java", "Javascript", "C++", "Ruby", "Json", "HTML");
                emitter.onNext(strings);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<String> strings) {
                        mTestDataLiveData.postValue(strings);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
