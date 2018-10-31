package com.skx.tomike.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.skx.tomike.R;
import com.skx.tomike.interf.IDataRefresh;

/**
 * @author shiguotao
 *         <p/>
 *         基类 activity
 */
public abstract class SkxBaseActivity extends AppCompatActivity implements IDataRefresh {


    WindowManager mWindowManager;
    private WindowManager.LayoutParams wmParams = null;
    private ImageView leftbtn = null;

    public void startWindowFloat() {
        mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        //设置LayoutParams(全局变量）相关参数
        wmParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                PixelFormat.TRANSLUCENT);

        wmParams.token = getWindow().getDecorView().getWindowToken();



        wmParams.x = 500;
        wmParams.y = 500;
        //设置悬浮窗口长宽数据
        wmParams.width = 100;
        wmParams.height = 100;

        createLeftFloatView();
        leftbtn.invalidate();
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


    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    @Override
    public void initializeData() {

    }

    @Override
    public void initializeView() {

    }

    @Override
    public void installListener() {

    }

    @Override
    public void refreshView() {

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, Bundle options) {
        if (requestCode > 0) {
            intent.putExtra("requestCode", requestCode);
        }
        super.startActivityForResult(intent, requestCode, options);
    }

    @Override
    public void startActivity(Intent intent, Bundle options) {
        super.startActivity(intent, options);
    }

    /**
     * 检查权限
     *
     * @param permissions 待检查的权限数组
     * @return
     */
    public boolean checkSelfPermission(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 为子类提供一个权限请求方法
     * @param requestCode
     * @param permissions
     */
    public void requestPermissions(int requestCode, String[] permissions) {
        ActivityCompat.requestPermissions(this, permissions, requestCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
