package com.skx.tomike.bomberlaboratory.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.skx.tomike.bomberlaboratory.R;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;

import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述 : 线程池 Executors
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019-12-19 17:03
 */
public class ThreadPoolExecutorsActivity extends SkxBaseActivity<BaseViewModel> implements View.OnClickListener {

    private SeekBar seekb_threadPool_process;
    private SeekBar seekb_threadPool_process1;
    private SeekBar seekb_threadPool_process2;
    private SeekBar seekb_threadPool_process3;
    private TextView tv_threadPool_processText;
    private TextView tv_threadPool_processText1;
    private TextView tv_threadPool_processText2;
    private TextView tv_threadPool_processText3;

    private SeekBar seekb_threadPool_process5;
    private TextView tv_threadPool_processText5;
    private SeekBar seekb_threadPool_process6;
    private TextView tv_threadPool_processText6;

    private static final int TASK_COUNT = 4;

    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    seekb_threadPool_process.setProgress(msg.arg1);
                    tv_threadPool_processText.setText(String.format(Locale.getDefault(), "%d%%", msg.arg1));
                    break;
                case 1:
                    seekb_threadPool_process1.setProgress(msg.arg1);
                    tv_threadPool_processText1.setText(String.format(Locale.getDefault(), "%d%%", msg.arg1));
                    break;
                case 2:
                    seekb_threadPool_process2.setProgress(msg.arg1);
                    tv_threadPool_processText2.setText(String.format(Locale.getDefault(), "%d%%", msg.arg1));
                    break;
                case 3:
                    seekb_threadPool_process3.setProgress(msg.arg1);
                    tv_threadPool_processText3.setText(String.format(Locale.getDefault(), "%d%%", msg.arg1));
                    break;
                case 5:
                    seekb_threadPool_process5.setProgress(msg.arg1);
                    tv_threadPool_processText5.setText(String.format(Locale.getDefault(), "%d%%", msg.arg1));
                    break;
                case 6:
                    seekb_threadPool_process6.setProgress(msg.arg1);
                    tv_threadPool_processText6.setText(String.format(Locale.getDefault(), "%d%%", msg.arg1));
                    break;
            }
        }
    };

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("线程池 Executors").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_thread_pool_executors;
    }

    @Override
    protected void subscribeEvent() {
    }

    @Override
    protected void initView() {
        findViewById(R.id.btn_threadPoolExecutors_newSingleThreadExecutor).setOnClickListener(this);
        findViewById(R.id.btn_threadPoolExecutors_newFixedThreadPool).setOnClickListener(this);
        findViewById(R.id.btn_threadPoolExecutors_newCachedThreadPool).setOnClickListener(this);
        findViewById(R.id.btn_threadPoolExecutors_newScheduledThreadPool).setOnClickListener(this);

        seekb_threadPool_process = findViewById(R.id.seekb_threadPoolExecutors_process);
        tv_threadPool_processText = findViewById(R.id.tv_threadPoolExecutors_processText);

        seekb_threadPool_process1 = findViewById(R.id.seekb_threadPoolExecutors_process1);
        tv_threadPool_processText1 = findViewById(R.id.tv_threadPoolExecutors_processText1);

        seekb_threadPool_process2 = findViewById(R.id.seekb_threadPoolExecutors_process2);
        tv_threadPool_processText2 = findViewById(R.id.tv_threadPoolExecutors_processText2);

        seekb_threadPool_process3 = findViewById(R.id.seekb_threadPoolExecutors_process3);
        tv_threadPool_processText3 = findViewById(R.id.tv_threadPoolExecutors_processText3);

        seekb_threadPool_process5 = findViewById(R.id.seekb_threadPoolExecutors_process5);
        tv_threadPool_processText5 = findViewById(R.id.tv_threadPoolExecutors_processText5);

        seekb_threadPool_process6 = findViewById(R.id.seekb_threadPoolExecutors_process6);
        tv_threadPool_processText6 = findViewById(R.id.tv_threadPoolExecutors_processText6);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        ExecutorService mExecutorService;
        if (id == R.id.btn_threadPoolExecutors_newSingleThreadExecutor) {
            mExecutorService = Executors.newSingleThreadExecutor();
            mExecutorService.execute(runnable0);
            mExecutorService.execute(runnable1);
            mExecutorService.execute(runnable2);
            mExecutorService.execute(runnable3);

        } else if (id == R.id.btn_threadPoolExecutors_newFixedThreadPool) {
            mExecutorService = Executors.newFixedThreadPool(4);
            mExecutorService.execute(runnable0);
            mExecutorService.execute(runnable1);
            mExecutorService.execute(runnable2);
            mExecutorService.execute(runnable3);
            mExecutorService.execute(runnable5);
            mExecutorService.execute(runnable6);

        } else if (id == R.id.btn_threadPoolExecutors_newCachedThreadPool) {
            mExecutorService = Executors.newCachedThreadPool();
            mExecutorService.execute(runnable0);
            mExecutorService.execute(runnable1);
            mExecutorService.execute(runnable2);
            mExecutorService.execute(runnable3);

        } else if (id == R.id.btn_threadPoolExecutors_newScheduledThreadPool) {
            mExecutorService = Executors.newScheduledThreadPool(4);
            mExecutorService.execute(runnable0);
            mExecutorService.execute(runnable1);
            mExecutorService.execute(runnable2);
            mExecutorService.execute(runnable3);
            mExecutorService.execute(runnable5);
            mExecutorService.execute(runnable6);
        }
    }

    private Runnable runnable0 = new PoolRunnable(0);
    private Runnable runnable1 = new PoolRunnable(1);
    private Runnable runnable2 = new PoolRunnable(2);
    private Runnable runnable3 = new PoolRunnable(3);
    private Runnable runnable5 = new PoolRunnable(5);
    private Runnable runnable6 = new PoolRunnable(6);

    private class PoolRunnable implements Runnable {

        private int mTaskId;

        public PoolRunnable(int taskId) {
            this.mTaskId = taskId;
        }

        @Override
        public void run() {
            Log.e("TaskId：" + mTaskId, Thread.currentThread().getName());
            int process = 0;
            while (process < 100) {
                process += 1;
                Message message = mHandler.obtainMessage(mTaskId, process, 0);
                mHandler.sendMessage(message);
                try {
                    switch (mTaskId) {
                        case 0:
                            Thread.sleep(100);
                            break;
                        case 1:
                            Thread.sleep(120);
                            break;
                        case 2:
                            Thread.sleep(50);
                            break;
                        case 3:
                            Thread.sleep(80);
                            break;
                        case 5:
                            Thread.sleep(110);
                            break;
                        case 6:
                            Thread.sleep(70);
                            break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
