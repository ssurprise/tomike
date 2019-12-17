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


    private VipThread vipThread = new VipThread("vip");
    private ClientThread client1Thread = new ClientThread("client-1");
    private ClientThread client2Thread = new ClientThread("client-2");
    private ClientThread client3Thread = new ClientThread("client-3");

    private final Queue<Integer> mClientArray = new LinkedList<>();
    private AtomicInteger mAtomicInteger = new AtomicInteger();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mTvLogcat.append("\n 请 " + msg.arg1 + " 到vip 柜台办理");
                    break;
                case 1:
                    mTvLogcat.append("\n 请 " + msg.arg1 + " 到柜台1 办理");
                    break;
                case 2:
                    mTvLogcat.append("\n 请  " + msg.arg1 + " 到柜台2 办理");
                    break;
                case 3:
                    mTvLogcat.append("\n 请  " + msg.arg1 + " 到柜台3 办理");
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);
        initView();
        init();
    }

    private void init() {

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
            vipThread.setClient(mClientArray.peek());
            vipThread.start();

        } else if (v.getId() == R.id.btn_thread_1) {
            client1Thread.setClient(mClientArray.peek());
            client1Thread.start();

        } else if (v.getId() == R.id.btn_thread_2) {
            client2Thread.setClient(mClientArray.peek());
            client2Thread.start();

        } else if (v.getId() == R.id.btn_thread_3) {
            client3Thread.setClient(mClientArray.peek());
            client3Thread.start();

        } else if (v.getId() == R.id.btn_add_people) {
            int i = mAtomicInteger.addAndGet(1);
            mClientArray.offer(i);
        }
    }

    class VipThread extends Thread {

        private Integer client;

        public VipThread(String name) {
            super(name);
        }

        public void setClient(Integer client) {
            this.client = client;
        }

        @Override
        public void run() {
            super.run();
            if (client < 0) {
                return;
            }
            Message message = mHandler.obtainMessage(0);
            message.arg1 = client;
            mHandler.sendMessage(message);
        }
    }

    class ClientThread extends Thread {

        private Integer client;

        public void setClient(Integer client) {
            this.client = client;
            mClientArray.poll();
        }

        public ClientThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            super.run();
            if (client < 0) {
                return;
            }
            Log.e("thread:", Thread.currentThread().getName());
            Message message = mHandler.obtainMessage();

            switch (getName()) {
                case "client-1":
                    message.what = 1;
                    break;
                case "client-2":
                    message.what = 2;
                    break;
                case "client-3":
                    message.what = 3;
                    break;
            }
            message.arg1 = client;
            mHandler.sendMessage(message);
        }
    }
}
