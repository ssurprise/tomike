package com.skx.tomike.cannonlaboratory.ui.activity;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.skx.tomike.cannonlaboratory.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.utils.ToastTool;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * 描述 : Handler demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/30 4:34 PM
 */
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
        cbDelayPost.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isDelayPost = isChecked;
            }
        });

        CheckBox cbWorkThread = findViewById(R.id.cb_handler_isWorkThread);
        cbWorkThread.setChecked(false);
        cbWorkThread.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isWorkThread = isChecked;
            }
        });

        mHandlerLogcat = findViewById(R.id.tv_handler_logcat);
        findViewById(R.id.btn_handler_sendOrPostMessage).setOnClickListener(this);
        findViewById(R.id.btn_handler_sendOrPostRunnable).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_handler_sendOrPostMessage) {
            sendMessage();

        } else if (id == R.id.btn_handler_sendOrPostRunnable) {
            sendRunnable();
        }
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.e("Handler", "post -> Runnable -> run()");
            ToastTool.showToast(mActivity, "11111");
        }
    };

    private void sendRunnable() {
        if (isWorkThread) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 为了演示api 专门分开写
                    if (isDelayPost) {
                        mHandler.postDelayed(runnable, DELAY_MILLIS);

                    } else {
                        mHandler.post(runnable);
                    }
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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message = mHandler.obtainMessage(1);
                    message.obj = "工作线程于" + getCurrentTime() + "发送的消息\n";
                    // 为了演示api 专门分开写
                    if (isDelayPost) {
                        mHandler.sendMessageDelayed(message, DELAY_MILLIS);
                    } else {
                        mHandler.sendMessage(message);
                    }
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

    private String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return format.format(calendar.getTime());
    }

    public static class RenderHandler extends Handler {

        private WeakReference<HandlerActivity> reference;

        RenderHandler(HandlerActivity context) {
            reference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Log.e("Handler", "handleMessage: " + msg.what);
            if (reference == null || reference.get() == null) return;
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
    class LooperThread extends Thread {
        public Handler mHandler;

        public void run() {
            Looper.prepare();

            mHandler = new Handler() {
                public void handleMessage(Message msg) {
                    // process incoming messages here
                }
            };

            Looper.loop();
        }
    }
}
