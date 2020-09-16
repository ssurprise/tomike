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
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

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
    private TextView tv_threadPool_processText;
    private TextView tv_threadPool_log;

    private SeekBar seekb_threadPool_process1;
    private TextView tv_threadPool_processText1;
    private TextView tv_threadPool_task1log;

    private SeekBar seekb_threadPool_process2;
    private TextView tv_threadPool_processText2;
    private TextView tv_threadPool_task2log;

    private SeekBar seekb_threadPool_process3;
    private TextView tv_threadPool_processText3;
    private TextView tv_threadPool_task3log;

    private SeekBar seekb_threadPool_process4;
    private TextView tv_threadPool_processText4;
    private TextView tv_threadPool_task4log;

    private SeekBar seekb_threadPool_process5;
    private TextView tv_threadPool_processText5;
    private TextView tv_threadPool_task5log;

    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (msg.obj != null) {
                        tv_threadPool_log.setText(String.format("执行任务的线程为：%s", msg.obj.toString()));
                    }
                    seekb_threadPool_process.setProgress(msg.arg1);
                    tv_threadPool_processText.setText(String.format(Locale.getDefault(), "%d%%", msg.arg1));
                    break;
                case 1:
                    if (msg.obj != null) {
                        tv_threadPool_task1log.setText(String.format("执行任务的线程为：%s", msg.obj.toString()));
                    }
                    seekb_threadPool_process1.setProgress(msg.arg1);
                    tv_threadPool_processText1.setText(String.format(Locale.getDefault(), "%d%%", msg.arg1));
                    break;
                case 2:
                    if (msg.obj != null) {
                        tv_threadPool_task2log.setText(String.format("执行任务的线程为：%s", msg.obj.toString()));
                    }
                    seekb_threadPool_process2.setProgress(msg.arg1);
                    tv_threadPool_processText2.setText(String.format(Locale.getDefault(), "%d%%", msg.arg1));
                    break;
                case 3:
                    if (msg.obj != null) {
                        tv_threadPool_task3log.setText(String.format("执行任务的线程为：%s", msg.obj.toString()));
                    }
                    seekb_threadPool_process3.setProgress(msg.arg1);
                    tv_threadPool_processText3.setText(String.format(Locale.getDefault(), "%d%%", msg.arg1));
                    break;
                case 4:
                    if (msg.obj != null) {
                        tv_threadPool_task4log.setText(String.format("执行任务的线程为：%s", msg.obj.toString()));
                    }
                    seekb_threadPool_process4.setProgress(msg.arg1);
                    tv_threadPool_processText4.setText(String.format(Locale.getDefault(), "%d%%", msg.arg1));
                    break;
                case 5:
                    if (msg.obj != null) {
                        tv_threadPool_task5log.setText(String.format("执行任务的线程为：%s", msg.obj.toString()));
                    }
                    seekb_threadPool_process5.setProgress(msg.arg1);
                    tv_threadPool_processText5.setText(String.format(Locale.getDefault(), "%d%%", msg.arg1));
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
        tv_threadPool_log = findViewById(R.id.tv_threadPoolExecutors_log);

        seekb_threadPool_process1 = findViewById(R.id.seekb_threadPoolExecutors_process1);
        tv_threadPool_processText1 = findViewById(R.id.tv_threadPoolExecutors_processText1);
        tv_threadPool_task1log = findViewById(R.id.tv_threadPoolExecutors_task1log);

        seekb_threadPool_process2 = findViewById(R.id.seekb_threadPoolExecutors_process2);
        tv_threadPool_processText2 = findViewById(R.id.tv_threadPoolExecutors_processText2);
        tv_threadPool_task2log = findViewById(R.id.tv_threadPoolExecutors_task2log);

        seekb_threadPool_process3 = findViewById(R.id.seekb_threadPoolExecutors_process3);
        tv_threadPool_processText3 = findViewById(R.id.tv_threadPoolExecutors_processText3);
        tv_threadPool_task3log = findViewById(R.id.tv_threadPoolExecutors_task3log);

        seekb_threadPool_process4 = findViewById(R.id.seekb_threadPoolExecutors_process4);
        tv_threadPool_processText4 = findViewById(R.id.tv_threadPoolExecutors_processText4);
        tv_threadPool_task4log = findViewById(R.id.tv_threadPoolExecutors_task4log);

        seekb_threadPool_process5 = findViewById(R.id.seekb_threadPoolExecutors_process5);
        tv_threadPool_processText5 = findViewById(R.id.tv_threadPoolExecutors_processText5);
        tv_threadPool_task5log = findViewById(R.id.tv_threadPoolExecutors_task5log);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        ExecutorService mExecutorService = null;
        if (id == R.id.btn_threadPoolExecutors_newSingleThreadExecutor) {
            mExecutorService = Executors.newSingleThreadExecutor();

        } else if (id == R.id.btn_threadPoolExecutors_newFixedThreadPool) {
            mExecutorService = Executors.newFixedThreadPool(3);

        } else if (id == R.id.btn_threadPoolExecutors_newCachedThreadPool) {
            mExecutorService = Executors.newCachedThreadPool();

        } else if (id == R.id.btn_threadPoolExecutors_newScheduledThreadPool) {
            mExecutorService = Executors.newScheduledThreadPool(3);
        }
        if (mExecutorService != null) {
            mExecutorService.execute(runnable0);
            mExecutorService.execute(runnable1);
            mExecutorService.execute(runnable2);
            mExecutorService.execute(runnable3);
            mExecutorService.execute(runnable4);
            mExecutorService.execute(runnable5);
        }
    }

    private Runnable runnable0 = new PoolRunnable(0);
    private Runnable runnable1 = new PoolRunnable(1);
    private Runnable runnable2 = new PoolRunnable(2);
    private Runnable runnable3 = new PoolRunnable(3);
    private Runnable runnable4 = new PoolRunnable(4);
    private Runnable runnable5 = new PoolRunnable(5);

    private class PoolRunnable implements Runnable {

        private int mTaskId;

        public PoolRunnable(int taskId) {
            this.mTaskId = taskId;
        }

        @Override
        public void run() {
            Message message1 = mHandler.obtainMessage(mTaskId, Thread.currentThread().getName());
            mHandler.sendMessage(message1);

            Log.e("TaskId：" + mTaskId, Thread.currentThread().getName());
            int process = 0;
            while (process < 100) {
                process += 1;
                Message message = mHandler.obtainMessage(mTaskId, process, 0);
                mHandler.sendMessage(message);
                try {
                    switch (mTaskId) {
                        case 0:
                            Thread.sleep(20);
                            break;
                        case 1:
                            Thread.sleep(70);
                            break;
                        case 2:
                            Thread.sleep(50);
                            break;
                        case 3:
                            Thread.sleep(10);
                            break;
                        case 4:
                            Thread.sleep(110);
                            break;
                        case 5:
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
