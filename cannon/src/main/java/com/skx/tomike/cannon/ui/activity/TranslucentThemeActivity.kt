package com.skx.tomike.cannon.ui.activity

import android.graphics.Rect
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.utils.ToastTool
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_TRANSLUCENT_THEME

/**
 * 描述 : 半透明Activity - 新手引导示例，核心技术点和透明Activity一样，均是通过配置theme 实现半透明效果。
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019/2/12 9:08 PM
 */
@Route(path = ROUTE_PATH_TRANSLUCENT_THEME)
class TranslucentThemeActivity : SkxBaseActivity<BaseViewModel<*>>() {

    private val mFirstRect: Rect = Rect(300, 600, 500, 700)
    private val mSecondRect: Rect = Rect(500, 1200, 700, 1300)

    private val mTvFirst: Button by lazy {
        findViewById<Button>(R.id.tv_translucent_first).apply {
            this.setOnClickListener {
                it.visibility = View.INVISIBLE
                mTvSecond.visibility = View.VISIBLE
            }
        }
    }
    private val mTvSecond: Button by lazy {
        findViewById<Button>(R.id.tv_translucent_second).apply {
            this.setOnClickListener {
                it.visibility = View.INVISIBLE
                ToastTool.showToast(mActivity, "好了，新手引导到此结束")
                onBackPressed()
            }
        }
    }

    override fun initParams() {}
    override fun layoutId(): Int {
        return R.layout.activity_theme_translucent
    }

    override fun initView() {
        val lp1 = mTvFirst.layoutParams as FrameLayout.LayoutParams
        lp1.topMargin = mFirstRect.top
        lp1.marginStart = mFirstRect.left
        mTvFirst.layoutParams = lp1

        val lp2 = mTvSecond.layoutParams as FrameLayout.LayoutParams
        lp2.topMargin = mSecondRect.top
        lp2.marginStart = mSecondRect.left
        mTvSecond.layoutParams = lp2
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // 需要设置这个属性，原因：部分设备在theme中设置 windowAnimationStyle=@null 无效，仍然有退出动效，
        // 单独设置退出/关闭动画也无效，还是会有动画在
        overridePendingTransition(R.anim.fast_fade_in, R.anim.fast_fade_out)
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
    注意：虽然设置了退出动效null，但是在部分设备上无效，仍然有退出退出/关闭 效果


    activity 的默认动效有这老多
        <!-- Standard animations for a full-screen window or activity. -->
    <style name="Animation.Activity">
        <item name="activityOpenEnterAnimation">@anim/activity_open_enter</item>
        <item name="activityOpenExitAnimation">@anim/activity_open_exit</item>
        <item name="activityCloseEnterAnimation">@anim/activity_close_enter</item>
        <item name="activityCloseExitAnimation">@anim/activity_close_exit</item>
        <item name="taskOpenEnterAnimation">@anim/task_open_enter</item>
        <item name="taskOpenExitAnimation">@anim/task_open_exit</item>
        <item name="launchTaskBehindTargetAnimation">@anim/launch_task_behind_target</item>
        <item name="launchTaskBehindSourceAnimation">@anim/launch_task_behind_source</item>
        <item name="taskCloseEnterAnimation">@anim/task_close_enter</item>
        <item name="taskCloseExitAnimation">@anim/task_close_exit</item>
        <item name="taskToFrontEnterAnimation">@anim/task_open_enter</item>
        <item name="taskToFrontExitAnimation">@anim/task_open_exit</item>
        <item name="taskToBackEnterAnimation">@anim/task_close_enter</item>
        <item name="taskToBackExitAnimation">@anim/task_close_exit</item>
        <item name="wallpaperOpenEnterAnimation">@anim/wallpaper_open_enter</item>
        <item name="wallpaperOpenExitAnimation">@anim/wallpaper_open_exit</item>
        <item name="wallpaperCloseEnterAnimation">@anim/wallpaper_close_enter</item>
        <item name="wallpaperCloseExitAnimation">@anim/wallpaper_close_exit</item>
        <item name="wallpaperIntraOpenEnterAnimation">@anim/wallpaper_intra_open_enter</item>
        <item name="wallpaperIntraOpenExitAnimation">@anim/wallpaper_intra_open_exit</item>
        <item name="wallpaperIntraCloseEnterAnimation">@anim/wallpaper_intra_close_enter</item>
        <item name="wallpaperIntraCloseExitAnimation">@anim/wallpaper_intra_close_exit</item>
        <item name="fragmentOpenEnterAnimation">@animator/fragment_open_enter</item>
        <item name="fragmentOpenExitAnimation">@animator/fragment_open_exit</item>
        <item name="fragmentCloseEnterAnimation">@animator/fragment_close_enter</item>
        <item name="fragmentCloseExitAnimation">@animator/fragment_close_exit</item>
        <item name="fragmentFadeEnterAnimation">@animator/fragment_fade_enter</item>
        <item name="fragmentFadeExitAnimation">@animator/fragment_fade_exit</item>
    </style>

     */
}