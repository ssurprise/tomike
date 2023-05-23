package com.skx.tomike.cannon.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.skx.common.base.BaseRepository;
import com.skx.common.base.BaseViewModel;
import com.skx.tomike.cannon.bean.RecentlyBrowsedBean;
import com.skx.tomike.cannon.repository.RecentlyBrowsedRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * 描述 : 最近浏览
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020-03-08 17:44
 */
public class RecentlyBrowsedViewModel extends BaseViewModel<BaseRepository<?>> {


    private RecentlyBrowsedRepository mRepository = new RecentlyBrowsedRepository();
    private final MutableLiveData<List<RecentlyBrowsedBean>> mRecentlyBrowsedLiveData = new MutableLiveData<>();

    public RecentlyBrowsedViewModel(@NonNull Application application) {
        super(application);
    }

    public void getRecentlyBrowsedByCityId(String cityId) {
        mRepository.queryRecentlyBrowsed(mApplication, cityId)
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程;
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<RecentlyBrowsedBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(List<RecentlyBrowsedBean> recentlyBrowsedBeans) {
                        mRecentlyBrowsedLiveData.postValue(recentlyBrowsedBeans);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void deleteRecentlyBrowsed(RecentlyBrowsedBean browsedRecord) {
        mRepository.deleteRecentBrowsed(mApplication, browsedRecord)
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程;
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void updateRecentlyBrowsed(RecentlyBrowsedBean browsedRecord) {
        mRepository.updateRecentBrowsed(mApplication, browsedRecord)
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程;
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void insertRecentlyBrowsed(RecentlyBrowsedBean browsedRecord) {
        mRepository.insertRecentBrowsed(mApplication, browsedRecord)
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程;
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public LiveData<List<RecentlyBrowsedBean>> getRecentlyBrowsedLiveData() {
        return mRecentlyBrowsedLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (mRepository != null) {
            mRepository = null;
        }
    }
}
