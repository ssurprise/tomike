package com.skx.tomike.cannon.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.skx.common.base.BaseViewModel;
import com.skx.common.base.IRepository;
import com.skx.tomike.cannon.bean.PhotoUpImageBucket;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * 描述 : 相册viewmodel
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/19 4:10 PM
 */
public class PhotoAlbumViewModel extends BaseViewModel<IRepository> {

    private final MutableLiveData<List<PhotoUpImageBucket>> mPhotoAlbumsLiveData = new MutableLiveData<>();

    public PhotoAlbumViewModel(@NonNull Application application) {
        super(application);
    }

    public void loadPhotoData() {
        Observable.create(new ObservableOnSubscribe<List<PhotoUpImageBucket>>() {
            @Override
            public void subscribe(@io.reactivex.rxjava3.annotations.NonNull ObservableEmitter<List<PhotoUpImageBucket>> emitter) throws Throwable {
                PhotoAlbumDao dao = new PhotoAlbumDao(mApplication.getApplicationContext());
                List<PhotoUpImageBucket> list = dao.getImagesBucketList();
                emitter.onNext(list);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())// 指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PhotoUpImageBucket>>() {
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@io.reactivex.rxjava3.annotations.NonNull List<PhotoUpImageBucket> photoUpImageBuckets) {
                        mPhotoAlbumsLiveData.postValue(photoUpImageBuckets);
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public MutableLiveData<List<PhotoUpImageBucket>> getPhotoAlbumsLiveData() {
        return mPhotoAlbumsLiveData;
    }
}
