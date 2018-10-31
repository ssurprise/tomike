package com.skx.tomike.activity.function;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.skx.tomike.R;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        // 被观察者
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello");
                subscriber.onNext("Hi");
                subscriber.onNext("Aloha");
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io())// 指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e("onCompleted", "完毕");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("onNext", s);
                    }

                    @Override
                    public void onStart() {
                        super.onStart();
                        Log.e("onAnimatorStart", "onAnimatorStart");
                    }
                });

//
//        Observable.just("images/logo.png") // 输入类型 String
//                .map(new Func1<String, Bitmap>() {
//                    @Override
//                    public Bitmap call(String filePath) { // 参数类型 String
//                        return getBitmapFromPath(filePath); // 返回类型 Bitmap
//                    }
//                })
//                .subscribe(new Action1<Bitmap>() {
//                    @Override
//                    public void call(Bitmap bitmap) { // 参数类型 Bitmap
//                        showBitmap(bitmap);
//                    }
//                });
    }
}
