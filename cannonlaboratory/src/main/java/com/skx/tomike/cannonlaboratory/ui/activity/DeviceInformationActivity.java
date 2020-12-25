package com.skx.tomike.cannonlaboratory.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import androidx.annotation.RequiresPermission;
import androidx.core.app.ActivityCompat;

import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.cannonlaboratory.R;

import static android.Manifest.permission.READ_PHONE_STATE;

/**
 * 描述 : 设备信息
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/8/5 4:44 PM
 */
public class DeviceInformationActivity extends SkxBaseActivity<BaseViewModel> {

    private TextView mTvAndroidId;
    private TextView mTvDeviceId;
    private TextView mTvImei;
    private TextView mTvMeid;

    @Override
    protected void initParams() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_device_information;
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("设备/应用信息").create();
    }

    @Override
    protected void initView() {
        TextView tvBrand = findViewById(R.id.tv_deviceInfo_brand);
        TextView tvModel = findViewById(R.id.tv_deviceInfo_model);
        TextView tvSystemVersion = findViewById(R.id.tv_deviceInfo_systemVersion);

        tvBrand.setText(String.format("手机品牌：%s", Build.BRAND));
        tvModel.setText(String.format("手机型号：%s", Build.MODEL));
        tvSystemVersion.setText(String.format("手机Android 版本：%s", Build.VERSION.RELEASE));


        mTvAndroidId = findViewById(R.id.tv_deviceInfo_androidId);
        mTvDeviceId = findViewById(R.id.tv_deviceInfo_deviceId);
        mTvImei = findViewById(R.id.tv_deviceInfo_imei);
        mTvMeid = findViewById(R.id.tv_deviceInfo_meid);
        requestPermission();

        TextView tvScreenWidth = findViewById(R.id.tv_deviceInfo_screenWidth);
        TextView tvScreenHeight = findViewById(R.id.tv_deviceInfo_screenHeight);
        tvScreenWidth.setText(String.format("屏幕宽：%spx", getResources().getDisplayMetrics().widthPixels));
        tvScreenHeight.setText(String.format("屏幕高：%spx", getResources().getDisplayMetrics().heightPixels));


        TextView tvAppName = findViewById(R.id.tv_appInfo_appName);
        TextView tvPackageName = findViewById(R.id.tv_appInfo_packageName);
        TextView tvAppVersionName = findViewById(R.id.tv_appInfo_appVersionName);
        TextView tvAppVersionCode = findViewById(R.id.tv_deviceInfo_appVersionCode);


        //获取包管理器
        PackageManager pm = getPackageManager();
        //获取包信息
        try {
            PackageInfo packageInfo = pm.getPackageInfo(getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            tvAppName.setText(String.format("名称：%s", getResources().getString(labelRes)));
            tvPackageName.setText(String.format("包名：%s", packageInfo.packageName));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                tvAppVersionCode.setText(String.format("versionCode：%s", packageInfo.getLongVersionCode()));
            } else {
                tvAppVersionCode.setText(String.format("versionCode：%s", packageInfo.versionCode));
            }
            tvAppVersionName.setText(String.format("versionName：%s", packageInfo.versionName));

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


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
        mTvAndroidId.setText(String.format("androidId：%s", Settings.System.getString(getContentResolver(), Settings.Secure.ANDROID_ID)));
        mTvDeviceId.setText(String.format("devicedId：%s", tm.getDeviceId()));
        mTvImei.setText(String.format("imei：%s", tm.getImei()));
        mTvMeid.setText(String.format("meid：%s", tm.getMeid()));
    }
}
