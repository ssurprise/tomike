package com.skx.tomikecommonlibrary.imageloader;

import android.content.Context;
import android.support.annotation.CheckResult;
import android.widget.ImageView;

import com.skx.tomikecommonlibrary.imageloader.target.Target;

/**
 * 作者：shiguotao
 * 日期：2018/11/14 5:03 PM
 * 描述：图片加载接口，提供图片加载最基本的操作，包括初始化、加载资源、应用可选项配置，下载图片，加载的重启和暂停。
 *
 * @param <TranscodeType> 目标转码类型，目前支持的类型包括：Bitmap/Drawable/File
 */
public interface ILoader<TranscodeType> extends SourceType<ILoader<TranscodeType>> {

    /**
     * 初始化
     *
     * @param context 上下文
     */
    ILoader<TranscodeType> init(Context context);

    /**
     * 应用资源请求可选项配置
     *
     * @param loadOptions 可选配置
     */
    @CheckResult
    ILoader<TranscodeType> apply(LoadOptions loadOptions);

    /**
     * 设置资源将要载入的目标
     *
     * @param target 将资源加载到的目标
     * @param <T>    Target或者其子类
     * @return 目标
     */
    <T extends Target<TranscodeType>> T into(T target);

    /**
     * 设置资源将要载入的目标ImageView
     *
     * @param target 目标ImageView
     * @param <T>    ImageView
     */
    <T extends ImageView> void into(T target);

    /**
     * 重启
     */
    void resume();

    /**
     * 暂停正在进行的任何负载，但不清除已完成负载的资源。
     */
    void pause();

}
