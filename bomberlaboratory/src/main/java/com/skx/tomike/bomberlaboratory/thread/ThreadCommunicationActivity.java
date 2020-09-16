package com.skx.tomike.bomberlaboratory.thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.skx.tomike.bomberlaboratory.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;


/**
 * 描述 : 线程间通信demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019-12-19 17:03
 */
public class ThreadCommunicationActivity extends SkxBaseActivity<BaseViewModel> implements View.OnClickListener {

    private final static int INIT = 20;

    private ScrollView mSvLogcat;
    private TextView mLogcat;

    private Production mProduction;
    private ProductionPro mProductionPro;

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
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("线程间通信 wait/notify").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_thread_communication;
    }

    @Override
    protected void subscribeEvent() {
    }

    @Override
    protected void initView() {
        mSvLogcat = findViewById(R.id.sv_threadCommunication_logcat);

        findViewById(R.id.btn_threadCommunication_unused).setOnClickListener(this);
        findViewById(R.id.btn_threadCommunication_waitAndNotify).setOnClickListener(this);
        findViewById(R.id.btn_threadCommunication_waitAndNotifyShortcoming).setOnClickListener(this);

        mLogcat = findViewById(R.id.tv_threadCommunication_logcat);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_threadCommunication_unused) {
            mLogcat.setText("");
            mProduction = new Production();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        mProduction.production();
                    }
                }
            }, "producer-thread").start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int i = 0;
                    while (i < 20) {
                        mProduction.consumer();
                        i++;
                    }
                }
            }, "consumer-thread").start();
        } else if (id == R.id.btn_threadCommunication_waitAndNotify) {
            mLogcat.setText("");
            mProductionPro = new ProductionPro();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        mProductionPro.make();
                    }
                }
            }, "producer-thread").start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        mProductionPro.consume();
                    }
                }
            }, "consumer-thread").start();
        } else if (id == R.id.btn_threadCommunication_waitAndNotifyShortcoming) {
            mLogcat.setText("");
            mProductionPro = new ProductionPro();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        mProductionPro.make();
                    }
                }
            }, "producer-thread").start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        mProductionPro.make();
                    }
                }
            }, "producer-thread-2").start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        mProductionPro.consume();
                    }
                }
            }, "consumer-thread").start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        mProductionPro.consume();
                    }
                }
            }, "consumer-thread-2").start();
        }
    }

    class Production {

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

    class ProductionPro {

        private int i = 0;
        private final Object LOCK = new Object();
        private volatile boolean isProdeced = false;

        /**
         * 生产产品
         */
        public void make() {
            synchronized (LOCK) {
                if (isProdeced) {
                    // 如果已经生产了，就等待
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

                        LOCK.notifyAll();
                    }
                }
            }
        }

        /**
         * 消费产品
         */
        public void consume() {
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
