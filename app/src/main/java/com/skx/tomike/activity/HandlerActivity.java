package com.skx.tomike.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.skx.tomike.R;

public class HandlerActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvLogcat;
    private TextView mBtnSent;
    private TextView mBtnThreadSent;
    private TextView mBtnPost;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    mTvLogcat.append("\n send +1");
                    break;
                case 2:
                    mTvLogcat.append("\n thread send +1");
                    break;
                case 3:
                    break;
            }
        }
    };
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mTvLogcat.append("\n post +1");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        initView();
    }

    private void initView() {
        mTvLogcat = findViewById(R.id.tv_handler_tv);
        mBtnSent = findViewById(R.id.btn_handler_sent);
        mBtnThreadSent = findViewById(R.id.btn_handler_threadSent);
        mBtnPost = findViewById(R.id.btn_handler_post);

        mBtnSent.setOnClickListener(this);
        mBtnThreadSent.setOnClickListener(this);
        mBtnPost.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_handler_sent) {
            mHandler.sendEmptyMessage(1);

        } else if (v.getId() == R.id.btn_handler_threadSent) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mHandler.sendEmptyMessage(2);
                }
            }).start();

        } else if (v.getId() == R.id.btn_handler_post) {
            mHandler.post(runnable);
        }
    }
}
