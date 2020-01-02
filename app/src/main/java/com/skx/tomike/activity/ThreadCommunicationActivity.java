package com.skx.tomike.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.skx.tomike.R;


/**
 * 描述 : 线程间通信demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019-12-19 17:03
 */
public class ThreadCommunicationActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "ThreadCommunicationActivity";

    private final static int INIT = 20;

    private ScrollView mSvLogcat;
    private TextView mLogcat;

    private ProductionConsumer mProductionConsumer;
    private ProductionConsumer1 mProductionConsumer1;

    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case -1:
//                    mLogcat.append(msg.obj.toString());
                    break;
                case 0:
                    mLogcat.append("\n 生产数据 " + msg.arg1);
                    break;
                case 1:
                    mLogcat.append("\n 消费数据 " + msg.arg1);
                    break;
            }
            mSvLogcat.fullScroll(View.FOCUS_DOWN);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_communication);
        initView();
    }

    private void initView() {
        mSvLogcat = findViewById(R.id.sv_threadCommunication_logcat);

        findViewById(R.id.btn_threadCommunication_unused).setOnClickListener(this);
        findViewById(R.id.btn_threadCommunication_waitAndNotify).setOnClickListener(this);
        findViewById(R.id.btn_threadCommunication_waitAndNotifyShortcoming).setOnClickListener(this);

        mLogcat = findViewById(R.id.tv_threadCommunication_logcat);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_threadCommunication_unused:
                mLogcat.setText("");
                mProductionConsumer = new ProductionConsumer();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            mProductionConsumer.production();
                        }
                    }
                }, "producer-thread").start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int i = 0;
                        while (i < 5) {
                            mProductionConsumer.consumer();
                            i++;
                        }
                    }
                }, "consumer-thread").start();
                break;

            case R.id.btn_threadCommunication_waitAndNotify:
                mLogcat.setText("");
                mProductionConsumer1 = new ProductionConsumer1();
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
                break;

            case R.id.btn_threadCommunication_waitAndNotifyShortcoming:
                mLogcat.setText("");
                mProductionConsumer1 = new ProductionConsumer1();
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
                            mProductionConsumer1.production();
                        }
                    }
                }, "producer-thread-2").start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            mProductionConsumer1.consumer();
                        }
                    }
                }, "consumer-thread").start();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            mProductionConsumer1.consumer();
                        }
                    }
                }, "consumer-thread-2").start();
                break;
        }
    }

    class ProductionConsumer {

        private int i = 1;

        public synchronized void production() {
            if (i <= INIT) {
                Message message = mHandler.obtainMessage(0);
                message.arg1 = i++;
                mHandler.sendMessage(message);
            }
        }

        public synchronized void consumer() {
            Message message = mHandler.obtainMessage(1);
            message.arg1 = i;
            mHandler.sendMessage(message);
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
                    if (i < INIT) {
                        i++;
                        isProdeced = true;

                        Message message = mHandler.obtainMessage(0);
                        message.arg1 = i;
                        mHandler.sendMessage(message);

                        LOCK.notify();
                    }
                }
            }
        }

        public synchronized void consumer() {
            synchronized (LOCK) {
                if (isProdeced) {
                    Message message = mHandler.obtainMessage(1);
                    message.arg1 = i;
                    mHandler.sendMessage(message);

                    isProdeced = false;
                    LOCK.notify();

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
