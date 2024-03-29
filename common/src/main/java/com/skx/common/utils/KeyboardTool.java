package com.skx.common.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by shiguotao on 2016/7/19.
 */
public class KeyboardTool {

    @SuppressLint("StaticFieldLeak")
    private static KeyboardTool keyboardManager;
    private final InputMethodManager imm;

    private KeyboardTool(Context context) {
        // 得到InputMethodManager的实例
        imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public static KeyboardTool getInstances(Context context) {
        if (keyboardManager == null) {
            keyboardManager = new KeyboardTool(context);
        }
        return keyboardManager;
    }

    /**
     * 切换软键盘的显示与隐藏
     */
    public void toggleKeyboard() {
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    /**
     * 判断软键盘是否显示
     *
     * @return 软键盘状态
     */
    public boolean keyboardIsActive() {
        return imm.isActive();
    }

    /**
     * 弹出软键盘
     */
    public void showKeyboard(View view) {
        if (imm.isActive()) {
            return;
        }
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 关闭软键盘
     * 针对于 有一个特定的view(EditText)
     *
     * @param view view
     */
    public void hideKeyboard(View view) {
        if (imm.isActive() && view != null) {
            // 关闭软键盘，开启方法相同，这个方法是切换开启与关闭状态的
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
