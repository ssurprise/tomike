package com.skx.tomikecommonlibrary.imageloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * 加载接口
 */
public interface ILoader {

    /**
     * 初始化
     *
     * @param context 上下文
     */
    void init(Context context);

    /**
     * 加载资源
     *
     * @param source   资源
     * @param <Source> 资源类型
     */
    <Source> void load(Source source);

    /**
     * 应用可选项
     *
     * @param loadOptions 可选项
     */
    void apply(LoadOptions loadOptions);

    /**
     * 设置资源将要载入的目标
     *
     * @param target 将资源加载到的目标
     */
    <E, T extends Target<E>> T into(T target);

    <T extends ImageView> void into(T target);

    void onlyDownload();

    /**
     * 重启
     */
    void resume();

    /**
     * 暂停正在进行的任何负载，但不清除已完成负载的资源。
     */
    void pause();

}
