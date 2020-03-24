package com.skx.tomike.bomberlaboratory.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.skx.tomike.bomberlaboratory.R;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;

/**
 * 描述 : 线程中断demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019-12-19 17:03
 */
public class ThreadInterruptActivity extends SkxBaseActivity {

    private final static String TAG = "ThreadInterruptActivity";

    private TextView mTvLogcat;
    private LoopThread mLoopThread;

    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mTvLogcat.append("\n" + msg.obj.toString());
                    break;
                case 1:
                    mTvLogcat.append("\n标记位方式 - > 线程循环结束");
                    break;
                case 2:
                    mTvLogcat.append("\ninterrupt 函数方式 - > 线程循环结束");
                    break;
            }
        }
    };

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("线程中断").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_thread_interrupt;
    }

    @Override
    protected void subscribeEvent() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTvLogcat = findViewById(R.id.tv_threadInterrupt_logcat);
    }

    public void onThreadInterruptFlag(View view) {
        if (mLoopThread != null) {
            mLoopThread.shutdown();
            mHandler.sendEmptyMessage(1);
        }
    }

    public void onThreadInterruptFunction(View view) {
        if (mLoopThread != null) {
            mLoopThread.interrupt();
            mHandler.sendEmptyMessage(2);
        }
    }

    public void onThreadInterruptStart(View view) {
        mLoopThread = new LoopThread();
        mLoopThread.start();
    }

    public void onThreadInterruptReset(View view) {
        if (mLoopThread != null) {
            mLoopThread.shutdown();
            mLoopThread.interrupt();
            mTvLogcat.setText("");
        }
    }

    class LoopThread extends Thread {

        private volatile boolean flag = true;

        @Override
        public void run() {
            int i = 0;
            while (flag) {
                Message msg = mHandler.obtainMessage(0);
                msg.obj = "线程循环" + i;
                mHandler.sendMessage(msg);
                i++;
                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }

        void shutdown() {
            flag = false;
        }
    }
}
