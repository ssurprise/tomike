package com.skx.tomike.cannonlaboratory.ui.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.skx.tomike.cannonlaboratory.R;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 描述 : Handler demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/30 4:34 PM
 */
public class HandlerActivity extends SkxBaseActivity<BaseViewModel> implements View.OnClickListener {

    public TextView mHandlerLogcat;

    private boolean isDelayPost = false;
    public final long mDelayMillis = 1000 * 10;//10s
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
        CheckBox cbDelayPost = findViewById(R.id.cb_handler_isDelayPost);
        cbDelayPost.setChecked(false);
        cbDelayPost.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isDelayPost = isChecked;
            }
        });
        mHandlerLogcat = findViewById(R.id.tv_handler_logcat);
        findViewById(R.id.btn_handler_mainThreadPost).setOnClickListener(this);
        findViewById(R.id.btn_handler_workThreadPost).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_handler_mainThreadPost) {
            Message message = mHandler.obtainMessage(0);
            message.obj = "主线程于" + getCurrentTime() + "发送的消息";
            mHandler.sendMessageDelayed(message, isDelayPost ? mDelayMillis : 0);

        } else if (id == R.id.btn_handler_workThreadPost) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Message message = mHandler.obtainMessage(0);
                    message.obj = "工作线程于" + getCurrentTime() + "发送的消息";
                    mHandler.sendMessageDelayed(message, isDelayPost ? mDelayMillis : 0);
                }
            }).start();
        }
    }

    private String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(calendar.getTime());
    }

    public static class RenderHandler extends Handler {

        private WeakReference<HandlerActivity> reference;

        RenderHandler(HandlerActivity context) {
            reference = new WeakReference<>(context);
        }

        @Override
        public void dispatchMessage(Message msg) {
            if (reference == null || reference.get() == null) return;
            super.dispatchMessage(msg);
            reference.get().mHandlerLogcat.append("\n" + msg.obj.toString());
//            switch (msg.what) {
//                case 1:
//                    break;
//                case 2:
//                    reference.get().mHandlerLogcat.append(msg.obj.toString());
//                    break;
//                default:
//                    break;
//            }
        }
    }
}
