package com.skx.tomike.bomber.thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.bomber.R;

import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_THREAD_POOL;

/**
 * 描述 : 线程池
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019-12-19 17:03
 */
@Route(path = ROUTE_PATH_THREAD_POOL)
public class ThreadPoolActivity extends SkxBaseActivity<BaseViewModel> {

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

    private SeekBar seekb_threadPool_process5;
    private TextView tv_threadPool_processText5;
    private TextView tv_threadPool_task5log;

    private SeekBar seekb_threadPool_process6;
    private TextView tv_threadPool_processText6;
    private TextView tv_threadPool_task6log;

    private int corePoolSize = 4;
    private int maxPoolSize = 4;
    private int blockingQueueCapacity = 4;

    private ThreadPoolExecutor mExecutor;

    private final Handler mHandler = new Handler(Looper.myLooper()) {
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
                case 5:
                    if (msg.obj != null) {
                        tv_threadPool_task5log.setText(String.format("执行任务的线程为：%s", msg.obj.toString()));
                    }
                    seekb_threadPool_process5.setProgress(msg.arg1);
                    tv_threadPool_processText5.setText(String.format(Locale.getDefault(), "%d%%", msg.arg1));
                    break;
                case 6:
                    if (msg.obj != null) {
                        tv_threadPool_task6log.setText(String.format("执行任务的线程为：%s", msg.obj.toString()));
                    }
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
        return new TitleConfig.Builder().setTitleText("线程池 ThreadPoolExecutor").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_thread_pool;
    }

    @Override
    protected void subscribeEvent() {
    }

    @Override
    protected void initView() {
        seekb_threadPool_process = findViewById(R.id.seekb_threadPool_process);
        tv_threadPool_processText = findViewById(R.id.tv_threadPool_processText);
        tv_threadPool_log = findViewById(R.id.tv_threadPool_log);

        seekb_threadPool_process1 = findViewById(R.id.seekb_threadPool_process1);
        tv_threadPool_processText1 = findViewById(R.id.tv_threadPool_processText1);
        tv_threadPool_task1log = findViewById(R.id.tv_threadPool_task1log);

        seekb_threadPool_process2 = findViewById(R.id.seekb_threadPool_process2);
        tv_threadPool_processText2 = findViewById(R.id.tv_threadPool_processText2);
        tv_threadPool_task2log = findViewById(R.id.tv_threadPool_task2log);

        seekb_threadPool_process3 = findViewById(R.id.seekb_threadPool_process3);
        tv_threadPool_processText3 = findViewById(R.id.tv_threadPool_processText3);
        tv_threadPool_task3log = findViewById(R.id.tv_threadPool_task3log);

        seekb_threadPool_process5 = findViewById(R.id.seekb_threadPool_process5);
        tv_threadPool_processText5 = findViewById(R.id.tv_threadPool_processText5);
        tv_threadPool_task5log = findViewById(R.id.tv_threadPool_task5log);

        seekb_threadPool_process6 = findViewById(R.id.seekb_threadPool_process6);
        tv_threadPool_processText6 = findViewById(R.id.tv_threadPool_processText6);
        tv_threadPool_task6log = findViewById(R.id.tv_threadPool_task6log);

        EditText et_threadPool_corePoolSize = findViewById(R.id.et_threadPool_corePoolSize);
        et_threadPool_corePoolSize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s.toString())) {
                    s = "0";
                }
                corePoolSize = Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        EditText et_threadPool_maxPoolSize = findViewById(R.id.et_threadPool_maxPoolSize);
        et_threadPool_maxPoolSize.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s.toString())) {
                    s = "0";
                }
                maxPoolSize = Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        EditText et_threadPool_blockingQueueCapacity = findViewById(R.id.et_threadPool_blockingQueueCapacity);
        et_threadPool_blockingQueueCapacity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s.toString())) {
                    s = "0";
                }
                blockingQueueCapacity = Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        et_threadPool_corePoolSize.setText(String.format(Locale.getDefault(), "%d", corePoolSize));
        et_threadPool_maxPoolSize.setText(String.format(Locale.getDefault(), "%d", maxPoolSize));
        et_threadPool_blockingQueueCapacity.setText(String.format(Locale.getDefault(), "%d", blockingQueueCapacity));

        findViewById(R.id.btn_threadPool_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 60,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>(blockingQueueCapacity));
                mExecutor.execute(runnable0);
                mExecutor.execute(runnable1);
                mExecutor.execute(runnable2);
                mExecutor.execute(runnable3);
                mExecutor.execute(runnable5);
                mExecutor.execute(runnable6);
            }
        });
        findViewById(R.id.btn_threadPool_rebuildPool).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, 60,
                        TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<>(blockingQueueCapacity));
            }
        });
        findViewById(R.id.btn_threadPool_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekb_threadPool_process.setProgress(0);
                tv_threadPool_processText.setText("0%");
                tv_threadPool_log.setText("执行任务的线程为：");

                seekb_threadPool_process1.setProgress(0);
                tv_threadPool_processText1.setText("0%");
                tv_threadPool_task1log.setText("执行任务的线程为：");

                seekb_threadPool_process2.setProgress(0);
                tv_threadPool_processText2.setText("0%");
                tv_threadPool_task2log.setText("执行任务的线程为：");

                seekb_threadPool_process3.setProgress(0);
                tv_threadPool_processText3.setText("0%");
                tv_threadPool_task3log.setText("执行任务的线程为：");

                seekb_threadPool_process5.setProgress(0);
                tv_threadPool_processText5.setText("0%");
                tv_threadPool_task5log.setText("执行任务的线程为：");

                seekb_threadPool_process6.setProgress(0);
                tv_threadPool_processText6.setText("0%");
                tv_threadPool_task6log.setText("执行任务的线程为：");
            }
        });
    }

    private final Runnable runnable0 = new PoolRunnable(0);
    private final Runnable runnable1 = new PoolRunnable(1);
    private final Runnable runnable2 = new PoolRunnable(2);
    private final Runnable runnable3 = new PoolRunnable(3);
    private final Runnable runnable5 = new PoolRunnable(5);
    private final Runnable runnable6 = new PoolRunnable(6);

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
