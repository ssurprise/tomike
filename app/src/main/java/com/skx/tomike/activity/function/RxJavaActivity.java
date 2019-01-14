package com.skx.tomike.activity.function;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.skx.tomike.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        // 被观察者
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.e("subscribe", "1");
                emitter.onNext("1");
                Log.e("subscribe", "2");
                emitter.onNext("2");
                Log.e("subscribe", "3");
                emitter.onNext("3");
                emitter.onComplete();

                Log.e("subscribe", "4");
                emitter.onNext("4");

                Log.e("subscribe", "5");
                emitter.onNext("5");

                Log.e("subscribe", "6");
                emitter.onNext("6");
            }
//        }).map(new Function<String, String>() {
//            @Override
//            public String apply(String s) throws Exception {
//                return null;
//            }
        }).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Exception {
                return null;
            }
        })
//                .observeOn(.mainThread())//回调在主线程
                .subscribeOn(Schedulers.io());//执行在io线程;

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("onSubscribe", d.toString());
            }

            @Override
            public void onNext(String s) {
                Log.e("onNext", s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        // 订阅
        observable.subscribe(observer);




//        Observable intervalObservable = Observable.interval(3, TimeUnit.SECONDS).subscribe(new Action);
//        Observable.range(0,5).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(Integer integer) {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

    }
}
