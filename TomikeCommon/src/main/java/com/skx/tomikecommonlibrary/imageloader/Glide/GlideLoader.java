package com.skx.tomikecommonlibrary.imageloader.Glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.skx.tomikecommonlibrary.imageloader.ILoader;
import com.skx.tomikecommonlibrary.imageloader.LoadOptions;
import com.skx.tomikecommonlibrary.imageloader.SGifDrawable;
import com.skx.tomikecommonlibrary.imageloader.target.Target;
import com.skx.tomikecommonlibrary.imageloader.transform.TransformStrategy;
import com.skx.tomikecommonlibrary.imageloader.transform.CenterCrop;
import com.skx.tomikecommonlibrary.imageloader.transform.CenterInside;
import com.skx.tomikecommonlibrary.imageloader.transform.CircleCrop;
import com.skx.tomikecommonlibrary.imageloader.transform.RoundedCorners;
import com.skx.tomikecommonlibrary.imageloader.transform.TransformAdapter;

import java.io.File;
import java.security.MessageDigest;


/**
 * 作者：shiguotao
 * 日期：2018/10/15 下午3:54
 * 描述：Glide 图片加载功能封装类。
 * <p>
 * 注意点：
 * 1、占位符是在主线程上加载Android资源加载。 通常希望占位符小，并且可以通过系统资源缓存轻松缓存。对于目标是ImageView的情况，修改的是src 并不是background，
 * 这会在某些情况下出现问题，解决方案是不适用Glide 的占位图，而是设置ImageView 的background;
 * 2、变换只应用于请求的资源，不会应用到任何占位符。如果想对placeholder 进行转换可以自定义视图进行裁剪；
 * 3、变换功能和过渡动画有冲突，不要一起使用。如果要使用变换功能，请不要使用过渡动画；
 * 4、针对可选配置项中的变换策略和变缓集，变换策略的优先级是高于自定义变换集的，简单来说只有变换策略为{@link TransformStrategy CUSTOMIZATION} 时才会进行自定义变换；
 * 5、暂时不对外开放加载优先级、加载时间的配置
 * 6、默认的策略叫做 AUTOMATIC ，它会尝试对本地和远程图片使用最佳的策略。当你加载远程数据（比如，从URL下载）时，AUTOMATIC 策略仅会存储未被你的加载过程修改过(比如，变换，
 * 裁剪–译者注)的原始数据，因为下载远程数据相比调整磁盘上已经存在的数据要昂贵得多。对于本地数据，AUTOMATIC 策略则会仅存储变换过的缩略图，因为即使你需要再次生成另一个尺寸
 * 或类型的图片，取回原始数据也很容易
 */
public class GlideLoader<TranscodeType> implements ILoader<TranscodeType> {

    private Context mContext;
    private Object mSource;
    private RequestOptions mOptions;
    /**
     * 是否设置过渡动画
     */
    private boolean mTransitionAnim = true;
    /**
     * 转码类型
     */
    private Class<TranscodeType> mTranscodeClass;

    public GlideLoader(Class<TranscodeType> transcodingClass) {
        this.mTranscodeClass = transcodingClass;
    }

    public void init(Context context) {
        this.mContext = context;
    }

    @Override
    public <T> void load(T t) {
        mSource = t;
    }

    /**
     * 应用资源请求可选项配置
     * 完成从{@link LoadOptions loadOptions} 到 {@link RequestOptions}的可选项转换
     * 目前支持的可配置项有：
     * 1.占位图
     * 2.错误占位图
     * 3.后备占位图
     * 4.变换功能
     * 5.目标大小调整
     * 6.内存缓存
     * 7.硬盘缓存策略
     * 8.是否使用过渡
     *
     * @param loadOptions 可选配置
     */
    @Override
    public void apply(LoadOptions loadOptions) {
        if (loadOptions == null) {
            loadOptions = LoadOptions.getDefaultLoadOptions();
        }
        mOptions = new RequestOptions();

        // 占位图设置
        if (loadOptions.isSetPlaceholder()) {
            if (loadOptions.getPlaceholderResId() > 0) {
                mOptions = mOptions.placeholder(loadOptions.getPlaceholderResId());
            } else if (loadOptions.getPlaceholderDrawable() != null) {
                mOptions = mOptions.placeholder(loadOptions.getPlaceholderDrawable());
            }
        }
        // 错误占位图设置
        if (loadOptions.isSetErrorPlaceholder()) {
            if (loadOptions.getErrorResId() > 0) {
                mOptions = mOptions.error(loadOptions.getErrorResId());
            } else if (loadOptions.getErrorDrawable() != null) {
                mOptions = mOptions.error(loadOptions.getErrorDrawable());
            }
        }
        // 后备占位图设置
        if (loadOptions.isSetFallback()) {
            if (loadOptions.getFallbackResId() > 0) {
                mOptions = mOptions.fallback(loadOptions.getFallbackResId());
            } else if (loadOptions.getFallbackDrawable() != null) {
                mOptions = mOptions.fallback(loadOptions.getFallbackDrawable());
            }
        }
        // 变换设置
        if (loadOptions.isSetFallback()) {
            configTransformSetting(loadOptions.getTransformStrategy(), loadOptions.getTransformAdapters());
        }

        // 调整大小设置
        if (loadOptions.isSetResize() && loadOptions.isValidOverride()) {
            mOptions = mOptions.override(loadOptions.getTargetWidth(), loadOptions.getTargetHeight());
        }

        // 是否使用内存缓存设置
        if (loadOptions.isSetMemoryCacheable()) {
            mOptions = mOptions.skipMemoryCache(!loadOptions.isMemoryCacheable());
        }

        // 硬盘缓存策略设置
        if (loadOptions.isSetDiskCacheStrategy()) {
            switch (loadOptions.getDiskCacheStrategy()) {
                case ALL:
                    mOptions.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL);
                    break;
                case NONE:
                    mOptions.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.NONE);
                    break;
                case DATA:
                    mOptions.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.DATA);
                    break;
                case RESOURCE:
                    mOptions.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.RESOURCE);
                    break;
                case AUTOMATIC:
                    mOptions.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.RESOURCE);
                    break;
            }
        }

        // 过渡设置
        mTransitionAnim = loadOptions.isTransitionAnim();


//        暂时不对外开放这个加载优先级配置
//        switch (loadOptions.getPriority()) {
//            case HIGH:
//                options.priority(com.bumptech.glide.Priority.HIGH);
//                break;
//            case LOW:
//                options.priority(com.bumptech.glide.Priority.LOW);
//                break;
//            default:
//                options.priority(com.bumptech.glide.Priority.NORMAL);
//                break;
//        }

    }

    @Override
    public <T extends Target<TranscodeType>> T into(final T target) {
        createGlideRequestBuilder().into(new SimpleTarget<TranscodeType>() {
            @Override
            public void onLoadStarted(@Nullable Drawable placeholder) {
                super.onLoadStarted(placeholder);
                target.onLoadStarted(placeholder);
            }

            @Override
            public void onLoadFailed(@Nullable Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                target.onLoadFailed(errorDrawable);
            }

            @Override
            public void onResourceReady(@NonNull TranscodeType resource, @Nullable Transition<? super TranscodeType> transition) {
                target.onResourceReady(resource);
            }
        });
        return target;
    }

    @Override
    public <T extends ImageView> void into(T target) {
        createGlideRequestBuilder().into(target);
    }

    /**
     * 创建Glide 内供的 RequestBuilder 对象，根据{@link Class<TranscodeType>} 匹配不同的参数化RequestBuilder，以完成加载的动作。
     * 设置请求资源、应用请求配置、添加请求监听、添加过渡动画。
     * <p>
     * 设置请求资源：这里没有统一的使用{@link RequestBuilder#load(java.lang.Object)},之所有做区分是想利用glide 自身为我们做的一些优化配置，减少维护压力。
     * 设置过渡动画：过渡动画只针对转码类型为Drawable和Bitmap 请求有效，而且有不同的加载类型，需要做不同区分。
     *
     * @return RequestBuilder
     */
    @SuppressWarnings("unchecked")
    @NonNull
    private RequestBuilder<TranscodeType> createGlideRequestBuilder() {
        RequestBuilder<?> drawableRequestBuilder;

        // 输出类型，通过转码class 来区分加载的是那种参数化类型的RequestBuilder
        if (mTranscodeClass.isAssignableFrom(Bitmap.class)) {
            if (mTransitionAnim) {
                drawableRequestBuilder = Glide.with(mContext).asBitmap().transition(BitmapTransitionOptions.withCrossFade());
            } else {
                drawableRequestBuilder = Glide.with(mContext).asBitmap();
            }

        } else if (mTranscodeClass.isAssignableFrom(Drawable.class)) {
            if (mTransitionAnim) {
                drawableRequestBuilder = Glide.with(mContext).asDrawable().transition(DrawableTransitionOptions.withCrossFade());
            } else {
                drawableRequestBuilder = Glide.with(mContext).asDrawable();
            }

        } else if (mTranscodeClass.isAssignableFrom(SGifDrawable.class)) {
            drawableRequestBuilder = Glide.with(mContext).asGif();
            // 解决加载gif 格式的资源可能存在的异常问题。
            mOptions = mOptions.diskCacheStrategy(DiskCacheStrategy.DATA);

        } else if (mTranscodeClass.isAssignableFrom(File.class)) {
            drawableRequestBuilder = Glide.with(mContext).asFile();

        } else {
            throw new IllegalArgumentException(
                    "Unhandled class: " + mTranscodeClass + ", try .as*(Class).transcode(ResourceTranscoder)");
        }

        // 加载类型，之所以没有统一使用 object 的加载方法是想保留glide给我们做的一些优化。
        if (mSource instanceof String) {
            drawableRequestBuilder = drawableRequestBuilder.load((String) mSource);
        } else if (mSource instanceof Uri) {
            drawableRequestBuilder = drawableRequestBuilder.load((Uri) mSource);
        } else if (mSource instanceof Drawable) {
            drawableRequestBuilder = drawableRequestBuilder.load((Drawable) mSource);
        } else if (mSource instanceof Bitmap) {
            drawableRequestBuilder = drawableRequestBuilder.load((Bitmap) mSource);
        } else if (mSource instanceof Integer) {
            drawableRequestBuilder = drawableRequestBuilder.load((Integer) mSource);
        } else if (mSource instanceof File) {
            drawableRequestBuilder = drawableRequestBuilder.load((File) mSource);
        } else if (mSource instanceof byte[]) {
            drawableRequestBuilder = drawableRequestBuilder.load((byte[]) mSource);
        } else {
            drawableRequestBuilder = drawableRequestBuilder.load(mSource);
        }
        return drawableRequestBuilder.apply(mOptions).listener(requestListener);
    }

    /**
     * 用于在图像加载时监视请求状态的类。
     */
    private final RequestListener requestListener = new RequestListener() {

        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, com.bumptech.glide.request.target.Target target, boolean isFirstResource) {
            Log.e("fail-source", model != null ? (String) model : "");
            return false;
        }

        @Override
        public boolean onResourceReady(Object resource, Object model, com.bumptech.glide.request.target.Target target, DataSource dataSource, boolean isFirstResource) {
            Log.e("success-source", String.format("来源：%s,%s，是否是第一次加载：%s", dataSource.toString(), model != null ? model.toString() : "", isFirstResource));
            return false;
        }
    };

    @Override
    public void onlyDownload() {
        FutureTarget<File> submit = Glide.with(mContext).download(mSource).submit();
    }

    @Override
    public void resume() {
        Glide.with(mContext).resumeRequests();
    }

    /**
     * 取消正在进行的任何负载，但不清除已完成负载的资源。
     */
    @Override
    public void pause() {
        Glide.with(mContext).pauseRequests();
    }


    /**
     * 配置转换设置。支持自带的变换和自定义变换。
     * 1.当缓存策略为null 时，不执行任何变换；
     * 2.根据变换策略来区分是加载元变换还是自定义变换；
     * 3.自定义变换同样支持元变换，只是做了一层转接，实际上还是走的Glide 提供的元变换。
     *
     * @param transformStrategy 转换策略
     * @param transformAdapters 自定义转换集
     */
    private void configTransformSetting(TransformStrategy transformStrategy, TransformAdapter[] transformAdapters) {
        if (transformStrategy != null) {
            switch (transformStrategy) {
                case NONE:
                    mOptions.dontTransform();
                case FIT_CENTER:
                    mOptions.fitCenter();
                    break;
                case CENTER_CROP:
                    mOptions.centerCrop();
                    break;
                case CENTER_INSIDE:
                    mOptions.centerInside();
                    break;
                case CIRCLE_CROP:
                    mOptions.circleCrop();
                    break;
                case CUSTOMIZATION:
                    configCustomTransformations(transformAdapters);
                    break;
                default:
                    mOptions.dontTransform();
                    break;
            }
        } else {
            mOptions = mOptions.dontTransform();
        }
    }

    /**
     * 配置自定义的bitmap转换集。首先需要将我们自定义的变换适配器转换成Glide 支持的变换对象，然后再使用Glide 的变换。
     * 1.当变换集为null 或者空，不执行任何变换；
     * 2.当变换集只有1个变换时，执行{@link RequestOptions#transform(com.bumptech.glide.load.Transformation)}
     *
     * @param transformAdapters bitmap转化集
     */
    private void configCustomTransformations(TransformAdapter[] transformAdapters) {
        if (transformAdapters == null || transformAdapters.length == 0) {
            mOptions = mOptions.dontTransform();

        } else if (transformAdapters.length == 1) {
            TransformAdapter transformAdapter = transformAdapters[0];
            mOptions = mOptions.transform(generateGlideBitmapTransformation(transformAdapter));
        } else {
            BitmapTransformation[] glideTransforms = new BitmapTransformation[transformAdapters.length];
            for (int i = 0, j = glideTransforms.length; i < j; i++) {
                glideTransforms[i] = generateGlideBitmapTransformation(transformAdapters[i]);
            }
            mOptions = mOptions.transforms(glideTransforms);
        }
    }

    /**
     * 生成 Glide 的Transformation类，如果Glide库有提供元变换，则匹配元变换，否则认为是自定义的变换。
     * 注意：变换的接口的四个方法都是要实现的，详细的影响点参考{@link TransformAdapter}的介绍
     *
     * @param transformAdapter 自定义Bitmap 转换类
     * @return Glide 的Transformation类
     */
    private BitmapTransformation generateGlideBitmapTransformation(final TransformAdapter transformAdapter) {
        if (transformAdapter == null) {
            return null;
        }

        if (transformAdapter instanceof CenterCrop) {
            return new com.bumptech.glide.load.resource.bitmap.CenterCrop();
        } else if (transformAdapter instanceof CenterInside) {
            return new com.bumptech.glide.load.resource.bitmap.CenterInside();
        } else if (transformAdapter instanceof CircleCrop) {
            return new com.bumptech.glide.load.resource.bitmap.CircleCrop();
        } else if (transformAdapter instanceof RoundedCorners) {
            RoundedCorners roundedCorners = (RoundedCorners) transformAdapter;
            return new com.bumptech.glide.load.resource.bitmap.RoundedCorners(roundedCorners.getRoundingRadius());
        } else {
            return new BitmapTransformation() {

                @Override
                protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
                    return transformAdapter.transform(toTransform, outWidth, outHeight);
                }

                @Override
                public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
                    transformAdapter.updateDiskCacheKey(messageDigest);
                }

                @Override
                public boolean equals(Object obj) {
                    return transformAdapter.equals(obj);
                }

                @Override
                public int hashCode() {
                    return transformAdapter.hashCode();
                }
            };
        }
    }
}
