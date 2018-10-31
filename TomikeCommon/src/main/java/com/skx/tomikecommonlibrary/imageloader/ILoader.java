package com.skx.tomikecommonlibrary.imageloader;

/**
 * 加载接口
 */
public interface ILoader<E> {

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
    <T extends Target<E>> void into(T target);

    /**
     * 重启
     */
    void resume();

    /**
     * 暂停
     */
    void pause();

    /**
     * 取消
     */
    void cancel();

    /**
     * 释放资源
     */
    void release();
}
