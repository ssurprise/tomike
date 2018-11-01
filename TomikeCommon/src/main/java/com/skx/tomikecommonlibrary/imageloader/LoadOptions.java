package com.skx.tomikecommonlibrary.imageloader;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


/**
 * 作者：shiguotao
 * 日期：2018/10/12 下午6:22
 * 描述：加载可选项
 */
public class LoadOptions {

    /**
     * 占位图。占位符是当请求正在执行时被展示的 Drawable 。当请求成功完成时，占位符会被请求到的资源替换。
     * 如果被请求的资源是从内存中加载出来的，那么占位符可能根本不会被显示。如果请求失败并且没有设置 error Drawable ，则占位符将被持续展示。
     * 类似地，如果请求的url/model为 null ，并且 error Drawable 和 fallback 都没有设置，那么占位符也会继续显示。
     */
    @Nullable
    private Drawable placeholderDrawable;
    private int placeholderResId;
    /**
     * 是否显示占位图，默认为显示。
     */
    private boolean showPlaceholder = true;

    /**
     * 错误图。error Drawable 在请求永久性失败时展示。error Drawable 同样也在请求的url/model为 null ，且并没有设置 fallback Drawable 时展示。
     */
    @Nullable
    private Drawable errorDrawable;
    private int errorResId;
    private boolean showErrorPlaceholder = false;

    /**
     * 后备图。
     */
    @Nullable
    private Drawable fallbackDrawable;
    private int fallbackResId;

    /**
     * 设置加载超时时间
     */
    private int timeout = Config.TIMEOUT;

    private boolean transitionAnim;

    /**
     * 加载优先级
     */
    @NonNull
    private Priority priority = Priority.NORMAL;
    private TargetType targetType = TargetType.DRAWABLE;
    private Class<?> sourceType = Drawable.class;

    private DiskCacheStrategy diskCacheStrategy = DiskCacheStrategy.AUTOMATIC;


    public void showPlaceholder(boolean showPlaceholder) {
        this.showPlaceholder = showPlaceholder;
    }

    public LoadOptions setPlaceholderDrawable(@Nullable Drawable placeholderDrawable) {
        this.placeholderDrawable = placeholderDrawable;
        return this;
    }

    public LoadOptions setPlaceholderResId(int placeholderResId) {
        this.placeholderResId = placeholderResId;
        return this;
    }

    public LoadOptions showErrorPlaceholder(boolean showErrorPlaceholder) {
        this.showErrorPlaceholder = showErrorPlaceholder;
        return this;
    }

    public LoadOptions setErrorDrawable(@Nullable Drawable errorDrawable) {
        this.errorDrawable = errorDrawable;
        return this;
    }

    public LoadOptions setErrorResId(int errorResId) {
        this.errorResId = errorResId;
        return this;
    }

    public LoadOptions setFallbackDrawable(@Nullable Drawable fallbackDrawable) {
        this.fallbackDrawable = fallbackDrawable;
        return this;
    }

    public LoadOptions setFallbackResId(int fallbackResId) {
        this.fallbackResId = fallbackResId;
        return this;
    }

    public LoadOptions setTransitionAnim(boolean transitionAnim) {
        this.transitionAnim = transitionAnim;
        return this;
    }

    /**
     * @param priority 加载优先级
     * @return 加载参数配置
     * @hide 暂时不对外开放此api，使用默认配置即可
     */
    public LoadOptions setPriority(@NonNull Priority priority) {
        this.priority = priority;
        return this;
    }

    /**
     * @param diskCacheStrategy 缓存配置
     * @return 加载参数配置
     * @hide 暂时不对外开放此api，使用默认配置即可
     */
    public LoadOptions setDiskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
        this.diskCacheStrategy = diskCacheStrategy;
        return this;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setTargetType(TargetType targetType) {
        this.targetType = targetType;
    }

    @Nullable
    public Drawable getPlaceholderDrawable() {
        return placeholderDrawable;
    }

    public int getPlaceholderResId() {
        return placeholderResId;
    }

    public boolean isShowPlaceholder() {
        return showPlaceholder;
    }

    @Nullable
    public Drawable getErrorDrawable() {
        return errorDrawable;
    }

    public int getErrorResId() {
        return errorResId;
    }


    public boolean isShowErrorPlaceholder() {
        return showErrorPlaceholder;
    }

    @Nullable
    public Drawable getFallbackDrawable() {
        return fallbackDrawable;
    }

    public int getFallbackResId() {
        return fallbackResId;
    }

    public boolean isTransitionAnim() {
        return transitionAnim;
    }

    /**
     * @return 加载优先级
     * {@hide} 暂时不对外开放
     */
    @NonNull
    public Priority getPriority() {
        return priority;
    }

    public DiskCacheStrategy getDiskCacheStrategy() {
        return diskCacheStrategy;
    }

    public int getTimeout() {
        return timeout;
    }

    public TargetType getTargetType() {
        return targetType;
    }

    public void setSourceType(Class<?> sourceType) {
        this.sourceType = sourceType;
    }

    public Class<?> getSourceType() {
        return sourceType;
    }
}
