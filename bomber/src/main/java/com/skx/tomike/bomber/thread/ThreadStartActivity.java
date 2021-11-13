package com.skx.tomike.bomber.thread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.utils.ToastTool;
import com.skx.tomike.bomber.R;

import java.util.LinkedList;
import java.util.Locale;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_THREAD_START;

/**
 * 描述 : 多线程demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019-12-19 18:46
 */
@Route(path = ROUTE_PATH_THREAD_START)
public class ThreadStartActivity extends SkxBaseActivity<BaseViewModel> implements View.OnClickListener {

    private ScrollView mSvLogcat;
    private TextView mTvLogcat;
    private TextView mTvPeopleCount;

    // 叫号机
    private int INIT_COUNT = 20;
    private int mIndex = 1;
    private final Queue<Integer> mClientArray = new LinkedList<>();
    private AtomicInteger mAtomicInteger = new AtomicInteger();

    private ClientRunnable mClientRunnable;

    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case -1:
                    ToastTool.showToast(ThreadStartActivity.this, "当前没人啊！");
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
            mSvLogcat.fullScroll(View.FOCUS_DOWN);
        }
    };

    @Override
    protected void initParams() {
        for (int i = 0; i < INIT_COUNT; i++) {
            mClientArray.offer(mAtomicInteger.addAndGet(1));
        }
        updatePeopleCount();
        mClientRunnable = new ClientRunnable();
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("模拟叫号系统 - 多线程").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_thread;
    }

    @Override
    protected void subscribeEvent() {
    }

    @Override
    protected void initView() {
        mSvLogcat = findViewById(R.id.sv_thread_logcat);
        mTvLogcat = findViewById(R.id.tv_thread_logcat);
        mTvPeopleCount = findViewById(R.id.tv_people_count);

        findViewById(R.id.btn_thread_0).setOnClickListener(this);
        findViewById(R.id.btn_thread_1).setOnClickListener(this);
        findViewById(R.id.btn_thread_2).setOnClickListener(this);
        findViewById(R.id.btn_thread_3).setOnClickListener(this);
        findViewById(R.id.btn_add_people).setOnClickListener(this);

        mTvPeopleCount.setText(String.format(Locale.CHINA, "当前等待人数：%d人", mClientArray.size()));
    }

    public void clearClient(View view) {
        mIndex = 1;
        mClientArray.clear();
        mAtomicInteger = new AtomicInteger();
        for (int i = 0; i < INIT_COUNT; i++) {
            mClientArray.offer(mAtomicInteger.addAndGet(1));
        }
        mTvLogcat.setText("");
        updatePeopleCount();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_thread_0) {
//            new ClientThread("vip").start();
            new Thread(mClientRunnable, "vip").start();

        } else if (v.getId() == R.id.btn_thread_1) {
//            new ClientThread("client-1").start();
            new Thread(mClientRunnable, "client-1").start();

        } else if (v.getId() == R.id.btn_thread_2) {
//            new ClientThread("client-2").start();
            new Thread(mClientRunnable, "client-2").start();

        } else if (v.getId() == R.id.btn_thread_3) {
//            new ClientThread("client-3").start();
            new Thread(mClientRunnable, "client-3").start();

        } else if (v.getId() == R.id.btn_add_people) {
            mClientArray.offer(mAtomicInteger.addAndGet(1));
            updatePeopleCount();
        }
    }

    private void updatePeopleCount() {
        if (mTvPeopleCount != null) {
            mTvPeopleCount.setText(String.format(Locale.CHINA, "当前等待人数：%d人", mClientArray.size()));
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
                // 模拟线程执行时间，随机。
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
            }
        }
    }

    class ClientRunnable implements Runnable {

        private Random rand = new Random();

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            Log.e("thread", name + "," + Thread.currentThread().getId());
            while (mIndex <= INIT_COUNT) {
                // 模拟线程执行时间，随机。
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

                Message message = mHandler.obtainMessage();
                message.arg1 = mIndex;
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
                mIndex++;
            }
        }
    }
}
