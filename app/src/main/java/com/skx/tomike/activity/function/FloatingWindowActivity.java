package com.skx.tomike.activity.function;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxBaseActivity;


public class FloatingWindowActivity extends SkxBaseActivity {

    WindowManager mWindowManager;
    private WindowManager.LayoutParams wmParams = null;
    private ImageView leftbtn = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_window_float);
//        mWindowManager = (WindowManager) this.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
//        //设置LayoutParams(全局变量）相关参数
//        wmParams = new WindowManager.LayoutParams(
//                LayoutParams.WRAP_CONTENT,
//                LayoutParams.WRAP_CONTENT,
//                LayoutParams.TYPE_SYSTEM_ALERT,
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
//                PixelFormat.TRANSLUCENT);
//
//
//        wmParams.x = 0;
//        wmParams.y = 0;
//        //设置悬浮窗口长宽数据
//        wmParams.width = 100;
//        wmParams.height = 100;
//
//        createLeftFloatView();
//        leftbtn.invalidate();
    }

//    @Override
//    public Context createDisplayContext(Display display) {
//        return this;
//    }

    public void startWindowFloat(View view) {

        startWindowFloat();
//        mWindowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
//        //设置LayoutParams(全局变量）相关参数
//        wmParams = new WindowManager.LayoutParams(
//                LayoutParams.WRAP_CONTENT,
//                LayoutParams.WRAP_CONTENT,
//                WindowManager.LayoutParams.TYPE_APPLICATION_PANEL,
//                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
//                PixelFormat.TRANSLUCENT);
//
//
//        wmParams.x = 0;
//        wmParams.y = 100;
//        //设置悬浮窗口长宽数据
//        wmParams.width = 100;
//        wmParams.height = 100;
//
//        createLeftFloatView();
//        leftbtn.invalidate();
    }

    /**
     * 创建左边悬浮按钮
     */
    private void createLeftFloatView() {
        leftbtn = new ImageView(this);
        leftbtn.setImageResource(R.drawable.icon_play_black);
        leftbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,"click",Toast.LENGTH_SHORT).show();
            }
        });
        //调整悬浮窗口
        wmParams.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
        //显示myFloatView图像
        mWindowManager.addView(leftbtn, wmParams);
    }

    /**
     * 初始化windowManager
     */
    private void initWindow() {
//        mWindowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
//        wmParams = getParams(wmParams);//设置好悬浮窗的参数
//        // 悬浮窗默认显示以左上角为起始坐标
//        wmParams.gravity = Gravity.LEFT| Gravity.TOP;
//        //悬浮窗的开始位置，因为设置的是从左上角开始，所以屏幕左上角是x=0;y=0
//        wmParams.x = 50;
//        wmParams.y = 50;
//        //得到容器，通过这个inflater来获得悬浮窗控件
//        inflater = LayoutInflater.from(getApplication());
//        // 获取浮动窗口视图所在布局
//        mlayout = (LinearLayout) inflater.inflate(R.layout.floating_layout, null);
//        // 添加悬浮窗的视图
//        mWindowManager.addView(mlayout, wmParams);
    }

    /**
     * 对windowManager进行设置
     *
     * @param wmParams
     * @return
     */
    public WindowManager.LayoutParams getParams(WindowManager.LayoutParams wmParams) {
        wmParams = new WindowManager.LayoutParams();
        //设置window type 下面变量2002是在屏幕区域显示，2003则可以显示在状态栏之上
        //wmParams.type = LayoutParams.TYPE_PHONE;
        //wmParams.type = LayoutParams.TYPE_SYSTEM_ALERT;
        wmParams.type = LayoutParams.TYPE_SYSTEM_ERROR;
        //设置图片格式，效果为背景透明
        wmParams.format = PixelFormat.RGBA_8888;
        //设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
        //wmParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE;
        //设置可以显示在状态栏上
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR |
                WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;

        //设置悬浮窗口长宽数据
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        return wmParams;
    }
}
