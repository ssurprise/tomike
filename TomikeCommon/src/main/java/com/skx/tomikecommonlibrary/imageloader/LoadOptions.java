package com.skx.tomikecommonlibrary.imageloader;

import android.graphics.drawable.Drawable;
import android.support.annotation.CheckResult;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.util.Util;
import com.skx.tomikecommonlibrary.R;
import com.skx.tomikecommonlibrary.imageloader.transform.TransformStrategy;
import com.skx.tomikecommonlibrary.imageloader.transform.TransformAdapter;


/**
 * 作者：shiguotao
 * 日期：2018/10/12 下午6:22
 * 描述：加载可选项
 * <p>
 * 功能点包括：
 * 1.占位图
 * 2.错误图
 * 3.备用图
 * 4.变换（元转换、自定义转换）
 * 5.过渡动画（默认关闭）
 * 6.指定目标大小
 * 7.内存缓存（默认使用）
 * 8.硬盘缓存策略
 * <p>
 * 暂时不对外开放的资源：
 * 1.请求超时时间设置，默认阈值是3000ms
 * 2.加载优先级
 * <p>
 * 注意：
 * 1.变换功能：变换策略的优先级高于变换集，当添加自定义变换功能时，会自动设置变换策略为{@link TransformStrategy CUSTOMIZATION}
 */
public class LoadOptions implements Cloneable {

    private static final int UNSET = -1;
    private static final int MEMORY_CACHEABLE = 1 << 2;
    private static final int DISK_CACHE_STRATEGY = 1 << 3;
    private static final int PRIORITY = 1 << 4;
    private static final int PLACEHOLDER = 1 << 5;
    private static final int PLACEHOLDER_ID = 1 << 6;
    private static final int ERROR_PLACEHOLDER = 1 << 7;
    private static final int ERROR_ID = 1 << 8;
    private static final int FALLBACK = 1 << 9;
    private static final int FALLBACK_ID = 1 << 10;
    private static final int RESIZE = 1 << 11;
    private static final int TRANSFORMATION = 1 << 12;
    private static final int TIMEOUT = 1 << 13;
    private static final int TRANSITION_ANIM = 1 << 14;
    private static final int ONLY_RETRIEVE_FROM_CACHE = 1 << 15;

    private final TransformAdapter[] EMPTY_TRANSFORM = new TransformAdapter[0];

    private int fields;

    /**
     * 占位图。占位符是当请求正在执行时被展示的 Drawable 。当请求成功完成时，占位符会被请求到的资源替换。
     * 如果被请求的资源是从内存中加载出来的，那么占位符可能根本不会被显示。如果请求失败并且没有设置 error Drawable ，则占位符将被持续展示。
     * 类似地，如果请求的url/model为 null ，并且 error Drawable 和 fallback 都没有设置，那么占位符也会继续显示。
     */
    @Nullable
    private Drawable placeholderDrawable;
    private int placeholderResId;

    /**
     * 错误图。error Drawable 在请求永久性失败时展示。error Drawable 同样也在请求的url/model为 null ，且并没有设置 fallback Drawable 时展示。
     */
    @Nullable
    private Drawable errorDrawable;
    private int errorResId;

    /** 后备图 */
    @Nullable
    private Drawable fallbackDrawable;
    private int fallbackResId;

    /** 用于调整大小的目标图像宽度。*/
    private int targetWidth = UNSET;
    /** 调整大小的目标图像高度。*/
    private int targetHeight = UNSET;

    /** 过渡动画 */
    private boolean transitionAnim = false;

    /** 资源变换策略 */
    private TransformStrategy transformStrategy = TransformStrategy.NONE;
    /** 自定义资源变换集合 */
    private TransformAdapter[] transformAdapters;

    /** 内存缓存 - 目前是用的boolean 表示，当用枚举值不满足需求时改为枚举值，默认为 true，即支持内存缓存 */
    private boolean memoryCacheable = true;
    /** 硬盘缓存策略,默认使用系统的加载策略 */
    private DiskCacheStrategy diskCacheStrategy = DiskCacheStrategy.AUTOMATIC;

    private Class<?> transcodeClass = Drawable.class;
    /** 设置加载超时时间 */
    private int timeout = Config.TIMEOUT;
    /** 加载优先级 */
    @NonNull
    private Priority priority = Priority.NORMAL;

    /**
     * 获取默认配置的加载配置对象
     * 默认设置为：
     * 1.占位图颜色为"#f5f5f5"；
     *
     * @return 默认配置的加载配置对象
     */
    public static LoadOptions getDefaultLoadOptions() {
        LoadOptions options = new LoadOptions();
        options.placeholder(R.color.skx_f5f5f5);
        return options;
    }

    /**
     * 不设置占位图
     *
     * @return 默认配置的加载配置对象
     */
    public LoadOptions noPlaceholder() {
        this.placeholderDrawable = null;
        this.placeholderResId = 0;
        fields &= ~PLACEHOLDER;
        fields &= ~PLACEHOLDER_ID;
        return this;
    }

    /**
     * 占位符。同步加载，是从主线程上的Android资源加载的。 通常希望占位符小，并且可以通过系统资源缓存轻松缓存。
     *
     * @param placeholderDrawable 占位图
     * @return 默认配置的加载配置对象
     */
    public LoadOptions placeholder(@Nullable Drawable placeholderDrawable) {
        if (placeholderDrawable == null) {
            throw new IllegalStateException("Placeholder image resource invalid.");
        }

        this.placeholderDrawable = placeholderDrawable;
        fields |= PLACEHOLDER;

        placeholderResId = 0;
        fields &= ~PLACEHOLDER_ID;

        return this;
    }

    /**
     * 占位符。同步加载，是从主线程上的Android资源加载的。 通常希望占位符小，并且可以通过系统资源缓存轻松缓存。
     *
     * @param placeholderResId 占位图资源
     * @return 默认配置的加载配置对象
     */
    public LoadOptions placeholder(int placeholderResId) {
        if (placeholderResId == 0) {
            throw new IllegalArgumentException("Placeholder image resource invalid.");
        }

        this.placeholderResId = placeholderResId;
        fields |= PLACEHOLDER_ID;

        placeholderDrawable = null;
        fields &= ~PLACEHOLDER;

        return this;
    }

    /**
     * 设置加载错误图，用户资源加载失败后的显示。
     *
     * @param errorDrawable 错误资源drawable
     * @return 可选参数对象
     */
    public LoadOptions error(@Nullable Drawable errorDrawable) {
        if (errorDrawable == null) {
            throw new IllegalStateException("Error image resource invalid.");
        }

        this.errorDrawable = errorDrawable;
        fields |= ERROR_PLACEHOLDER;

        this.errorResId = 0;
        fields &= ~ERROR_ID;

        return this;
    }

    /**
     * 设置加载错误图，用户资源加载失败后的显示。
     *
     * @param errorResId 错误资源id
     * @return 可选参数对象
     */
    public LoadOptions error(int errorResId) {
        if (errorResId == 0) {
            throw new IllegalArgumentException("Error image resource invalid.");
        }

        this.errorResId = errorResId;
        fields |= ERROR_ID;

        this.errorDrawable = null;
        fields &= ~ERROR_PLACEHOLDER;

        return this;
    }

    /**
     * 设置后备资源
     *
     * @param fallbackDrawable 后备资源Drawable
     * @return 可选参数对象
     */
    public LoadOptions fallback(@Nullable Drawable fallbackDrawable) {
        if (errorDrawable == null) {
            throw new IllegalStateException("Fallback image resource invalid.");
        }

        this.fallbackDrawable = fallbackDrawable;
        fields |= FALLBACK;

        fallbackResId = 0;
        fields &= ~FALLBACK_ID;

        return this;
    }

    /**
     * 设置后备资源
     *
     * @param fallbackResId 后备资源id
     * @return 可选参数对象
     */
    public LoadOptions fallback(int fallbackResId) {
        if (fallbackResId == 0) {
            throw new IllegalArgumentException("Fallback image resource invalid.");
        }

        this.fallbackResId = fallbackResId;
        fields |= FALLBACK_ID;

        fallbackDrawable = null;
        fields &= ~FALLBACK;

        return this;
    }

    /**
     * 将图像大小调整为指定大小（以像素为单位）。
     *
     * @param targetWidth  指定宽度
     * @param targetHeight 指定高度
     * @return 可选参数对象
     * @throws IllegalArgumentException 如果取值范围{@code targetWidth < 0 || targetHeight < 0 || targetWidth == 0 && targetHeight == 0}
     */
    public LoadOptions resize(@IntRange(from = 0) int targetWidth, @IntRange(from = 0) int targetHeight) {
        if (targetWidth < 0) {
            throw new IllegalArgumentException("Width must be positive number or 0.");
        }
        if (targetHeight < 0) {
            throw new IllegalArgumentException("Height must be positive number or 0.");
        }
        if (targetWidth == 0 && targetHeight == 0) {
            throw new IllegalArgumentException("At least one dimension has to be positive number.");
        }

        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
        fields |= RESIZE;

        return this;
    }

    /**
     * 是否开启默认的过渡动画
     *
     * @param transitionAnim 是否开启
     * @return 可选参数对象
     */
    public LoadOptions transitionAnim(boolean transitionAnim) {
        this.transitionAnim = transitionAnim;
        fields |= TRANSITION_ANIM;

        return this;
    }

    /**
     * 设置变换策略。注意设置了变换策略再添加自定义的变化，会不生效，以后添加的为准。
     *
     * @param transformStrategy 变换策略
     * @return 可选参数对象
     */
    public LoadOptions transformStrategy(TransformStrategy transformStrategy) {
        this.transformStrategy = transformStrategy != null ? transformStrategy : TransformStrategy.NONE;
        // 设置变换策略后，清空设置的自定义变化。
        this.transformAdapters = EMPTY_TRANSFORM;
        fields |= TRANSFORMATION;

        return this;
    }

    /**
     * 设置自定义转换，如果自定义转换为 null，则不会有任何改变。
     * 注意：转换仅应用于请求的资源，而不应用于任何占位符。
     *
     * @param transformAdapter 自定义变换
     * @return 可选参数对象
     */
    public LoadOptions transform(TransformAdapter transformAdapter) {
        if (transformAdapter == null) {
            return this;
        }
        // 设置自定义变换后，置变换策略为自定义类型。
        this.transformStrategy = TransformStrategy.CUSTOMIZATION;
        this.transformAdapters = new TransformAdapter[]{transformAdapter};
        fields |= TRANSFORMATION;

        return this;
    }

    /**
     * 设置自定义转换，如果自定义转换集为 null 或者为空，则不会有任何改变。
     * 注意：转换仅应用于请求的资源，而不应用于任何占位符。
     *
     * @param transformAdapters 自定义变换集
     * @return 可选参数对象
     */
    public LoadOptions transform(TransformAdapter... transformAdapters) {
        if (transformAdapters == null || transformAdapters.length == 0) {
            return this;
        }

        // 设置自定义变换后，置变换策略为自定义类型。
        this.transformStrategy = TransformStrategy.CUSTOMIZATION;
        this.transformAdapters = transformAdapters;
        fields |= TRANSFORMATION;

        return this;
    }

    /**
     * 移除所有资源应用的变换功能。
     * 置缓存策略为{@link com.skx.tomikecommonlibrary.imageloader.transform.TransformStrategy NONE},
     * 同时清空自定义变换集的所有变换。
     *
     * @return 可选参数对象
     */
    public LoadOptions dontTransform() {
        transformStrategy = TransformStrategy.NONE;
        transformAdapters = EMPTY_TRANSFORM;
        fields &= ~TRANSFORMATION;

        return this;
    }

    /**
     * 设置是否支持内存缓存
     *
     * @param memoryCacheable 是否支持内存缓存
     * @return 可选参数对象
     */
    public LoadOptions memoryCacheable(boolean memoryCacheable) {
        this.memoryCacheable = memoryCacheable;
        fields |= MEMORY_CACHEABLE;

        return this;
    }

    /**
     * 设置硬盘缓存策略。当设置的 diskCacheStrategy 为null 时，默认使用系统缓存，即{@link DiskCacheStrategy#AUTOMATIC}
     *
     * @param diskCacheStrategy 缓存配置
     * @return 加载参数配置
     * @hide 暂时不对外开放此api，使用默认配置即可
     */
    public LoadOptions diskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
        this.diskCacheStrategy = diskCacheStrategy == null ? DiskCacheStrategy.AUTOMATIC : diskCacheStrategy;
        fields |= DISK_CACHE_STRATEGY;

        return this;
    }

    /**
     * 设置加载优先级
     *
     * @param priority 加载优先级
     * @return 可选参数对象
     * @hide 暂时不对外开放此api，使用默认配置即可
     */
    public LoadOptions priority(@NonNull Priority priority) {
        this.priority = priority;
        fields |= PRIORITY;

        return this;
    }

    /**
     * 设置超时时间
     *
     * @param timeout 加载超时时间
     * @return 加载参数配置
     */
    public LoadOptions timeout(int timeout) {
        this.timeout = timeout;
        fields |= TIMEOUT;

        return this;
    }

    @Nullable
    public final Drawable getPlaceholderDrawable() {
        return placeholderDrawable;
    }

    public final int getPlaceholderResId() {
        return placeholderResId;
    }

    @Nullable
    public final Drawable getErrorDrawable() {
        return errorDrawable;
    }

    public final int getErrorResId() {
        return errorResId;
    }

    @Nullable
    public final Drawable getFallbackDrawable() {
        return fallbackDrawable;
    }

    public final int getFallbackResId() {
        return fallbackResId;
    }

    public final boolean isTransitionAnim() {
        return transitionAnim;
    }

    /**
     * @return 加载优先级
     * {@hide} 暂时不对外开放
     */
    @NonNull
    public final Priority getPriority() {
        return priority;
    }

    public final boolean isMemoryCacheable() {
        return memoryCacheable;
    }

    public final DiskCacheStrategy getDiskCacheStrategy() {
        if (diskCacheStrategy == null) {
            diskCacheStrategy = DiskCacheStrategy.AUTOMATIC;
        }
        return diskCacheStrategy;
    }

    public void setTranscodeClass(Class<?> transcodeClass) {
        this.transcodeClass = transcodeClass;
    }

    public final Class<?> getTranscodeClass() {
        return transcodeClass;
    }

    public final TransformStrategy getTransformStrategy() {
        return transformStrategy;
    }

    public final TransformAdapter[] getTransformAdapters() {
        return transformAdapters;
    }

    public final int getTargetWidth() {
        return targetWidth;
    }

    public final int getTargetHeight() {
        return targetHeight;
    }


    /**
     * 是否设置了指定的属性
     *
     * @param flag 属性值
     * @return true:已设置
     */
    private final boolean isSet(int flag) {
        return (this.fields & flag) != 0;
    }

    /**
     * 是否设置了硬盘缓存策略
     *
     * @return true:已设置
     */
    public final boolean isSetDiskCacheStrategy() {
        return isSet(DISK_CACHE_STRATEGY);
    }

    /**
     * 是否设置了内存缓存策略
     *
     * @return true:已设置
     */
    public final boolean isSetMemoryCacheable() {
        return isSet(MEMORY_CACHEABLE);
    }

    /**
     * 是否设置了占位图
     *
     * @return true:已设置
     */
    public final boolean isSetPlaceholder() {
        return isSet(PLACEHOLDER) || isSet(PLACEHOLDER_ID);
    }

    /**
     * 是否设置了错误占位图
     *
     * @return true:已设置
     */
    public final boolean isSetErrorPlaceholder() {
        return isSet(ERROR_PLACEHOLDER) || isSet(ERROR_ID);
    }

    /**
     * 是否设置了后备占位图
     *
     * @return true:已设置
     */
    public final boolean isSetFallback() {
        return isSet(FALLBACK) || isSet(FALLBACK_ID);
    }

    /**
     * 是否设置过变化操作
     *
     * @return true:设置过
     */
    public final boolean isSetTransformation() {
        return isSet(TRANSFORMATION);
    }

    /**
     * 是否设置过调整大小
     *
     * @return true:设置过
     */
    public final boolean isSetResize() {
        return isSet(RESIZE);
    }

    /**
     * 设置的指定目标宽高是否合法有效
     *
     * @return true:合法有效
     */
    public final boolean isValidOverride() {
        return Util.isValidDimensions(targetWidth, targetHeight);
    }

    @SuppressWarnings({
            "unchecked",
            // we don't want to throw to be user friendly
            "PMD.CloneThrowsCloneNotSupportedException"
    })
    @CheckResult
    @Override
    public LoadOptions clone() {
        try {
            LoadOptions result = (LoadOptions) super.clone();
            result.transformAdapters = transformAdapters.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
