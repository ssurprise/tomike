package com.skx.tomike.cannon.ui.activity;

import android.app.AlertDialog;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.utils.ToastTool;
import com.skx.tomike.cannon.R;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_HANDLER;

/**
 * 描述 : Handler demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/30 4:34 PM
 */
@Route(path = ROUTE_PATH_HANDLER)
public class HandlerActivity extends SkxBaseActivity<BaseViewModel> implements View.OnClickListener {

    public TextView mHandlerLogcat;

    private boolean isDelayPost = false;
    private boolean isWorkThread = false;

    public final long DELAY_MILLIS = 1000 * 5;//5s
    private RenderHandler mHandler;

    @Override
    protected void initParams() {
        mHandler = new RenderHandler(this);
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("Handler demo").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_handler;
    }

    @Override
    protected void initView() {
        CheckBox cbDelayPost = findViewById(R.id.cb_handler_isDelay);
        cbDelayPost.setChecked(false);
        cbDelayPost.setOnCheckedChangeListener((buttonView, isChecked) -> isDelayPost = isChecked);

        CheckBox cbWorkThread = findViewById(R.id.cb_handler_isWorkThread);
        cbWorkThread.setChecked(false);
        cbWorkThread.setOnCheckedChangeListener((buttonView, isChecked) -> isWorkThread = isChecked);

        mHandlerLogcat = findViewById(R.id.tv_handler_logcat);
        findViewById(R.id.btn_handler_sendOrPostMessage).setOnClickListener(this);
        findViewById(R.id.btn_handler_sendOrPostRunnable).setOnClickListener(this);
        findViewById(R.id.btn_handler_dialogWorkThread).setOnClickListener(this);
        findViewById(R.id.btn_handler_toastWorkThread).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_handler_sendOrPostMessage) {
            sendMessage();

        } else if (id == R.id.btn_handler_sendOrPostRunnable) {
            sendRunnable();

        } else if (id == R.id.btn_handler_dialogWorkThread) {
            workThreadShowDialog();

        } else if (id == R.id.btn_handler_toastWorkThread) {
            workThreadShowToast();
        }
    }


    private final Runnable runnable = () -> {
        Log.e("Handler", "post -> Runnable -> run()");
        ToastTool.showToast(mActivity, "11111");
    };

    private void sendRunnable() {
        if (isWorkThread) {
            new Thread(() -> {
                // 为了演示api 专门分开写
                if (isDelayPost) {
                    mHandler.postDelayed(runnable, DELAY_MILLIS);

                } else {
                    mHandler.post(runnable);
                }
            }).start();
        } else {
            // 为了演示api 专门分开写
            if (isDelayPost) {
                mHandler.postDelayed(runnable, DELAY_MILLIS);

            } else {
                mHandler.post(runnable);
            }
        }

    }

    private void sendMessage() {
        if (isWorkThread) {
            new Thread(() -> {
                Message message = mHandler.obtainMessage(1);
                message.obj = "工作线程于" + getCurrentTime() + "发送的消息\n";
                // 为了演示api 专门分开写
                if (isDelayPost) {
                    mHandler.sendMessageDelayed(message, DELAY_MILLIS);
                } else {
                    mHandler.sendMessage(message);
                }
            }).start();

        } else {
            Message message = mHandler.obtainMessage(1);
            message.obj = "主线程于" + getCurrentTime() + "发送的消息\n";
            // 为了演示api 专门分开写
            if (isDelayPost) {
                mHandler.sendMessageDelayed(message, DELAY_MILLIS);
            } else {
                mHandler.sendMessage(message);
            }
        }
    }

    private void workThreadShowToast() {
        new Thread(() -> ToastTool.showToast(this.mActivity, "子线程可以弹出toast吗？")).start();
    }

    private void workThreadShowDialog() {
        new Thread(() -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
            builder.setMessage("子线程可以直接弹出 Dialog 吗？");
            builder.setCancelable(false);
            builder.setPositiveButton("关闭", (dialog, which) -> dialog.dismiss());
            builder.show();
        }).start();
        /*
32110-4051/com.skx.tomike E/Handler: This is not main thread, and the caller
        should invoke Looper.prepare()  and Looper.loop()called byandroid.os.Handler.<init>:122
        android.app.Dialog.<init>:179
        android.app.AlertDialog.<init>:205
        android.app.AlertDialog$Builder.create:1112
        android.app.AlertDialog$Builder.show:1137
        com.skx.tomike.cannon.ui.activity.HandlerActivity.lambda$workThreadShowDialog$7$HandlerActivity:155
        com.skx.tomike.cannon.ui.activity.-$$Lambda$HandlerActivity$wdFfvgJiwLNhCjtQZt8h10UYh4M.run:2 java.lang.Thread.run:929

         */
    }

    private String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return format.format(calendar.getTime());
    }

    public static class RenderHandler extends Handler {

        private final WeakReference<HandlerActivity> reference;

        public RenderHandler(HandlerActivity context) {
            reference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("Handler", "handleMessage: " + msg.what);
            if (reference.get() == null) return;
            switch (msg.what) {
                case 1:
                case 2:
                    reference.get().mHandlerLogcat.append(msg.obj.toString());
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * 工作线程，带Handler
     */
    static class LooperThread extends Thread {
        public Handler mHandler;

        public void run() {
            Looper.prepare();

            mHandler = new Handler(Looper.myLooper()) {
                public void handleMessage(Message msg) {
                    // process incoming messages here
                }
            };

            Looper.loop();
        }
    }
}
