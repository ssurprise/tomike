package com.skx.tomike.bomberlaboratory.ui.activity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.skx.tomike.bomberlaboratory.R;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;


/**
 * 描述 : 获取线程返回值demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019-12-19 17:03
 */
public class ThreadCallbackActivity extends SkxBaseActivity {

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
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("获取线程返回值").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_thread_callback;
    }

    @Override
    protected void subscribeEvent() {
    }

    @Override
    protected void initView() {
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


    /**
     * thread-join 法
     */
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


    /**
     * Callable - FutureTask 法
     */
    public void onFutureTaskFun(View view) {
        if (isWorking) return;
        isWorking = true;
        mTvLogcat.setText("");

        // 模拟主线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendMessageToLogcat("使用Callable - FutureTask实现 start");

                // 开启子线程
                FutureTask<String> futureTask = new FutureTask<>(new FunCallable());
                new Thread(futureTask).start();

                if (!futureTask.isDone()) {
                    // 子线程未执行完毕，主线程等待
                    sendMessageToLogcat("主线程 waiting");
                }

                // 主线程收到子线程的返回值，return = returnValueRunnable.value
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

    /**
     * Callable - ThreadPool 法
     */
    public void onThreadPoolFun(View view) {
        if (isWorking) return;
        isWorking = true;
        mTvLogcat.setText("");

        // 模拟主线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                sendMessageToLogcat("使用Callable - ThreadPool实现 start");

                // 通过线程池开启一个子线程
                ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
                Future<String> future = cachedThreadPool.submit(new FunCallable());

                if (!future.isDone()) {
                    // 子线程未执行完毕，主线程等待
                    sendMessageToLogcat("主线程 waiting");
                }

                // 主线程收到子线程的返回值，return = returnValueRunnable.value
                try {
                    sendMessageToLogcat("主线程收到子线程的返回值，return =\"" + future.get() + "\"");
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    cachedThreadPool.shutdown();
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
