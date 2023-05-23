package com.skx.common.base;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


/**
 * 描述 : base ViewModel 类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019/1/25 11:09 上午
 */
public class BaseViewModel<T extends BaseRepository<?>> extends AndroidViewModel {

    protected String TAG = getClass().getSimpleName();

    protected Application mApplication;
    protected T mRepository;

    private CompositeDisposable mCompositeDisposable;


    public BaseViewModel(@NonNull Application application) {
        super(application);
        this.mApplication = application;

        try {
            Type genType = this.getClass().getGenericSuperclass();
            if (genType instanceof ParameterizedType) {
                Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
                if (params.length > 0 && params[0] instanceof Class) {
                    @SuppressWarnings("unchecked")
                    Class<T> clazz = (Class<T>) params[0];
                    mRepository = clazz.newInstance();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected final <E extends BaseObserver<K>, K> E subscribeDisposable(BaseObserver<K> disposable) {
        return subscribe(disposable);
    }

    @SuppressWarnings("unchecked")
    private <E extends BaseObserver<K>, K> E subscribe(BaseObserver<K> disposable) {
        disposable
                .getObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                .onErrorResumeNext(new RxScheduler.HandlerException<K>())
                .subscribe(disposable);
        return (E) addSubscribe(disposable);
    }


    public <E> BaseObserver<E> addSubscribe(BaseObserver<E> disposable) {
        Objects.requireNonNull(disposable, "disposable is null");
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
        return disposable;
    }


    public <E> void removeSubscribe(BaseObserver<E> disposable) {
        Objects.requireNonNull(disposable, "disposable is null");
        if (mCompositeDisposable != null) {
            mCompositeDisposable.remove(disposable);
        }
    }

    public void unsubscribe() {
        if (mCompositeDisposable != null && mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        unsubscribe();
    }
}
