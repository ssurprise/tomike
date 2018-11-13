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
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
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
 * 描述：Glide 图片加载封装类
 * <p>
 * 注意点：
 * 1、占位符是在主线程上加载Android资源加载。 通常希望占位符小，并且可以通过系统资源缓存轻松缓存；
 * 2、转换只应用于请求的资源，不会应用到任何占位符。如果想对placeholder 进行转换可以自定义视图进行裁剪；
 * 3、
 */
public class GlideLoader<TranscodeType> implements ILoader<TranscodeType> {

    private RequestOptions mOptions;
    private Context mContext;
    private Object mSource;
    private boolean transitionAnim = true;

    private Class<TranscodeType> transcodeClass;

    public GlideLoader(Class<TranscodeType> transcodingClass) {
        this.transcodeClass = transcodingClass;
    }

    public void init(Context context) {
        this.mContext = context;
    }

    @Override
    public <T> void load(T t) {
        mSource = t;
    }

    @Override
    public void apply(LoadOptions loadOptions) {
        if (loadOptions == null) {
            loadOptions = LoadOptions.getDefaultLoadOptions();
        }
        mOptions = new RequestOptions();

        if (loadOptions.isSetPlaceholder()) {
            if (loadOptions.getPlaceholderResId() > 0) {
                mOptions = mOptions.placeholder(loadOptions.getPlaceholderResId());
            } else if (loadOptions.getPlaceholderDrawable() != null) {
                mOptions = mOptions.placeholder(loadOptions.getPlaceholderDrawable());
            }
        }

        if (loadOptions.getErrorResId() > 0) {
            mOptions = mOptions.error(loadOptions.getErrorResId());
        } else if (loadOptions.getErrorDrawable() != null) {
            mOptions = mOptions.error(loadOptions.getErrorDrawable());
        }

        if (loadOptions.getFallbackResId() > 0) {
            mOptions = mOptions.fallback(loadOptions.getFallbackResId());
        } else if (loadOptions.getFallbackDrawable() != null) {
            mOptions = mOptions.fallback(loadOptions.getFallbackDrawable());
        }
        configTransformSetting(loadOptions.getTransformStrategy(), loadOptions.getTransformAdapters());

        transitionAnim = loadOptions.isTransitionAnim();

        if (loadOptions.isValidOverride()) {
            mOptions = mOptions.override(loadOptions.getTargetWidth(), loadOptions.getTargetHeight());
        }

//        暂时不对外开放这个缓存配置
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

//        暂时不对外开放这个缓存配置
//        switch (loadOptions.getDiskCacheStrategy()) {
//            case ALL:
//                options.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL);
//                break;
//            case NONE:
//                options.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.NONE);
//                break;
//            case DATA:
//                options.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.DATA);
//                break;
//            case RESOURCE:
//                options.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.RESOURCE);
//                break;
//            case AUTOMATIC:
//                options.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.RESOURCE);
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

    @SuppressWarnings("unchecked")
    @NonNull
    private RequestBuilder<TranscodeType> createGlideRequestBuilder() {
        RequestBuilder<?> drawableRequestBuilder;

        // 输出类型
        if (transcodeClass.isAssignableFrom(Bitmap.class)) {
            if (transitionAnim) {
                drawableRequestBuilder = Glide.with(mContext).asBitmap().transition(BitmapTransitionOptions.withCrossFade());
            } else {
                drawableRequestBuilder = Glide.with(mContext).asBitmap();
            }

        } else if (transcodeClass.isAssignableFrom(Drawable.class)) {
            if (transitionAnim) {
                drawableRequestBuilder = Glide.with(mContext).asDrawable().transition(DrawableTransitionOptions.withCrossFade());
            } else {
                drawableRequestBuilder = Glide.with(mContext).asDrawable();
            }

        } else if (transcodeClass.isAssignableFrom(SGifDrawable.class)) {
            drawableRequestBuilder = Glide.with(mContext).asGif();

        } else if (transcodeClass.isAssignableFrom(File.class)) {
            drawableRequestBuilder = Glide.with(mContext).asFile();

        } else {
            throw new IllegalArgumentException(
                    "Unhandled class: " + transcodeClass + ", try .as*(Class).transcode(ResourceTranscoder)");
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
     * 配置转换设置
     *
     * @param transformStrategy 转换策略
     * @param transformAdapters   自定义转换集
     */
    private void configTransformSetting(TransformStrategy transformStrategy, TransformAdapter[] transformAdapters) {
        if (transformStrategy == null && (transformAdapters == null || transformAdapters.length == 0)) {
            mOptions = mOptions.dontTransform();
        } else if (transformStrategy != null) {
            switch (transformStrategy) {
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
                default:
                    mOptions.dontTransform();
                    break;
            }
        } else {
            configCustomTransformations(transformAdapters);
        }
    }

    /**
     * 配置自定义的bitmap转换集
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
     * 生成 Glide 的Transformation类
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
