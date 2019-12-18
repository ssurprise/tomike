package com.skx.tomike.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomikecommonlibrary.utils.ToastTool;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvLogcat;
    private TextView mTvPeopleCount;

    // 叫号机
    private final Queue<Integer> mClientArray = new LinkedList<>();
    private AtomicInteger mAtomicInteger = new AtomicInteger();

    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case -1:
                    ToastTool.showToast(ThreadActivity.this, "当前没人啊！");
                    break;
                case 0:
                    mTvLogcat.append("\n 请 " + msg.arg1 + " 到vip 柜台办理");
                    break;
                case 1:
                    mTvLogcat.append("\n 请 " + msg.arg1 + " 到柜台1 办理");
                    break;
                case 2:
                    mTvLogcat.append("\n 请 " + msg.arg1 + " 到柜台2 办理");
                    break;
                case 3:
                    mTvLogcat.append("\n 请 " + msg.arg1 + " 到柜台3 办理");
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
        for (int i = 0; i < 20; i++) {
            mClientArray.offer(mAtomicInteger.addAndGet(1));
        }
        updatePeopleCount();
    }

    private void initView() {
        mTvLogcat = findViewById(R.id.tv_thread_tv);
        Button mBtnThread0 = findViewById(R.id.btn_thread_0);
        Button mBtnThread1 = findViewById(R.id.btn_thread_1);
        Button mBtnThread2 = findViewById(R.id.btn_thread_2);
        Button mBtnThread3 = findViewById(R.id.btn_thread_3);

        mTvPeopleCount = findViewById(R.id.tv_people_count);
        Button mBtnAddPeople = findViewById(R.id.btn_add_people);

        mBtnThread0.setOnClickListener(this);
        mBtnThread1.setOnClickListener(this);
        mBtnThread2.setOnClickListener(this);
        mBtnThread3.setOnClickListener(this);
        mBtnAddPeople.setOnClickListener(this);
    }

    public void clearClient(View view) {
        mClientArray.clear();
        for (int i = 0; i < 20; i++) {
            mClientArray.offer(mAtomicInteger.addAndGet(1));
        }
        mAtomicInteger = new AtomicInteger();
        mTvLogcat.setText("");
        updatePeopleCount();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_thread_0) {
            new ClientThread("vip").start();
//            new Thread(new ClientRunnable(), "vip").start();

        } else if (v.getId() == R.id.btn_thread_1) {
            new ClientThread("client-1").start();
//            new Thread(new ClientRunnable(), "client-1").start();

        } else if (v.getId() == R.id.btn_thread_2) {
            new ClientThread("client-2").start();
//            new Thread(new ClientRunnable(), "client-2").start();

        } else if (v.getId() == R.id.btn_thread_3) {
            new ClientThread("client-3").start();
//            new Thread(new ClientRunnable(), "client-3").start();

        } else if (v.getId() == R.id.btn_add_people) {
            mClientArray.offer(mAtomicInteger.addAndGet(1));
            updatePeopleCount();
        }
    }

    private void updatePeopleCount() {
        mTvPeopleCount.setText(String.format(Locale.CHINA, "当前等待人数：%d人", mClientArray.size()));
    }


    class ClientRunnable implements Runnable {

        private Random rand = new Random();

        @Override
        public void run() {
            if (mClientArray.isEmpty()) {
                mHandler.sendEmptyMessage(-1);
                return;
            }

            String name = Thread.currentThread().getName();

            Log.e("thread", name + "," + Thread.currentThread().getId());
            while (!mClientArray.isEmpty()) {
                Message message = mHandler.obtainMessage();
                message.arg1 = mClientArray.peek();
                mClientArray.poll();
                switch (name) {
                    case "vip":
                        message.what = 0;
                        break;
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
                mHandler.sendMessage(message);

                try {
                    int sleepInt;
                    switch (name) {
                        case "vip":
                            sleepInt = 5;
                            break;
                        case "client-1":
                            sleepInt = 4;
                            break;
                        case "client-2":
                        case "client-3":
                            sleepInt = 2;
                            break;
                        default:
                            sleepInt = 3;
                            break;
                    }

                    Thread.sleep(rand.nextInt(sleepInt) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class ClientThread extends Thread {

        private Random rand = new Random(5);

        public ClientThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            super.run();
            if (mClientArray.isEmpty()) {
                mHandler.sendEmptyMessage(-1);
                return;
            }

            String name = Thread.currentThread().getName();
            Log.e("thread", name + "," + Thread.currentThread().getId());
            while (!mClientArray.isEmpty()) {
                Message message = mHandler.obtainMessage();
                message.arg1 = mClientArray.peek();
                mClientArray.poll();
                switch (name) {
                    case "vip":
                        message.what = 0;
                        break;
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
                mHandler.sendMessage(message);

                try {
                    int sleepInt;
                    switch (name) {
                        case "vip":
                            sleepInt = 5;
                            break;
                        case "client-1":
                            sleepInt = 4;
                            break;
                        case "client-2":
                        case "client-3":
                            sleepInt = 2;
                            break;
                        default:
                            sleepInt = 3;
                            break;
                    }

                    Thread.sleep(rand.nextInt(sleepInt) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
