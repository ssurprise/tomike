package com.skx.tomikecommonlibrary.imageloader;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.skx.tomikecommonlibrary.R;
import com.skx.tomikecommonlibrary.imageloader.transform.Transformation;

import java.util.ArrayList;
import java.util.List;


/**
 * 作者：shiguotao
 * 日期：2018/10/12 下午6:22
 * 描述：加载可选项
 */
public class LoadOptions {

    /**
     * 是否显示占位图，默认为显示。
     */
    private boolean setPlaceholder = true;

    /**
     * 占位图。占位符是当请求正在执行时被展示的 Drawable 。当请求成功完成时，占位符会被请求到的资源替换。
     * 如果被请求的资源是从内存中加载出来的，那么占位符可能根本不会被显示。如果请求失败并且没有设置 error Drawable ，则占位符将被持续展示。
     * 类似地，如果请求的url/model为 null ，并且 error Drawable 和 fallback 都没有设置，那么占位符也会继续显示。
     */
    @Nullable
    private Drawable placeholderDrawable;
    private int placeholderResId = R.color.skx_f5f5f5;

    /**
     * 错误图。error Drawable 在请求永久性失败时展示。error Drawable 同样也在请求的url/model为 null ，且并没有设置 fallback Drawable 时展示。
     */
    @Nullable
    private Drawable errorDrawable;
    private int errorResId;

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

    private List<Transformation> transformations;

    /**
     * 加载优先级
     */
    @NonNull
    private Priority priority = Priority.NORMAL;
    private Class<?> sourceType = Drawable.class;

    private DiskCacheStrategy diskCacheStrategy = DiskCacheStrategy.AUTOMATIC;

    /**
     * 获取默认配置的加载配置对象
     *
     * @return 默认配置的加载配置对象
     */
    public static LoadOptions getDefaultLoadOptions() {
        LoadOptions options = new LoadOptions();
        options.placeholder(R.color.skx_f5f5f5);
        return options;
    }

    public LoadOptions noPlaceholder() {
        if (placeholderResId != 0) {
            throw new IllegalStateException("Placeholder resource already set.");
        }
        if (placeholderDrawable != null) {
            throw new IllegalStateException("Placeholder image already set.");
        }
        setPlaceholder = false;
        return this;
    }

    public LoadOptions placeholder(@Nullable Drawable placeholderDrawable) {
        if (!setPlaceholder) {
            throw new IllegalStateException("Already explicitly declared as no placeholder.");
        }
        if (placeholderResId == 0) {
            throw new IllegalArgumentException("Placeholder image resource invalid.");
        }
        this.placeholderDrawable = placeholderDrawable;
        return this;
    }

    public LoadOptions placeholder(int placeholderResId) {
        this.placeholderResId = placeholderResId;
        return this;
    }

    public LoadOptions error(@Nullable Drawable errorDrawable) {
        this.errorDrawable = errorDrawable;
        return this;
    }

    public LoadOptions error(int errorResId) {
        this.errorResId = errorResId;
        return this;
    }

    public LoadOptions fallback(@Nullable Drawable fallbackDrawable) {
        this.fallbackDrawable = fallbackDrawable;
        return this;
    }

    public LoadOptions fallback(int fallbackResId) {
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

    public LoadOptions transformation(Transformation transformation) {
        if (transformations == null) {
            transformations = new ArrayList<>();
        } else {
            transformations.clear();
        }
        transformations.add(transformation);
        return this;
    }

    public LoadOptions transformation(List<Transformation> transformation) {
        if (transformations == null) {
            transformations = new ArrayList<>();
        } else {
            transformations.clear();
        }
        if (transformation == null || transformation.isEmpty()) {
            return this;
        }
        transformations.addAll(transformation);
        return this;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    @Nullable
    public Drawable getPlaceholderDrawable() {
        return placeholderDrawable;
    }

    public int getPlaceholderResId() {
        return placeholderResId;
    }

    public boolean isSetPlaceholder() {
        return setPlaceholder;
    }

    @Nullable
    public Drawable getErrorDrawable() {
        return errorDrawable;
    }

    public int getErrorResId() {
        return errorResId;
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


    public void setSourceType(Class<?> sourceType) {
        this.sourceType = sourceType;
    }

    public Class<?> getSourceType() {
        return sourceType;
    }

    public List<Transformation> getTransformation() {
        return transformations;
    }

}
