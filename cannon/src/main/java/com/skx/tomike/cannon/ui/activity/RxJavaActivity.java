package com.skx.tomike.cannon.ui.activity;

import android.os.SystemClock;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.cannon.R;
import com.skx.tomike.cannon.bean.Student;
import com.skx.tomike.cannon.bean.Transcript;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_RXJAVA;

/**
 * 描述 : RxJava 实现并行、串行访问数据
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/23 4:25 PM
 */
@Route(path = ROUTE_PATH_RXJAVA)
public class RxJavaActivity extends SkxBaseActivity<BaseViewModel> {

    private LinearLayout mRlLoading;
    private TextView mTvLoadingText;

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText(" RxJava 实现并行、串行").create();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_rx_java;
    }

    @Override
    protected void initView() {
        mRlLoading = findViewById(R.id.rl_rxjava_loading);
        mTvLoadingText = findViewById(R.id.rl_rxjava_loadingText);
    }

    /**
     * 并行执行
     * <p>
     * 案例：同时请求接口A和接口B，最终返回接口A 和接口B的合并数据。
     */
    public void parallelExecute(View view) {
        Observable.zip(
                Observable.create(new ObservableOnSubscribe<Double>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Double> emitter) {
                        Log.e(TAG, "create 1:" + Thread.currentThread().getName());

                        Log.e("Observable", "1.1");
                        SystemClock.sleep(2000);
                        Log.e("Observable", "1.2");

                        emitter.onNext(10d);
                        emitter.onComplete();
                    }
                }).subscribeOn(Schedulers.io()),// 指定 subscribe() 所发生的线程，简单来说就是发射事件的线程。或者叫做事件产生的线程。

                Observable.create(new ObservableOnSubscribe<Double>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Double> emitter) {
                        Log.e(TAG, "create 2:" + Thread.currentThread().getName());

                        Log.e("Observable", "2.1");
                        SystemClock.sleep(3000);
                        Log.e("Observable", "2.2");

                        emitter.onNext(8d);
                        emitter.onComplete();
                    }
                })/*.subscribeOn(Schedulers.io())*/,// 指定 subscribe() 所发生的线程，简单来说就是发射事件的线程。或者叫做事件产生的线程。

                new BiFunction<Double, Double, Double>() {
                    @NonNull
                    @Override
                    public Double apply(@NonNull Double aDouble, @NonNull Double aDouble2) {
                        Log.e(TAG, "create 3:" + Thread.currentThread().getName());

                        Log.e("Observable", "3");
                        return aDouble + aDouble2;
                    }
                }
        ).subscribeOn(Schedulers.io())// 指定 subscribe() 所发生的线程，简单来说就是发射事件的线程。或者叫做事件产生的线程。
                .observeOn(AndroidSchedulers.mainThread())// 指定观察者接收事件的线程。或者叫做事件消费的线程。
                .subscribe(new Observer<Double>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e("Observer-onSubscribe", "subscribe");
                        mRlLoading.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(@NonNull Double aDouble) {
                        Toast.makeText(RxJavaActivity.this, aDouble + "", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("Observer-onError", "error");
                        mRlLoading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {
                        Log.e("Observer-onComplete", "complete");
                        mRlLoading.setVisibility(View.GONE);
                    }
                });

        /*
        外部指定了线程：
        1. 内部均指定线程                 -> 并行执行 create 1、2 分别执行在不同的子线程，create 3的执行线程在执行时间最长的子线程中
        2. 内部均没有指定线程              -> 串行执行 create 1:执行在子线程1,待执行完毕后 create 2同样执行在子线程1，而后create 3同
        3. 内部a指定线程，b没有指定线程    -> 并行执行 同1
        4. 内部a没有指定线程，b指定线程    -> 串行执行 create 1:执行在子线程1，待执行完毕后 create 2执行在子线程2，create 3 串行执行在子线程2

        外部没有指定线程：
        1. 内部均指定线程                -> 并行执行
        2. 内部均没有指定线程            -> 串行执行  （不建议，可能会造成主线程卡顿）create 1、2、3 顺序执行在main 线程
        3. 内部a指定线程，b没有指定线程    -> 并行执行 （不建议，可能会造成主线程卡顿）create 2:执行在main 线程，create 1 和create 3 执行在同一个子线程
        3. 内部a没有指定线程，b指定线程    -> 串行执行 （不建议，可能会造成主线程卡顿）create 1:执行在main 线程，create 2 和create 3 执行在同一个子线程

         */
    }

    /**
     * 串行执行。
     * <p>
     * 案例：比如先请求接口A，根据接口A 返回的数据再请求接口B，最终返回接口B的数据
     */
    public void serialExecute(View view) {
        Observable.create(new ObservableOnSubscribe<Double>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Double> emitter) {
                Log.e(TAG, "create :" + Thread.currentThread().getName());
                Log.e("Observable", "1.1");
                SystemClock.sleep(2000);
                Log.e("Observable", "1.2");

                emitter.onNext(5d);
                emitter.onComplete();
//                emitter.onError(new Throwable());
            }
        }).flatMap(new Function<Double, ObservableSource<Double>>() {
            @Override
            public ObservableSource<Double> apply(@NonNull Double aDouble) {
                return Observable.create(new ObservableOnSubscribe<Double>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Double> emitter) {
                        Log.e(TAG, "flatMap :" + Thread.currentThread().getName());

                        Log.e("Observable", "2.1");
                        SystemClock.sleep(2000);
                        Log.e("Observable", "2.2");

                        emitter.onNext(20d);
                        emitter.onComplete();
//                                emitter.onError(new Throwable());
                    }
                });
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Double>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e("Observer-onSubscribe", "subscribe");
                        mRlLoading.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(@NonNull Double aDouble) {
                        Log.e(TAG, "onNext :" + Thread.currentThread().getName());

                        Log.e("Observer-onNext", aDouble + "");
                        Toast.makeText(RxJavaActivity.this, aDouble + "", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("Observer-onError", "error");
                        mRlLoading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete :" + Thread.currentThread().getName());

                        Log.e("Observer-onComplete", "complete");
                        mRlLoading.setVisibility(View.GONE);
                    }
                });
    }


    /**
     * 串行
     * <p>
     * 案例：请求接口A，根据接口A 的返回值再去请求接口B，返回接口A 和接口B 的合并数据。
     */
    public void serialAndMerge1(View view) {
        Observable
                .create(new ObservableOnSubscribe<Student>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Student> emitter) {
                        mTvLoadingText.setText("学生信息获取中...");

                        SystemClock.sleep(2000);

                        Student student = new Student();
                        student.setId(101);
                        student.setSex("男");
                        student.setName("小黑");
                        student.setClazz("3年级2班");

                        emitter.onNext(student);
                        emitter.onComplete();
//                        emitter.onError(new Throwable());
                    }
                })
                .subscribeOn(Schedulers.io())// 指定 subscribe() 所发生的线程，简单来说就是发射事件的线程。或者叫做事件产生的线程。
                .observeOn(AndroidSchedulers.mainThread())// 指定观察者接受事件所在的线程
                .flatMap(new Function<Student, ObservableSource<Pair<Student, Transcript>>>() {
                    @Override
                    public ObservableSource<Pair<Student, Transcript>> apply(@NonNull Student student) {
                        Log.e("flatMap", "222222");
                        return Observable
                                .zip(Observable.just(student),
                                        Observable.create(new ObservableOnSubscribe<Transcript>() {
                                            @Override
                                            public void subscribe(@NonNull ObservableEmitter<Transcript> emitter) throws Exception {

                                                mTvLoadingText.setText("成绩单获取中...");

                                                Thread.sleep(3000);

                                                Transcript transcript = new Transcript();
                                                transcript.id = 101;
                                                transcript.Chinese = 100;
                                                transcript.English = 98;
                                                transcript.Mathematics = 99;

                                                emitter.onNext(transcript);
                                                emitter.onComplete();
//                                                emitter.onError(new Throwable());
                                            }
                                        }).subscribeOn(Schedulers.io())// 指定 subscribe() 所发生的线程，简单来说就是发射事件的线程。或者叫做事件产生的线程。
                                                .observeOn(AndroidSchedulers.mainThread()),// 指定观察者接受事件所在的线程
                                        new BiFunction<Student, Transcript, Pair<Student, Transcript>>() {

                                            @NonNull
                                            @Override
                                            public Pair<Student, Transcript> apply(@NonNull Student student, @NonNull Transcript transcript) {
                                                Log.e("biz", "333333");
                                                return new Pair<>(student, transcript);
                                            }
                                        });
                    }
                })
                .subscribe(new Observer<Pair<Student, Transcript>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e("Observer-onSubscribe", "subscribe");
                        mRlLoading.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(@NonNull Pair<Student, Transcript> studentTranscriptPair) {
                        Log.e("Observer-onNext", "444444");
                        String studentTranscript = String.format("%s %s 语文成绩为：%s",
                                studentTranscriptPair.first.getClazz(),
                                studentTranscriptPair.first.getName(),
                                studentTranscriptPair.second.Chinese);
                        Toast.makeText(RxJavaActivity.this, studentTranscript, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("Observer-onError", "error");
                        mRlLoading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onComplete() {
                        Log.e("Observer-onComplete", "complete");
                        mRlLoading.setVisibility(View.GONE);
                    }
                });
    }

    /**
     * 串行执行.
     * <p>
     * 案例：请求接口A，根据接口A 的返回值再去请求接口B，返回接口A 和接口B 的合并数据。
     * 问题：这种方式有问题，当第一个错误的时候，直接就崩掉了
     */
    public void serialAndMerge2(View view) {
        ConnectableObservable<Double> PAY = Observable.create(new ObservableOnSubscribe<Double>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Double> emitter) {
                Log.e("Observable", "1.1");
                SystemClock.sleep(2000);
                Log.e("Observable", "1.2");

//                emitter.onNext(10d);
//                emitter.onComplete();
                emitter.onError(new Throwable());

            }
        }).subscribeOn(Schedulers.io())
                .publish();

        Observable<Double> flatMap = PAY
                .flatMap(new Function<Double, ObservableSource<Double>>() {
                    @Override
                    public ObservableSource<Double> apply(@NonNull Double aDouble) {
                        return Observable.create(new ObservableOnSubscribe<Double>() {
                            @Override
                            public void subscribe(@NonNull ObservableEmitter<Double> emitter) {
                                Log.e("Observable", "2.1");
                                SystemClock.sleep(2000);
                                Log.e("Observable", "2.2");

//                                emitter.onNext(20d);
//                                emitter.onComplete();
                                emitter.onError(new Throwable());
                            }
                        });
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        Observable.zip(PAY, flatMap, new BiFunction<Double, Double, Double>() {
            @NonNull
            @Override
            public Double apply(@NonNull Double aDouble, @NonNull Double aDouble2) {
                return aDouble + aDouble2;
            }
        }).subscribe(new Observer<Double>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.e("Observer-onSubscribe", "subscribe");
                mRlLoading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNext(@NonNull Double aDouble) {
                Toast.makeText(RxJavaActivity.this, aDouble + "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.e("Observer-onError", "error");
                mRlLoading.setVisibility(View.GONE);
            }

            @Override
            public void onComplete() {
                Log.e("Observer-onComplete", "complete");
                mRlLoading.setVisibility(View.GONE);
            }
        });
        PAY.connect();
    }

}
