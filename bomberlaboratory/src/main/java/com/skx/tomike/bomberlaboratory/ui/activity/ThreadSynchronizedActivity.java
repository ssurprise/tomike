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

import java.util.ArrayList;
import java.util.List;


/**
 * 描述 : 线程同步demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019-12-19 17:03
 */
public class ThreadSynchronizedActivity extends SkxBaseActivity implements View.OnClickListener {

    public final static String TAG = "ThreadSynchronizedActivity";
    private TextView mTvLogcat;

    private Handler mHandler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.obj instanceof String) {
                mTvLogcat.append((String) msg.obj);
                mTvLogcat.append("\n");
            }
        }
    };

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("线程同步").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_thread_synchronized;
    }

    @Override
    protected void subscribeEvent() {
    }

    @Override
    protected void initView() {
        findViewById(R.id.btn_threadSynchronized_objectLock_codeBlockThis).setOnClickListener(this);
        findViewById(R.id.btn_threadSynchronized_objectLock_codeBlockMonitor).setOnClickListener(this);
        findViewById(R.id.btn_threadSynchronized_objectLock_syncMethod).setOnClickListener(this);
        findViewById(R.id.btn_threadSynchronized_classLock_codeBlock).setOnClickListener(this);
        findViewById(R.id.btn_threadSynchronized_classLock_staticSyncMethod).setOnClickListener(this);
        mTvLogcat = findViewById(R.id.tv_threadSynchronized_logcat);
    }

    @Override
    public void onClick(View v) {
        final int vId = v.getId();
        if (vId == R.id.btn_threadSynchronized_objectLock_codeBlockThis) {
            final SynchronizedTest synchronizedTest = new SynchronizedTest(mHandler);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        synchronizedTest.fun1();
                    }

                }
            }, "T1").start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true)
                        synchronizedTest.fun1();

                }
            }, "T2").start();

        } else if (vId == R.id.btn_threadSynchronized_objectLock_codeBlockMonitor) {
            final SynchronizedTest synchronizedTest = new SynchronizedTest(mHandler);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        synchronizedTest.fun2();
                    }
                }
            }, "T1").start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        synchronizedTest.fun2();
                    }
                }
            }, "T2").start();

        } else if (vId == R.id.btn_threadSynchronized_objectLock_syncMethod) {
            final SynchronizedTest synchronizedTest = new SynchronizedTest(mHandler);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true)
                        synchronizedTest.fun3();

                }
            }, "T1").start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true)
                        synchronizedTest.fun3();

                }
            }, "T2").start();

        } else if (vId == R.id.btn_threadSynchronized_classLock_codeBlock) {
            final SynchronizedTest synchronizedTest = new SynchronizedTest(mHandler);
            final SynchronizedTest synchronizedTest2 = new SynchronizedTest(mHandler);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true)
                        synchronizedTest.fun4();

                }
            }, "T1").start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true)
                        synchronizedTest2.fun4();

                }
            }, "T2").start();

        } else if (vId == R.id.btn_threadSynchronized_classLock_staticSyncMethod) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true)
                        SynchronizedTest.fun5();

                }
            }, "T1").start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true)
                        SynchronizedTest.fun5();

                }
            }, "T2").start();
        }
    }


}

class SynchronizedTest {

    private final static String TAG = "SynchronizedTest";

    private static final List<String> mObjectLockList = new ArrayList<>();
    private static final List<String> mClassLockLList = new ArrayList<>();

    private Handler mHandler;
    private final Object LOCK = new Object();
    private int mIndex;

    public SynchronizedTest(Handler mHandler) {
        this.mHandler = mHandler;
    }

    // 同步代码块。实际获取的是当前类的monitor
    public void fun1() {
        synchronized (this) {
            if (mIndex < mObjectLockList.size()) {
                try {
                    Thread.currentThread().sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = mHandler.obtainMessage(1);
                message.obj = Thread.currentThread().getName() + ": " + mObjectLockList.get(mIndex);
                mHandler.sendMessage(message);

                mIndex++;
            }
        }
    }

    // 同步代码块。获取的是LOCK实例的monitor，如果实例相同，那么只有一个线程能执行该块内容
    public void fun2() {
        synchronized (LOCK) {
            if (mIndex < mObjectLockList.size()) {
                try {
                    Thread.currentThread().sleep(600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message message = mHandler.obtainMessage(1);
                message.obj = Thread.currentThread().getName() + ": " + mObjectLockList.get(mIndex);
                mHandler.sendMessage(message);

                mIndex++;
            }
        }
    }

    // 同步方法（非静态）。此时锁的是当前实例的对象，也就是说同一对象争用该锁。
    public synchronized void fun3() {
        // do something
        if (mIndex < mObjectLockList.size()) {
            try {
                Thread.currentThread().sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = mHandler.obtainMessage(1);
            message.obj = Thread.currentThread().getName() + ": " + mObjectLockList.get(mIndex);
            mHandler.sendMessage(message);

            mIndex++;
        }
    }

    public void fun4() {
        // 类锁 - 同步代码块。同一类争用该锁，此时锁的是括号内的class对象
        synchronized (SynchronizedTest.class) {
            if (mIndex < mClassLockLList.size()) {
                try {
                    Thread.currentThread().sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = mHandler.obtainMessage(2);
                message.obj = Thread.currentThread().getName() + ": " + mClassLockLList.get(mIndex);
                mHandler.sendMessage(message);

                mIndex++;
            }
        }
    }

    // 类锁 - 静态同步方法。此时锁的是当前对象的class对象。
    public synchronized static void fun5() {
        // do something
    }


    static {
        mObjectLockList.add("public void fun1() {");
        mObjectLockList.add("    // 对象锁 - 同步代码块。实际获取的是当前类的monitor");
        mObjectLockList.add("    synchronized (this) {");
        mObjectLockList.add("        // do something");
        mObjectLockList.add("");
        mObjectLockList.add("    }");
        mObjectLockList.add("}");
        mObjectLockList.add("");
        mObjectLockList.add("");
        mObjectLockList.add("private final Object LOCK = new Object();");
        mObjectLockList.add("public void fun2() {");
        mObjectLockList.add("    // 对象锁 - 同步代码块。获取的是LOCK实例的monitor，如果实例相同，那么只有一个线程能执行该块内容");
        mObjectLockList.add("    synchronized (LOCK) {");
        mObjectLockList.add("        // do something");
        mObjectLockList.add("    }");
        mObjectLockList.add("}");

        mObjectLockList.add("");
        mObjectLockList.add("");
        mObjectLockList.add("// 对象锁 - 同步方法（非静态）。此时锁的是当前实例的对象，也就是说同一对象争用该锁。");
        mObjectLockList.add("public synchronized void fun3() {");
        mObjectLockList.add("    // do something");
        mObjectLockList.add("}");

        mClassLockLList.add("// 类锁 - 同步代码块。此时锁的是括号内 class对象");
        mClassLockLList.add("public void fun4() {");
        mClassLockLList.add("    synchronized (Test.class) {");
        mClassLockLList.add("        // do something");
        mClassLockLList.add("}");

        mClassLockLList.add("");
        mClassLockLList.add("");
        mClassLockLList.add("// 类锁 - 静态同步方法。此时锁的是当前对象的class对象。");
        mClassLockLList.add("public synchronized static void fun5() {");
        mClassLockLList.add("    // do something");
        mClassLockLList.add("}");
    }
}
