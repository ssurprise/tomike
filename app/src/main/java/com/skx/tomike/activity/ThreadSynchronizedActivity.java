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
                synchronizedTest.synMethod2();

            }
        }, "T1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedTest.synMethod2();

            }
        }, "T2").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronizedTest.synMethod2();

            }
        }, "T3").start();


        List<? super String> k = new ArrayList<>();
        k.add("a");


    }
}

class SynchronizedTest {

    private final static String TAG = "SynchronizedTest";

    private final Object lock = new Object();

    public void synMethod0() {
        synchronized (SynchronizedTest.class) {
        }
    }

    public void synMethod1() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                Log.e(TAG, Thread.currentThread().getName() + " -> " + i);
            }
        }
    }

    public void synMethod2() {
        synchronized (lock) {
            for (int i = 0; i < 5; i++) {
                Log.e(TAG, Thread.currentThread().getName() + " -> " + i);
            }
        }
    }

    /*
    synchronized修饰静态同步方法。此时锁的是类的class对象。
    对象锁，方法级别,同一对象争用该锁,普通（非静态）方法,synchronized的锁绑定在调用该方法的对象上
     */
    public synchronized void synMethod3() {
        for (int i = 0; i < 5; i++) {
            Log.e(TAG, Thread.currentThread().getName() + " -> " + i);
        }
    }

    public static synchronized void synMethod4() {
    }

}
