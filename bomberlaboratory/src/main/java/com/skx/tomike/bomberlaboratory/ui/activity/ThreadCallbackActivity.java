package com.skx.tomike.bomberlaboratory.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.skx.tomike.bomberlaboratory.R;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


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


    private void sendMessageToLogcat(@NonNull String msg) {
        Message startMsg = mHandler.obtainMessage(1);
        startMsg.obj = msg;
        mHandler.sendMessage(startMsg);
    }

    /**
     * 主线程等待法
     */
    public void mainThreadWaitFun(View view) {
        if (isWorking) return;
        isWorking = true;
        mTvLogcat.setText("");

        // 模拟主线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendMessageToLogcat("主线程等待法 start");

                // 开启子线程
                ReturnValueRunnable returnValueRunnable = new ReturnValueRunnable();
                Thread thread = new Thread(returnValueRunnable);
                thread.start();

                // 主线程轮循等待
                while (TextUtils.isEmpty(returnValueRunnable.getValue())) {
                    sendMessageToLogcat("主线程 waiting");
                    try {
                        Thread.currentThread().sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // 主线程收到子线程的返回值，return = returnValueRunnable.value
                sendMessageToLogcat("主线程收到子线程的返回值，return =\"" + returnValueRunnable.getValue() + "\"");
                isWorking = false;
            }
        }).start();
    }


    public void threadJoinFun(View view) {
        if (isWorking) return;
        isWorking = true;
        mTvLogcat.setText("");

        // 模拟主线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendMessageToLogcat("使用Thread的join函数 start");

                // 开启子线程
                ReturnValueRunnable returnValueRunnable = new ReturnValueRunnable();
                Thread thread = new Thread(returnValueRunnable);
                thread.start();

                // 主线程join 等待子线程执行完毕
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 主线程收到子线程的返回值，return = returnValueRunnable.value
                sendMessageToLogcat("主线程收到子线程的返回值，return =\"" + returnValueRunnable.getValue() + "\"");
                isWorking = false;
            }
        }).start();
    }


    public void onFutureTaskFun(View view) {
        if (isWorking) return;
        isWorking = true;
        mTvLogcat.setText("");

        new Thread(new Runnable() {
            @Override
            public void run() {
                sendMessageToLogcat("使用FutureTask实现 start");

                FutureTask<String> futureTask = new FutureTask<>(new FunCallable());
                new Thread(futureTask).start();

                if (!futureTask.isDone()) {
                    sendMessageToLogcat("主线程 waiting");
                }

                try {
                    sendMessageToLogcat("主线程收到子线程的返回值，return =\"" + futureTask.get() + "\"");
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isWorking = false;
            }
        }).start();
    }

    class ReturnValueRunnable implements Runnable {

        private String value;
        private Random random = new Random();

        @Override
        public void run() {
            sendMessageToLogcat("子线程 doing");

            try {
                int i = random.nextInt(3000);
                Log.e(TAG, i + "");
                Thread.currentThread().sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            value = "luckin";

            sendMessageToLogcat("子线程 done，return =\"" + value + "\"");
        }

        public String getValue() {
            return value;
        }
    }

    class FunCallable implements Callable<String> {

        private Random random = new Random();

        @Override
        public String call() throws Exception {
            sendMessageToLogcat("子线程 doing");

            try {
                int i = random.nextInt(3000);
                Log.e(TAG, i + "");
                Thread.currentThread().sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String value = "luckin";
            sendMessageToLogcat("子线程 done，return =\"" + value + "\"");

            return value;
        }
    }
}
