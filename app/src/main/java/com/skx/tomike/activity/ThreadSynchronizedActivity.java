package com.skx.tomike.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.skx.tomike.R;

import java.util.ArrayList;
import java.util.List;


/**
 * 描述 : 线程同步demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019-12-19 17:03
 */
public class ThreadSynchronizedActivity extends AppCompatActivity {

    public final static String TAG = "ThreadSynchronizedActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_synchronized);
        initView();
    }

    private void initView() {
        final SynchronizedTest synchronizedTest = new SynchronizedTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedTest.fun2();

            }
        }, "T1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedTest.fun2();

            }
        }, "T2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedTest.fun2();

            }
        }, "T3").start();


        List<? super String> k = new ArrayList<>();
        k.add("a");


    }
}

class SynchronizedTest {

    private final static String TAG = "SynchronizedTest";

    private final Object LOCK = new Object();

    public void fun0() {
        synchronized (SynchronizedTest.class) {
        }
    }

    // 同步代码块。实际获取的是当前类的monitor
    public void fun1() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                Log.e(TAG, Thread.currentThread().getName() + " -> " + i);
            }
        }
    }

    // 同步代码块。获取的是LOCK实例的monitor，如果实例相同，那么只有一个线程能执行该块内容
    public void fun2() {
        synchronized (LOCK) {
            for (int i = 0; i < 5; i++) {
                Log.e(TAG, Thread.currentThread().getName() + " -> " + i);
            }
        }
    }

    // 同步方法（非静态）。此时锁的是当前实例的对象，也就是说同一对象争用该锁。
    public synchronized void fun3() {
        for (int i = 0; i < 5; i++) {
            Log.e(TAG, Thread.currentThread().getName() + " -> " + i);
        }
    }

    public static synchronized void fun4() {
    }

}
