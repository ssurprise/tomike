package com.skx.common.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.IntRange;

public class StatusBarUtils {
    /**
     * 透明状态栏
     *
     * @param activity
     */
    public static void translucentStatusView(Activity activity) {

        Window window = activity.getWindow();
        if (window != null) {
            translucentStatusView(window);
        }
    }

    /**
     * 透明状态栏
     *
     * @param window
     */
    public static void translucentStatusView(Window window) {

        if (window != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {// 5.0+ 透明状态栏实现
                    int flag = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//6.0+ 可以设置字体颜色
                        flag |= View.SYSTEM_UI_FLAG_VISIBLE;
                    }
                    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.getDecorView().setSystemUiVisibility(flag);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(Color.TRANSPARENT);
                } else {
                    window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                }
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {// 4.4-6.0以下由于不可以修改字体颜色，统一加蒙层
                    addTranslucentView(window.getDecorView(), 51);// 20%透明度
                }
                AndroidBug5497Workaround.assistWindow(window);
            }
        }
    }

    /**
     * 获取状态栏高度
     *
     * @param context
     * @return
     */
    public static int getPhoneStatusBarHeight(Context context, int defaultInt) {
        int statusBarHeight = defaultInt;
        try {
            //获取status_bar_height资源的ID
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                //根据资源ID获取响应的尺寸值
                statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 设置默认状态栏样式
     *
     * @param window
     */
    public static void setStatusBarDefaultStyle(Window window) {
        if (window != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                View decorView = window.getDecorView();
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                window.setStatusBarColor(Color.WHITE);
                decorView.setFitsSystemWindows(false);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(Color.parseColor("#CCCCCC"));
            }
        }
    }

    /**
     * 改变状态栏文字颜色
     *
     * @param isBlack true 黑色  false  白色
     */
    public static void changeStatueTextBlack(Window window, boolean isBlack) {
        if (window != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//6.0+ 改变字体颜色
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | (isBlack ? View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR : View.SYSTEM_UI_FLAG_VISIBLE));
            }
        }
    }

    /**
     * 添加半透明矩形条
     *
     * @param parentView
     * @param statusBarAlpha 透明值
     */
    private static void addTranslucentView(View parentView, @IntRange(from = 0, to = 255) int statusBarAlpha) {
        ViewGroup contentView = parentView.findViewById(android.R.id.content);
        View fakeTranslucentView = new View(parentView.getContext());
        fakeTranslucentView.setBackgroundColor(Color.argb(statusBarAlpha, 0, 0, 0));
        contentView.addView(fakeTranslucentView, -1, getPhoneStatusBarHeight(parentView.getContext(), 30));
    }

    /**
     * Android全屏和adjustResize的冲突解决  补丁类
     */
    static class AndroidBug5497Workaround {

        // For more information, see https://code.google.com/p/android/issues/detail?id=5497
        // To use this class, simply invoke assistWindow() on an Activity that already has its content view set.

        public static void assistWindow(Window window) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                new AndroidBug5497Workaround(window);
            }
        }

        private boolean isFirst = true; // 是否为第一次测量
        private int usableHeightPrevious; // 上次测量的可用高度
        private int statusBarHeight; // 状态栏高度

        private View mChildOfContent;// 内容布局
        private FrameLayout.LayoutParams frameLayoutParams;// 内容布局的布局参数

        private AndroidBug5497Workaround(Window window) {
            View decorView = window.getDecorView();
            if (decorView != null) {
                //获取状态栏的高度
                statusBarHeight = getPhoneStatusBarHeight(decorView.getContext(), 0);
                FrameLayout content = decorView.findViewById(android.R.id.content);
                mChildOfContent = content.getChildAt(0);
                if (mChildOfContent != null) {
                    frameLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();
                    //界面出现变动都会调用这个监听事件
                    mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        public void onGlobalLayout() {
                            if (isFirst) {
                                isFirst = false;
                            }
                            possiblyResizeChildOfContent();
                        }
                    });
                }
            }
        }

        //重新调整跟布局的高度 
        private void possiblyResizeChildOfContent() {
            int usableHeightNow = computeUsableHeight();// 可见高度
            //当前可见高度和上一次可见高度不一致 布局变动      
            if (usableHeightNow != usableHeightPrevious) {
                //int usableHeightSansKeyboard2 = mChildOfContent.getHeight();//兼容华为等机型
                int usableHeightSansKeyboard = mChildOfContent.getRootView().getHeight();
                int heightDifference = usableHeightSansKeyboard - usableHeightNow;
                if (heightDifference > (usableHeightSansKeyboard / 4)) {
                    // keyboard probably just became visible 
                    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
                        //frameLayoutParams.height = usableHeightSansKeyboard - heightDifference;                    
                        frameLayoutParams.height = usableHeightSansKeyboard - heightDifference + statusBarHeight;
                    } else {
                        frameLayoutParams.height = usableHeightSansKeyboard - heightDifference;
                    }
                } else {
                    if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
                        frameLayoutParams.height = usableHeightSansKeyboard;
                    } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
                        frameLayoutParams.height = usableHeightNow;
                    }
                }
                mChildOfContent.requestLayout();
                usableHeightPrevious = usableHeightNow;
            }
        }

        /**
         * 计算mChildOfContent可见高度
         * * @return
         */
        private int computeUsableHeight() {
            Rect r = new Rect();
            mChildOfContent.getWindowVisibleDisplayFrame(r);
            //这个判断是为了解决19之后的版本在弹出软键盘时，键盘和推上去的布局（adjustResize）之间有黑色区域的问题
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                return (r.bottom - r.top) + statusBarHeight;
            }
            return (r.bottom - r.top);
        }
    }
}