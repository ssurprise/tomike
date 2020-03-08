package com.skx.tomike.cannonlaboratory.ui.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.skx.tomike.cannonlaboratory.R;


/**
 * 6.0 权限介绍
 */
public class PermissionIntroActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_intro);
    }

    public void onByPressed(View view) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                /**
                 * 这里权限模式
                 */
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                    // 解释为什么需要定位权限之类的
                } else {
                    // 请求权限处理
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
                /**
                 * 如果要用意图模式的话，就不需要用权限模式了，直接跳转到系统设置页面，
                 * 让用户自己控制权限的授权与否，app只承担了一个引导作用
                 */
            } else {
                // 获得定位信息的code
            }
        } else {
            Toast.makeText(this, "6.0 以下", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                // 如果授权被取消，结果数组是 空的，注意这里是empty ，而不是null
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 已经授权，在这里做你想要做的事
                } else {
                    // 拒绝授权
                }
                return;
            }
            // 其他的权限请求处理
        }
    }
}
