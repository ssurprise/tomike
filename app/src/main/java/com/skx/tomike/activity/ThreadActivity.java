package com.skx.tomike.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.skx.tomike.R;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvLogcat;

    private Button mBtnThread0;
    private Button mBtnThread1;
    private Button mBtnThread2;
    private Button mBtnThread3;

    private Button mBtnAddPeople;

    private final Queue<Integer> mClientArray = new LinkedList<>();
    private AtomicInteger mAtomicInteger = new AtomicInteger();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mTvLogcat.append("\n 请 xxx 到vip 柜台办理");
                    break;
                case 1:
                    mTvLogcat.append("\n 请 xxx 到柜台1 办理");
                    break;
                case 2:
                    mTvLogcat.append("\n 请 xxx 到柜台2 办理");
                    break;
                case 3:
                    mTvLogcat.append("\n 请 xxx 到柜台3 办理");
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        initView();
    }

    private void initView() {
        mTvLogcat = findViewById(R.id.tv_thread_tv);
        mBtnThread0 = findViewById(R.id.btn_thread_0);
        mBtnThread1 = findViewById(R.id.btn_thread_1);
        mBtnThread2 = findViewById(R.id.btn_thread_2);
        mBtnThread3 = findViewById(R.id.btn_thread_3);
        mBtnAddPeople = findViewById(R.id.btn_add_people);

        mBtnThread0.setOnClickListener(this);
        mBtnThread1.setOnClickListener(this);
        mBtnThread2.setOnClickListener(this);
        mBtnThread3.setOnClickListener(this);
        mBtnAddPeople.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_thread_0) {
            new VipThread().start();

        } else if (v.getId() == R.id.btn_thread_1) {
            new ClientThread().start();

        } else if (v.getId() == R.id.btn_thread_2) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Log.e("thread:", Thread.currentThread().getName());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mHandler.sendEmptyMessage(2);
                }
            };
            new Thread(runnable).start();

        } else if (v.getId() == R.id.btn_thread_3) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mHandler.sendEmptyMessage(3);
                }
            }).start();

        } else if (v.getId() == R.id.btn_add_people) {
            int i = mAtomicInteger.addAndGet(1);
            mClientArray.offer(i);
        }
    }

    class VipThread extends Thread {

        @Override
        public void run() {
            super.run();
            mHandler.sendEmptyMessage(0);
        }
    }

    class ClientThread extends Thread {

        @Override
        public void run() {
            super.run();
            Log.e("thread:", Thread.currentThread().getName());
        }
    }
}
