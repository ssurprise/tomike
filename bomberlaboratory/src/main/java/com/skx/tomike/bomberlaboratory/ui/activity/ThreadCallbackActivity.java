package com.skx.tomike.bomberlaboratory.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.skx.tomike.bomberlaboratory.R;

import java.util.Random;
import java.util.concurrent.Callable;


/**
 * 描述 : 获取线程返回值demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019-12-19 17:03
 */
public class ThreadCallbackActivity extends AppCompatActivity {

    private final static String TAG = "ThreadCallbackActivity";

    private TextView mTvLogcat;
    private boolean isWorking = false;

    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTvLogcat.append(msg.obj.toString() + "\n");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_callback);
        initView();
    }

    private void initView() {
        mTvLogcat = findViewById(R.id.tv_threadCallback_logcat);
    }

    /**
     * 主线程等待法
     */
    public void mainThreadWaitFun(View view) {
        if (isWorking) return;
        isWorking = true;
        mTvLogcat.setText("");

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message startMsg = mHandler.obtainMessage(0);
                startMsg.obj = "主线程等待法 start";
                mHandler.sendMessage(startMsg);

                ReturnValueRunnable returnValueRunnable = new ReturnValueRunnable();
                Thread thread = new Thread(returnValueRunnable);
                thread.start();

                while (TextUtils.isEmpty(returnValueRunnable.value)) {
                    Message loopMsg = mHandler.obtainMessage(0);
                    loopMsg.obj = "主线程 waiting";
                    mHandler.sendMessage(loopMsg);
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Message endMsg = mHandler.obtainMessage(0);
                endMsg.obj = "主线程收到子线程的返回值，return =\"" + returnValueRunnable.value + "\"";
                mHandler.sendMessage(endMsg);
                isWorking = false;
            }
        }).start();
    }

    public void threadJoinFun(View view) {
        if (isWorking) return;
        isWorking = true;
        mTvLogcat.setText("");

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message startMsg = mHandler.obtainMessage(1);
                startMsg.obj = "使用Thread的join函数 start";
                mHandler.sendMessage(startMsg);

                ReturnValueRunnable returnValueRunnable = new ReturnValueRunnable();
                Thread thread = new Thread(returnValueRunnable);
                thread.start();

                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message endMsg = mHandler.obtainMessage(1);
                endMsg.obj = "主线程收到子线程的返回值，return =\"" + returnValueRunnable.value + "\"";
                mHandler.sendMessage(endMsg);
                isWorking = false;
            }
        }).start();
    }

    public void onFutureTaskFun(View view) {
    }

    class ReturnValueRunnable implements Runnable {

        public String value;
        private Random random = new Random();

        @Override
        public void run() {
            Message message = mHandler.obtainMessage(0);
            message.obj = "子线程 doing";
            mHandler.sendMessage(message);

            try {
                int i = random.nextInt(3000);
                Log.e(TAG, i + "");
                Thread.currentThread().sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            value = "luckin";

            Message message2 = mHandler.obtainMessage(0);
            message2.obj = "子线程 done，return =\"" + value + "\"";
            mHandler.sendMessage(message2);
        }
    }

    class FunCallback implements Callable<String> {

        @Override
        public String call() throws Exception {

            return null;
        }
    }
}
