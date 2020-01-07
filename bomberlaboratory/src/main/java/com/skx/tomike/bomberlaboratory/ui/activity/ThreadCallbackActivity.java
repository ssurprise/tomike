package com.skx.tomike.bomberlaboratory.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.skx.tomike.bomberlaboratory.R;


/**
 * 描述 : 线程返回值demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019-12-19 17:03
 */
public class ThreadCallbackActivity extends AppCompatActivity {

    private final static String TAG = "ThreadCallbackActivity";

    private TextView mTvLogcat;


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
        setContentView(R.layout.activity_thread_callback);
        initView();
    }

    private void initView() {
        mTvLogcat = findViewById(R.id.tv_threadCallback_logcat);
    }

    public void mainThreadWaitFun(View view) {
    }

    public void threadJoinFun(View view) {
    }

    public void onFutureTaskFun(View view) {
    }
}
