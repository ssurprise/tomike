package com.skx.tomikecommonlibrary.imageloader;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.skx.tomikecommonlibrary.R;
import com.skx.tomikecommonlibrary.imageloader.transform.Transformation;


/**
 * 作者：shiguotao
 * 日期：2018/10/12 下午6:22
 * 描述：加载可选项
 * <p>
 * 功能点包括：
 * 1.占位图（默认的颜色值时 ：#f5f5f5）
 * 2.错误图
 * 3.备用图
 * 4.转换（元转换、自定义转换）
 * 5.过渡动画
 * <p>
 * 暂时不对外开放的资源：
 * 1.请求超时时间设置，默认阈值是3000ms
 * 2.加载优先级
 * 3.硬盘缓存策略
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
    private int placeholderResId;

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


    private boolean transitionAnim;

    private TransformStrategy transformStrategy;
    private Transformation[] transformations;

    private Class<?> sourceType = Drawable.class;
    /**
     * 设置加载超时时间
     */
    private int timeout = Config.TIMEOUT;
    /**
     * 加载优先级
     */
    @NonNull
    private Priority priority = Priority.NORMAL;
    private DiskCacheStrategy diskCacheStrategy = DiskCacheStrategy.AUTOMATIC;

    /**
     * 获取默认配置的加载配置对象
     * 默认设置为：
     * 1.占位图颜色为"#f5f5f5"；
     * 2.默认开启淡入淡出的过渡动画；
     *
     * @return 默认配置的加载配置对象
     */
    public static LoadOptions getDefaultLoadOptions() {
        LoadOptions options = new LoadOptions();
        options.placeholder(R.color.skx_f5f5f5);
        options.transitionAnim(true);
        return options;
    }

    /**
     * 不设置占位图
     *
     * @return 默认配置的加载配置对象
     */
    public LoadOptions noPlaceholder() {
        setPlaceholder = false;
        this.placeholderDrawable = null;
        this.placeholderResId = 0;
        return this;
    }

    /**
     * 占位符。同步加载，是从主线程上的Android资源加载的。 我们通常希望占位符小，并且可以通过系统资源缓存轻松缓存。
     *
     * @param placeholderDrawable 占位图
     * @return 默认配置的加载配置对象
     */
    public LoadOptions placeholder(@Nullable Drawable placeholderDrawable) {
        if (!setPlaceholder) {
            throw new IllegalStateException("Already explicitly declared as no placeholder.");
        }
        if (placeholderResId != 0) {
            throw new IllegalStateException("Placeholder image already set.");
        }
        this.placeholderDrawable = placeholderDrawable;
        this.setPlaceholder = true;
        return this;
    }

    /**
     * 占位符。同步加载，是从主线程上的Android资源加载的。 我们通常希望占位符小，并且可以通过系统资源缓存轻松缓存。
     *
     * @param placeholderResId 占位图资源
     * @return 默认配置的加载配置对象
     */
    public LoadOptions placeholder(int placeholderResId) {
        if (placeholderResId == 0) {
            throw new IllegalArgumentException("Placeholder image resource invalid.");
        }
        if (placeholderDrawable != null) {
            throw new IllegalStateException("Placeholder image already set.");
        }
        this.placeholderResId = placeholderResId;
        this.setPlaceholder = true;
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

    public LoadOptions transitionAnim(boolean transitionAnim) {
        this.transitionAnim = transitionAnim;
        return this;
    }

    /**
     * 设置变换策略。注意设置了变换策略再添加自定义的变化，会不生效，以后添加的为准。
     *
     * @param transformStrategy 变换策略
     * @return 可选参数对象
     */
    public LoadOptions transformStrategy(TransformStrategy transformStrategy) {
        this.transformStrategy = transformStrategy;
        // 设置变换策略后，清空调设置的自定义变化。
        transformations = null;

        return this;
    }

    /**
     * 设置自定义转换。注意：转换仅应用于请求的资源，而不应用于任何占位符。
     *
     * @param transformation 自定义转换
     * @return 可选参数对象
     */
    public LoadOptions transform(Transformation transformation) {
        if (transformation == null) {
            return this;
        }

        transformations = new Transformation[]{transformation};
        // 设置自定义变换后，清空掉设置的变换策略。
        transformStrategy = null;

        return this;
    }

    /**
     * 设置自定义转换。注意：转换仅应用于请求的资源，而不应用于任何占位符。
     *
     * @param transformation 自定义转换集
     * @return 可选参数对象
     */
    public LoadOptions transform(Transformation... transformation) {
        if (transformation == null || transformation.length == 0) {
            return this;
        }

        transformations = transformation;

        // 设置自定义变换后，清空掉设置的变换策略。
        transformStrategy = null;

        return this;
    }

    /**
     * 移除所有资源应用的转换功能
     *
     * @return 可选参数对象
     */
    public LoadOptions dontTransform() {
        transformStrategy = null;
        transformations = null;
        return this;
    }

    /**
     * @param priority 加载优先级
     * @return 加载参数配置
     * @hide 暂时不对外开放此api，使用默认配置即可
     */
    public LoadOptions priority(@NonNull Priority priority) {
        this.priority = priority;
        return this;
    }

    /**
     * @param diskCacheStrategy 缓存配置
     * @return 加载参数配置
     * @hide 暂时不对外开放此api，使用默认配置即可
     */
    public LoadOptions diskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
        this.diskCacheStrategy = diskCacheStrategy;
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

    public TransformStrategy getTransformStrategy() {
        return transformStrategy;
    }

    public Transformation[] getTransformations() {
        return transformations;
    }

}
