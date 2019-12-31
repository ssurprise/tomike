package com.skx.tomike.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.skx.tomike.R;


/**
 * 描述 : 线程间通信demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019-12-19 17:03
 */
public class ThreadCommunicationActivity extends AppCompatActivity {

    public final static String TAG = "ThreadCommunicationActivity";

    private ProductionConsumer mProductionConsumer;
    private ProductionConsumer1 mProductionConsumer1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_communication);
        initView();
    }

    private void initView() {
        mProductionConsumer = new ProductionConsumer();
        mProductionConsumer1 = new ProductionConsumer1();
    }

    public void onThreadCommunicationStart(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    mProductionConsumer1.production();
                }
            }
        }, "producer-thread").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    mProductionConsumer1.consumer();
                }
            }
        }, "consumer-thread").start();
    }

    class ProductionConsumer {

        private int i = 1;

        public synchronized void production() {
            if (i <= 200)
                Log.e(TAG, "生产数据" + i++);
        }

        public synchronized void consumer() {
            Log.e(TAG, "消费数据" + i);
        }
    }

    class ProductionConsumer1 {

        private int i = 0;
        private final Object LOCK = new Object();
        private volatile boolean isProdeced = false;

        public void production() {
            synchronized (LOCK) {
                if (isProdeced) {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    if (i < 20) {
                        i++;
                        Log.e(TAG, "生产数据" + i);
                        LOCK.notify();
                        isProdeced = true;
                    }
                }
            }
        }

        public synchronized void consumer() {
            synchronized (LOCK) {
                if (isProdeced) {
                    Log.e(TAG, "消费数据" + i);
                    LOCK.notify();
                    isProdeced = false;

                } else {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
