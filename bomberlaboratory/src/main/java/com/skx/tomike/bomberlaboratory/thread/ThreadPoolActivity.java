package com.skx.tomike.bomberlaboratory.thread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.skx.tomike.bomberlaboratory.R;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 描述 : 线程池
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019-12-19 17:03
 */
public class ThreadPoolActivity extends SkxBaseActivity<BaseViewModel> {

    private TextView mTvLogcat;

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
        return new TitleConfig.Builder().setTitleText("线程池").create();
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
        mTvLogcat = findViewById(R.id.tv_threadPool_logcat);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void sendMessageToLogcat(@NonNull String msg) {
        Message startMsg = mHandler.obtainMessage(1);
        startMsg.obj = msg;
        mHandler.sendMessage(startMsg);
    }
}
