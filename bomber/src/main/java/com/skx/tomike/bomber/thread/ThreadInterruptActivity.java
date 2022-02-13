package com.skx.tomike.bomber.thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.bomber.R;

import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_THREAD_INTERRUPT;

/**
 * 描述 : 线程中断demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019-12-19 17:03
 */
@Route(path = ROUTE_PATH_THREAD_INTERRUPT)
public class ThreadInterruptActivity extends SkxBaseActivity<BaseViewModel> {

    private TextView mTvLogcat;
    private LoopThread mLoopThread;

    private final Handler mHandler = new Handler(Looper.myLooper()) {
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
    protected int layoutId() {
        return R.layout.activity_thread_interrupt;
    }

    @Override
    protected void initView() {
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
