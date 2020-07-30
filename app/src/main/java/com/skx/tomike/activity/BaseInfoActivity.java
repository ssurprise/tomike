package com.skx.tomike.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.skx.tomike.R;

import static android.Manifest.permission.READ_PHONE_STATE;

public class BaseInfoActivity extends AppCompatActivity {

    private TextView tv_baseInfo_androidId;
    private TextView tv_baseInfo_deviceId;
    private TextView tv_baseInfo_imei;
    private TextView tv_baseInfo_meid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_info);

        tv_baseInfo_androidId = (TextView) findViewById(R.id.tv_baseInfo_androidId);
        tv_baseInfo_deviceId = (TextView) findViewById(R.id.tv_baseInfo_deviceId);
        tv_baseInfo_imei = (TextView) findViewById(R.id.tv_baseInfo_imei);
        tv_baseInfo_meid = (TextView) findViewById(R.id.tv_baseInfo_meid);

        requestPermission();
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {
                    // 解释为什么需要定位权限之类的
                    renderView();
                } else {
                    // 请求权限处理
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
                }
            } else {
                renderView();
            }
        } else {
            renderView();
        }
    }

    @RequiresPermission(READ_PHONE_STATE)
    private void renderView() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        tv_baseInfo_androidId.setText("androidId：" + Settings.System.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
        tv_baseInfo_deviceId.setText("devicedId：" + tm.getDeviceId());
        tv_baseInfo_imei.setText("imei：" + tm.getImei());
        tv_baseInfo_meid.setText("meid：" + tm.getMeid());
    }
}
