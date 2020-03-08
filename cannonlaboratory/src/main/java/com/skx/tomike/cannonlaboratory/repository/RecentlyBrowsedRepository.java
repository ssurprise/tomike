package com.skx.tomike.cannonlaboratory.repository;

import android.content.Context;

import com.skx.tomike.cannonlaboratory.bean.RecentlyBrowsedBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;

public class RecentlyBrowsedRepository {

    /**
     * 查询最近浏览记录
     *
     * @param context 上下文
     * @param cityId
     * @return
     */
    public Observable<List<RecentlyBrowsedBean>> queryRecentlyBrowsed(final Context context, final String cityId) {
        return Observable.create(new ObservableOnSubscribe<List<RecentlyBrowsedBean>>() {
            @Override
            public void subscribe(ObservableEmitter<List<RecentlyBrowsedBean>> emitter) {
                List<RecentlyBrowsedBean> recentlyBrowsedByCityId = RecentlyBrowsedDatabase.getInstance(context)
                        .recentlyBrowsedDao().getRecentlyBrowsedByCityId(cityId);

                if (recentlyBrowsedByCityId == null) {
                    recentlyBrowsedByCityId = new ArrayList<>();
                }
                emitter.onNext(recentlyBrowsedByCityId);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()); // 指定 subscribe() 发生在 IO 线程
    }

    /**
     * 删除浏览记录
     *
     * @param browsedRecord 浏览记录
     * @return
     */
    public Observable<Boolean> deleteRecentBrowsed(final Context context, final RecentlyBrowsedBean browsedRecord) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) {
                RecentlyBrowsedDatabase.getInstance(context)
                        .recentlyBrowsedDao().deleteBrowsedRecord(browsedRecord);
                emitter.onNext(true);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()); // 指定 subscribe() 发生在 IO 线程
    }

    /**
     * 更新浏览记录
     *
     * @param browsedRecord 浏览记录
     * @return
     */
    public Observable<Boolean> updateRecentBrowsed(final Context context, final RecentlyBrowsedBean browsedRecord) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) {
                RecentlyBrowsedDatabase.getInstance(context)
                        .recentlyBrowsedDao().updateBrowsedRecord(browsedRecord);
                emitter.onNext(true);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()); // 指定 subscribe() 发生在 IO 线程
    }

    /**
     * 插入浏览记录
     *
     * @param browsedRecord 浏览记录
     * @return
     */
    public Observable<Boolean> insertRecentBrowsed(final Context context, final RecentlyBrowsedBean browsedRecord) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) {
                RecentlyBrowsedDatabase.getInstance(context)
                        .recentlyBrowsedDao().insertBrowsedRecord(browsedRecord);
                emitter.onNext(true);
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()); // 指定 subscribe() 发生在 IO 线程

    }
}
