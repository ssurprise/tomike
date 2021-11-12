package com.skx.tomike.cannon.ui.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.tomike.cannon.R;

import static com.skx.tomike.cannon.RouteConstantsKt.ROUTER_GROUP;
import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_transparent_theme;


@Route(path = ROUTE_PATH_transparent_theme, group = ROUTER_GROUP)
public class TransparentThemeActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_transparent_theme;
    }

    @Override
    protected void initView() {

    }

    /*

    方案一：直接使用透明theme
        1.antivity 配置主题 ： <activity android:theme="@android:style/Theme.Translucent.NoTitleBar"/>
        2.在此activity的 onCreate方法中添加 getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        需要注意的是，配置此主题的activity 必须继承的是 Acitivity，而如果继承的是AppCompatActivity 则会报错。
        java.lang.RuntimeException: Unable to start activity ComponentInfo{com.skx.tomike/com.skx.tomike.cannonlaboratory.ui.activity.TransparentThemeActivity}:
        java.lang.IllegalStateException: You need to use a Theme.AppCompat theme (or descendant) with this activity.

    方案二：自定义theme
    <style name="GlobalCaptchaTheme.Transparent" parent="@style/Theme.AppCompat.Light.NoActionBar">
        <item name="android:windowBackground">@android:color/transparent</item>
        <item name="android:windowIsTranslucent">true</item>
        <item name="android:windowContentOverlay">@null</item>
        <item name="android:activityOpenEnterAnimation">@null</item>
        <item name="android:activityOpenExitAnimation">@null</item>
        <item name="android:activityCloseEnterAnimation">@null</item>
        <item name="android:activityCloseExitAnimation">@null</item>
        <item name="android:windowAnimationStyle">@null</item>
    </style>

    注意：透明主题的使用过程中需要注意 android:screenOrientation 这个属性，不能指定方向，否则在Android 8.0系统上会报错（系统兼容问题）。
    主要是和 android:windowIsTranslucent 这个属性的冲突

    注意：不要使用 android:windowNoDisplay 属性，否则会有如下报错。Activity did not call finish() prior to onResume() completing

     */
}
