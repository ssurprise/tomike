package com.skx.common.permission;

public interface RationaleListener {

    void showRequestPermissionRationale(int requestCode, DialogHandler requestRationaleHandler);

    void showSettingPermissionRationale(int requestCode, DialogHandler settingRationaleHandler);
}
