package com.skx.tomike.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.skx.tomike.R;

/**
 * 描述 : 守护线程demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019-12-19 17:03
 */
public class ThreadDaemonActivity extends AppCompatActivity {

    private final static String TAG = "ThreadDaemonActivity";

    private CheckBox mCbDaemon;
    private TextView mTvLogcat;
    private Thread mUserThread;

    private volatile boolean isDaemon;

    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTvLogcat.append("\n" + msg.obj.toString());
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_daemon);
        initView();
    }

    private void initView() {
        mCbDaemon = findViewById(R.id.btn_threadDaemon_daemon);
        mTvLogcat = findViewById(R.id.tv_threadDaemon_logcat);

        mCbDaemon.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isDaemon = isChecked;
            }
        });
    }

    public void onThreadDaemonStart(View view) {
        mUserThread = new Thread(new Runnable() {

            @Override
            public void run() {
                Message message = mHandler.obtainMessage(0);
                message.obj = "user 线程 start ";
                mHandler.sendMessage(message);

                // 守护线程
                Thread daemonThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int i = 0;
                        while (i < 20) {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Message message = mHandler.obtainMessage(1);
                            message.obj = "daemon 线程 running - " + i;
                            mHandler.sendMessage(message);
                            i++;
                        }
                    }
                }, "daemon");
                daemonThread.setDaemon(isDaemon);
                Log.e(TAG, "daemon 是否是守护线程：" + isDaemon);
                daemonThread.start();

                // 国王线程
                int i = 0;
                while (i < 5) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Message message_1 = mHandler.obtainMessage(0);
                    message_1.obj = "user线程 running - " + i;
                    mHandler.sendMessage(message_1);
                    i++;
                }
            }
        }, "user");
        mUserThread.start();

        Log.e(TAG, "id:" + mUserThread.getId());
        Log.e(TAG, "name:" + mUserThread.getName());
        Log.e(TAG, "priority:" + mUserThread.getPriority());
        Log.e(TAG, "state:" + mUserThread.getState());
    }

    public void onThreadDaemonReset(View view) {
        mUserThread.interrupt();
        mTvLogcat.setText("");
        Log.e(TAG, mUserThread.getName() + "->" + mUserThread.getState());
    }
}
