//package com.skx.common.base.d;
//
//import android.app.Application;
//
//import androidx.annotation.NonNull;
//import androidx.lifecycle.AndroidViewModel;
//import androidx.lifecycle.LifecycleObserver;
//import androidx.lifecycle.MutableLiveData;
//
//import com.xiaozhu.biz.support.base.viewData.SingleLiveEvent;
//import com.xiaozhu.lib.common.net.base.BaseObserver;
//import com.xiaozhu.lib.common.net.base.RxScheduler;
//import com.xiaozhu.lib.common.tools.TUtil;
//
//import io.reactivex.Observer;
//
//public abstract class BaseViewModel<T extends BaseRepository> extends AndroidViewModel implements LifecycleObserver {
//
//    protected final MutableLiveData<String> msg = new SingleLiveEvent<>();
//
//    protected T mRepository;
//
//    public BaseViewModel(@NonNull Application application) {
//        super(application);
//        mRepository = getBaseRepository();
//
//    }
//
//    protected final <E extends BaseObserver<T>, T> E subscribeDisposable(BaseObserver<T> disposable) {
//        return (E) subscribe(disposable);
//    }
//
//    private <E extends BaseObserver<T>, T> E subscribe(BaseObserver<T> disposable) {
//        disposable
//                .getObservable()
//                .compose(new RxScheduler.IO_MAIN<T>())
//                .onErrorResumeNext(new RxScheduler.HandlerException<T>())
//                .subscribeWith(disposable);
//        return (E) mRepository.addSubscribe(disposable);
//    }
//
//
//    @Override
//    protected void onCleared() {
//        super.onCleared();
//        if (mRepository != null) {
//            mRepository.onUnsubscribe();
//        }
//    }
//
//    public MutableLiveData<String> getMsgLiveData() {
//        return msg;
//    }
//
//    protected T getBaseRepository() {
//        return TUtil.getT(this, 0);
//    }
//
//    public static <T> Observer<T> subscribeOnMainThread(BaseObserver<T> disposable) {
//        return disposable.getObservable()
//                .compose(new RxScheduler.IO_MAIN<T>())
//                .onErrorResumeNext(new RxScheduler.HandlerException<T>())
//                .subscribeWith(disposable);
//    }
//}