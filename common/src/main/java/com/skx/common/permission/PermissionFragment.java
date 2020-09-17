package com.skx.common.permission;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

/**
 * 描述 : 权限
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/9/16 5:21 PM
 */
public class PermissionFragment extends Fragment {

    private static final String KEY_PERMISSION = "request_permissions";
    private static final int PERMISSION_REQUEST_CODE = 2003;

    private Context mContext;
    /**
     * 申请的权限
     */
    private String[] mPermissions;


    public static PermissionFragment getInstance(String[] permissions) {
        PermissionFragment fragment = new PermissionFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArray(KEY_PERMISSION, permissions);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParams();
        checkPermissions();
    }

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT < 23) {
            Toast.makeText(mContext, "6.0 以下", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mPermissions == null || mPermissions.length == 0) return;

        for (String permission : mPermissions) {

        }

        // api 23 及以上，进行权限申请
        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            /**
             * 这里权限模式
             */
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                // 解释为什么需要定位权限之类的
            } else {
                // 请求权限处理
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
            }
            /**
             * 如果要用意图模式的话，就不需要用权限模式了，直接跳转到系统设置页面，
             * 让用户自己控制权限的授权与否，app只承担了一个引导作用
             */
        } else {
            // 获得定位信息的code
        }
    }

    private void initParams() {
        Bundle arguments = getArguments();
        if (arguments != null) {
            if (arguments.containsKey(KEY_PERMISSION)) {
                mPermissions = arguments.getStringArray(KEY_PERMISSION);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {

        }
    }
}
