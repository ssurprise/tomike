package com.skx.tomike.cannonlaboratory.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.skx.tomike.cannonlaboratory.bean.PhotoUpImageBucket;
import com.skx.tomikecommonlibrary.base.BaseViewModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 描述 : 相册viewmodel
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/19 4:10 PM
 */
public class PhotoAlbumViewModel extends BaseViewModel {

    private final MutableLiveData<List<PhotoUpImageBucket>> mPhotoAlbumsLiveData = new MutableLiveData<>();

    public PhotoAlbumViewModel(@NonNull Application application) {
        super(application);
    }

    public void loadPhotoData() {
        Observable.create(new ObservableOnSubscribe<List<PhotoUpImageBucket>>() {
            @Override
            public void subscribe(ObservableEmitter<List<PhotoUpImageBucket>> emitter) {
                PhotoAlbumDao dao = new PhotoAlbumDao(mApplication.getApplicationContext());
                List<PhotoUpImageBucket> list = dao.getImagesBucketList();
                emitter.onNext(list);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io())// 指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<PhotoUpImageBucket>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<PhotoUpImageBucket> o) {
                        mPhotoAlbumsLiveData.postValue(o);
                    }

                    @Override
                    public void onError(Throwable e) {

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
