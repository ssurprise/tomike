package com.skx.tomike.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomike.data.bo.HomepageNavigationTabBo;
import com.skx.tomike.fragment.business.CatalogFragment;
import com.skx.tomike.fragment.business.HomepageFragment;
import com.skx.tomike.fragment.business.PersonalFragment;
import com.skx.tomike.service.LocalService;
import com.skx.tomike.service.RemoteService;
import com.skx.tomike.util.ToastTool;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends SkxBaseActivity implements OnClickListener {

    private FragmentManager mFragmentManager;
    private FrameLayout fl_content_container;
    private ImageView imv_homepageTab_icon;
    private TextView tv_homepageTab_text;

    private ImageView imv_catalogTab_icon;
    private TextView tv_catalogTab_text;

    private ImageView imv_myTab_icon;
    private TextView tv_myTab_text;

    private List<HomepageNavigationTabBo> tabList;
    private int currentIndex = 0;
    private int lastIndex = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        initializeView();
        refreshView();
        installListener();
    }

    TelephonyManager telephonyManager;

    private void initData() {
        if (tabList == null) {
            tabList = new ArrayList<>();
        } else {
            tabList.clear();
        }


        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);


        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                /**
                 * 这里权限模式
                 */
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {
                    // 解释为什么需要定位权限之类的
                } else {
                    // 请求权限处理
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
                }
                /**
                 * 如果要用意图模式的话，就不需要用权限模式了，直接跳转到系统设置页面，
                 * 让用户自己控制权限的授权与否，app只承担了一个引导作用
                 */
            } else {
                // 获得定位信息的code

                String deviceId = telephonyManager.getDeviceId();
                Log.e("deviceId", deviceId);
            }
        } else {
            ToastTool.showToast(this, "6.0 以下");
        }

        HomepageNavigationTabBo homepageTab = new HomepageNavigationTabBo("首页", android.R.drawable.ic_dialog_map, "");
        HomepageNavigationTabBo catalogTab = new HomepageNavigationTabBo("目录", android.R.drawable.ic_menu_save, "");
        HomepageNavigationTabBo myTab = new HomepageNavigationTabBo("我的", android.R.drawable.ic_menu_manage, "");

        tabList.add(homepageTab);
        tabList.add(catalogTab);
        tabList.add(myTab);
    }

    public static String getDeviceId(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//            return DEFAULT_DEVICE_ID;
//        }
        return md5(telephonyManager.getDeviceId()).substring(0, 16);
    }

    private static final String TAG = HomeActivity.class.getName();

    private static final String DEFAULT_DEVICE_ID = "00000000000000000000000000000000";

    public static String md5(String s) {
        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "no md5 algorithm found.");
        }
        return DEFAULT_DEVICE_ID;
    }


//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        switch (requestCode) {
//            case 1: {
//                // 如果授权被取消，结果数组是 空的，注意这里是empty ，而不是null
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    // 已经授权，在这里做你想要做的事
//                    String deviceId = telephonyManager.getDeviceId();
//                    Log.e("deviceId", deviceId);
//                } else {
//                    // 拒绝授权
//                }
//                return;
//            }
//            // 其他的权限请求处理
//        }
//    }

    @Override
    public void initializeView() {
        setContentView(R.layout.activity_main);
        fl_content_container = (FrameLayout) findViewById(R.id.homepage_content_container);

        imv_homepageTab_icon = (ImageView) findViewById(R.id.homepage_navigation_homepageTab_icon);
        tv_homepageTab_text = (TextView) findViewById(R.id.homepage_navigation_homepageTab_text);

        imv_catalogTab_icon = (ImageView) findViewById(R.id.homepage_navigation_catalogTab_icon);
        tv_catalogTab_text = (TextView) findViewById(R.id.homepage_navigation_catalogTab_text);

        imv_myTab_icon = (ImageView) findViewById(R.id.homepage_navigation_myTab_icon);
        tv_myTab_text = (TextView) findViewById(R.id.homepage_navigation_myTab_text);

//        LinearLayout homepage_navigation_container = (LinearLayout) findViewById(R.id.homepage_navigation_container);
    }

    @Override
    public void refreshView() {
        // 开启双服务保活
        startService(new Intent(this, LocalService.class));
        startService(new Intent(this, RemoteService.class));

        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        HomepageFragment homepageFragment = new HomepageFragment();
        fragmentTransaction.replace(R.id.homepage_content_container, homepageFragment);
        fragmentTransaction.commit();

        setCurrentTabColor();
    }


    @Override
    public void installListener() {
        findViewById(R.id.homepage_navigation_homepageTab).setOnClickListener(this);
        findViewById(R.id.homepage_navigation_catalogTab).setOnClickListener(this);
        findViewById(R.id.homepage_navigation_myTab).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        switch (v.getId()) {
            case R.id.homepage_navigation_homepageTab:
                // 点击后重置currentIndex为当前点击的tab
                currentIndex = 0;
                if (currentIndex != lastIndex) {// 当点击的不是同一个tab的时候，刷新tab，切换到对应的内容页面
                    setCurrentTabColor();
                    setFragmentAnimation(fragmentTransaction);

                    HomepageFragment homepageFragment = new HomepageFragment();
                    fragmentTransaction.replace(R.id.homepage_content_container, homepageFragment);
                    lastIndex = currentIndex;
                }
                break;

            case R.id.homepage_navigation_catalogTab:
                currentIndex = 1;
                if (currentIndex != lastIndex) {
                    setCurrentTabColor();
                    setFragmentAnimation(fragmentTransaction);

                    CatalogFragment catalogFragment = new CatalogFragment();
                    fragmentTransaction.replace(R.id.homepage_content_container, catalogFragment);
                    lastIndex = currentIndex;
                }
                break;

            case R.id.homepage_navigation_myTab:
                currentIndex = 2;
                if (currentIndex != lastIndex) {
                    setCurrentTabColor();
                    setFragmentAnimation(fragmentTransaction);

                    PersonalFragment personalFragment = new PersonalFragment();
                    fragmentTransaction.replace(R.id.homepage_content_container, personalFragment);
                    lastIndex = currentIndex;
                }
                break;
            default:
                break;
        }
        // 事务提交
        fragmentTransaction.commit();
    }

    /**
     * 设置页签的颜色
     */
    private void setCurrentTabColor() {
        for (int i = 0, j = tabList.size(); i < j; i++) {
            if (i == currentIndex) {
                if (i == 0) {
                    tv_homepageTab_text.setTextColor(Color.parseColor("#ff4081"));
                } else if (i == 1) {
                    tv_catalogTab_text.setTextColor(Color.parseColor("#ff4081"));
                } else if (i == 2) {
                    tv_myTab_text.setTextColor(Color.parseColor("#ff4081"));
                }
            } else if (i == lastIndex) {
                if (i == 0) {
                    tv_homepageTab_text.setTextColor(Color.parseColor("#323232"));
                } else if (i == 1) {
                    tv_catalogTab_text.setTextColor(Color.parseColor("#323232"));
                } else if (i == 2) {
                    tv_myTab_text.setTextColor(Color.parseColor("#323232"));
                }
            } else {
            }
        }
    }

    /**
     * 设置Fragment专场动画
     * <p>
     * 自定义转场动画是通过setCustomAnimations()方法，因为Fragment添加时可以指定加入到Back Stack中，
     * 所以转场动画有添加、移除、从Back stack中pop出来，还有进入四种情况。
     * 注意setCustomAnimations()方法必须在add、remove、replace调用之前被设置，否则不起作用。
     *
     * @param fragmentTransaction fragment事务
     */
    public void setFragmentAnimation(FragmentTransaction fragmentTransaction) {
        fragmentTransaction.setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if (lastIndex < currentIndex) {
            fragmentTransaction.setCustomAnimations(R.anim.enter_right_left, R.anim.exit_right_left);
        } else if (lastIndex > currentIndex) {
            fragmentTransaction.setCustomAnimations(R.anim.enter_left_right, R.anim.exit_left_right);
        }
    }
}
