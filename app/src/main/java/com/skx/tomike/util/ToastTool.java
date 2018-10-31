package com.skx.tomike.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by shiguotao on 2016/7/19.
 */
public class ToastTool {

    /**
     * 展示提示消息
     *
     * @param context
     * 上下文对象
     * @param tips
     * 要提示的内容
     */
    private static Toast toast;

    /**
     * 停止展示Toast
     */
    public static void cancel(){
        if(toast != null){
            toast.cancel();
        }
    }

    public static void showToast(Context context, String tips) {
        if (toast == null) {
            toast = Toast.makeText(context, tips, Toast.LENGTH_SHORT);
        } else {
            toast.setText(tips);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
