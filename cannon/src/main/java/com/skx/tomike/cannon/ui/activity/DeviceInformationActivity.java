package com.skx.tomike.cannon.ui.activity;

import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_DEVICE_INFO;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Environment;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.permission.PermissionController;
import com.skx.common.permission.PermissionResultListener;
import com.skx.common.utils.DevicesUtils;
import com.skx.common.utils.MemoryUtils;
import com.skx.common.utils.StorageUtils;
import com.skx.tomike.cannon.R;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * 描述 : 设备信息
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/8/5 4:44 PM
 */
@Route(path = ROUTE_PATH_DEVICE_INFO)
public class DeviceInformationActivity extends SkxBaseActivity<BaseViewModel<?>> {

    private TextView mTvAndroidId;
    private TextView mTvDeviceId;
    private TextView mTvSn;
    private TextView mTvImei;
    private TextView mTvMeid;

    @Override
    protected void initParams() {
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_device_information;
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("设备信息").create();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        TextView tvBrand = findViewById(R.id.tv_deviceInfo_brand);
        TextView tvModel = findViewById(R.id.tv_deviceInfo_model);
        TextView tvManufacturer = findViewById(R.id.tv_deviceInfo_manufacturer);
        TextView tvSystemVersion = findViewById(R.id.tv_deviceInfo_systemVersion);
        TextView tvABI = findViewById(R.id.tv_deviceInfo_abi);

        tvBrand.setText(String.format("手机品牌：%s", DevicesUtils.getBrand()));
        tvModel.setText(String.format("手机型号：%s", DevicesUtils.getModel()));
        tvManufacturer.setText(String.format("设备制造商：%s", DevicesUtils.getManufacturer()));
        tvSystemVersion.setText(String.format("Android版本：%s", Build.VERSION.RELEASE));
        String[] supportedAbis = Build.SUPPORTED_ABIS;
        StringBuilder abi = new StringBuilder();
        for (String supportedAbi : supportedAbis) {
            abi.append(supportedAbi).append(" ");
        }
        tvABI.setText(String.format("ABI信息：%s", abi.toString()));

        mTvAndroidId = findViewById(R.id.tv_deviceInfo_androidId);
        mTvDeviceId = findViewById(R.id.tv_deviceInfo_deviceId);
        mTvSn = findViewById(R.id.tv_deviceInfo_sn);
        mTvImei = findViewById(R.id.tv_deviceInfo_imei);
        mTvMeid = findViewById(R.id.tv_deviceInfo_meid);

        requestPermission();
        setResolutionInfo();

        setStorage();
        setMemory();

        setAppInfo();
    }

    private void requestPermission() {
        new PermissionController.Builder(this)
                .permission(Manifest.permission.READ_PHONE_STATE)
                .callback(new PermissionResultListener() {
                    @Override
                    public void onSucceed(@Nullable List<String> grantPermissions) {
                        renderView();
                    }

                    @Override
                    public void onFailed(@Nullable List<String> deniedPermissions) {
                        renderView();
                    }
                })
                .request();
    }

    @SuppressLint("SetTextI18n")
    private void setStorage() {
        TextView tvExternal = findViewById(R.id.tv_deviceInfo_external);
        tvExternal.setText(String.format("SD卡根目录：%s", Environment.getExternalStorageDirectory().getAbsolutePath()));

        TextView tvTotalStorage = findViewById(R.id.tv_deviceInfo_totalStorage);
        TextView tvAvailStorage = findViewById(R.id.tv_deviceInfo_availStorage);

        tvTotalStorage.setText("总存储空间：" + StorageUtils.INSTANCE.getTotalStorageSize(this, "GB") + "GB");
        tvAvailStorage.setText("可用存储空间：" + StorageUtils.INSTANCE.getAvailableStorageSize(this, "GB") + "GB");
    }

    @SuppressLint("SetTextI18n")
    private void setMemory() {
        TextView tvTotalMem = findViewById(R.id.tv_deviceInfo_totalMem);
        TextView tvAvailMem = findViewById(R.id.tv_deviceInfo_availMem);

        tvTotalMem.setText("总内存：" + MemoryUtils.INSTANCE.getTotalMemSize("GB") + "GB");
        tvAvailMem.setText("可用内存：" + MemoryUtils.INSTANCE.getAvailMemory(this, "GB") + "GB");
    }

    /**
     * 分辨率相关
     */
    private void setResolutionInfo() {
        TextView tvScreenWidth = findViewById(R.id.tv_deviceInfo_screen);
        tvScreenWidth.setText(String.format("屏幕宽高(px)：%s * %s",
                DevicesUtils.getScreenWidth(this),
                DevicesUtils.getScreenHeight(this))
        );

        TextView tvDpi = findViewById(R.id.tv_deviceInfo_dpi);
        tvDpi.setText(String.format("dpi：%s", DevicesUtils.getDensityDpi(this)));
    }

    /**
     * app 信息相关
     */
    private void setAppInfo() {
        TextView tvAppDataDir = findViewById(R.id.tv_deviceInfo_appFilesDir);
        tvAppDataDir.setText(String.format("files：%s", getFilesDir().getAbsolutePath()));

        TextView tvAppCacheDir = findViewById(R.id.tv_deviceInfo_appCacheDir);
        tvAppCacheDir.setText(String.format("cache：%s", getCacheDir().getAbsolutePath()));

        TextView tvAppPrivateDir = findViewById(R.id.tv_deviceInfo_appPrivateFiles);
        tvAppPrivateDir.setText(String.format("files：%s", getExternalFilesDir(null).getAbsolutePath()));

        TextView tvAppPrivateCache = findViewById(R.id.tv_deviceInfo_appPrivateCache);
        tvAppPrivateCache.setText(String.format("cache：%s", getExternalCacheDir().getAbsolutePath()));
    }

    private void renderView() {
        try {
            mTvAndroidId.setText(String.format("androidId：%s", DevicesUtils.getAndroidId(this)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mTvDeviceId.setText(String.format("devicedId：%s", DevicesUtils.getDeviceId(this)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            mTvImei.setText(String.format("sn：%s", DevicesUtils.getSerialNo()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            mTvImei.setText(String.format("imei：%s", DevicesUtils.getImei(this)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            mTvMeid.setText(String.format("meid：%s", DevicesUtils.getMeid(this)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
