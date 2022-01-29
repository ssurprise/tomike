
package com.skx.common.permission;

import android.content.Context;

public interface DialogHandler {

    /**
     * 执行后续逻辑
     */
    void resume();

    /**
     * 取消后续逻辑
     */
    void cancel();

    /**
     * 获取上下文
     *
     * @return
     */
    Context getContext();

    /**
     * 获取默认样式的dialog
     */
    void showDefaultDialog(String title, String msg);

}
