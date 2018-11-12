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
import com.skx.tomikecommonlibrary.imageloader.Target;
import com.skx.tomikecommonlibrary.imageloader.TransformStrategy;
import com.skx.tomikecommonlibrary.imageloader.transform.CenterCrop;
import com.skx.tomikecommonlibrary.imageloader.transform.CenterInside;
import com.skx.tomikecommonlibrary.imageloader.transform.CircleCrop;
import com.skx.tomikecommonlibrary.imageloader.transform.RoundedCorners;
import com.skx.tomikecommonlibrary.imageloader.transform.Transformation;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.security.MessageDigest;


/**
 * 作者：shiguotao
 * 日期：2018/10/15 下午3:54
 * 描述：Glide 图片加载封装类
 * <p>
 * 注意点：
 * 1、占位符是在主线程上加载Android资源加载。 我们通常希望占位符小，并且可以通过系统资源缓存轻松缓存；
 * 2、转换只应用于请求的资源，不会应用到任何占位符。如果想对placeholder 进行转换可以自定义视图进行裁剪；
 * 3、
 */
public class GlideLoader implements ILoader {

    private RequestOptions options;
    private Context mContext;
    private Object source;
    private boolean transitionAnim = true;

    private Class<?> transcodeClass;

    public void init(Context context) {
        this.mContext = context;
    }

    @Override
    public <T> void load(T t) {
        source = t;
    }

    @Override
    public void apply(LoadOptions loadOptions) {
        if (loadOptions == null) {
            loadOptions = LoadOptions.getDefaultLoadOptions();
        }
        options = new RequestOptions();

        if (loadOptions.isSetPlaceholder()) {
            if (loadOptions.getPlaceholderResId() > 0) {
                options = options.placeholder(loadOptions.getPlaceholderResId());
            } else if (loadOptions.getPlaceholderDrawable() != null) {
                options = options.placeholder(loadOptions.getPlaceholderDrawable());
            }
        }

        if (loadOptions.getErrorResId() > 0) {
            options = options.error(loadOptions.getErrorResId());
        } else if (loadOptions.getErrorDrawable() != null) {
            options = options.error(loadOptions.getErrorDrawable());
        }

        if (loadOptions.getFallbackResId() > 0) {
            options = options.fallback(loadOptions.getFallbackResId());
        } else if (loadOptions.getFallbackDrawable() != null) {
            options = options.fallback(loadOptions.getFallbackDrawable());
        }
        configTransformSetting(loadOptions.getTransformStrategy(), loadOptions.getTransformations());

        transitionAnim = loadOptions.isTransitionAnim();

        if (loadOptions.isValidOverride()) {
            options = options.override(loadOptions.getTargetWidth(), loadOptions.getTargetHeight());
        }


//        options = options.sizeMultiplier(0.1f);

//        transcodeClass = (Class<E>) loadOptions.getSourceType();

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

    /**
     * 配置转换设置
     *
     * @param transformStrategy 转换策略
     * @param transformations   自定义转换集
     */
    private void configTransformSetting(TransformStrategy transformStrategy, Transformation[] transformations) {
        if (transformStrategy == null && (transformations == null || transformations.length == 0)) {
            options = options.dontTransform();
        } else if (transformStrategy != null) {
            switch (transformStrategy) {
                case FIT_CENTER:
                    options.fitCenter();
                    break;
                case CENTER_CROP:
                    options.centerCrop();
                    break;
                case CENTER_INSIDE:
                    options.centerInside();
                    break;
                case CIRCLE_CROP:
                    options.circleCrop();
                    break;
                default:
                    options.dontTransform();
                    break;
            }
        } else {
            configCustomTransformations(transformations);
        }
    }

    /**
     * 配置自定义的bitmap转换集
     *
     * @param transformations bitmap转化集
     */
    private void configCustomTransformations(Transformation[] transformations) {
        if (transformations == null || transformations.length == 0) {
            options = options.dontTransform();

        } else if (transformations.length == 1) {
            Transformation transformation = transformations[0];
            options = options.transform(generateGlideBitmapTransformation(transformation));
        } else {
            BitmapTransformation[] glideTransforms = new BitmapTransformation[transformations.length];
            for (int i = 0, j = glideTransforms.length; i < j; i++) {
                glideTransforms[i] = generateGlideBitmapTransformation(transformations[i]);
            }
            options = options.transforms(glideTransforms);
        }
    }

    /**
     * 生成 Glide 的Transformation类
     *
     * @param transformation 自定义Bitmap 转换类
     * @return Glide 的Transformation类
     */
    private BitmapTransformation generateGlideBitmapTransformation(final Transformation transformation) {
        if (transformation == null) {
            return null;
        }

        if (transformation instanceof CenterCrop) {
            return new com.bumptech.glide.load.resource.bitmap.CenterCrop();
        } else if (transformation instanceof CenterInside) {
            return new com.bumptech.glide.load.resource.bitmap.CenterInside();
        } else if (transformation instanceof CircleCrop) {
            return new com.bumptech.glide.load.resource.bitmap.CircleCrop();
        } else if (transformation instanceof RoundedCorners) {
            RoundedCorners roundedCorners = (RoundedCorners) transformation;
            return new com.bumptech.glide.load.resource.bitmap.RoundedCorners(roundedCorners.getRoundingRadius());
        } else {
            return new BitmapTransformation() {

                @Override
                protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
                    return transformation.transform(toTransform, outWidth, outHeight);
                }

                @Override
                public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
                    transformation.updateDiskCacheKey(messageDigest);
                }

                @Override
                public boolean equals(Object obj) {
                    return transformation.equals(obj);
                }

                @Override
                public int hashCode() {
                    return transformation.hashCode();
                }
            };
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    public <E, T extends Target<E>> T into(final T target) {
        RequestBuilder<?> drawableRequestBuilder;

        ParameterizedType type = (ParameterizedType) target.getClass().getGenericSuperclass();
        Class<E> z = (Class) type.getActualTypeArguments()[0];

        // 输出类型
        if (z.isAssignableFrom(Bitmap.class)) {
            if (transitionAnim) {
                drawableRequestBuilder = Glide.with(mContext).asBitmap().transition(BitmapTransitionOptions.withCrossFade());
            } else {
                drawableRequestBuilder = Glide.with(mContext).asBitmap();
            }

        } else if (z.isAssignableFrom(File.class)) {
            drawableRequestBuilder = Glide.with(mContext).asFile();

        } else {
            if (transitionAnim) {
                drawableRequestBuilder = Glide.with(mContext).asDrawable().transition(DrawableTransitionOptions.withCrossFade());
            } else {
                drawableRequestBuilder = Glide.with(mContext).asDrawable();
            }
        }

        // 加载类型
        if (source instanceof String) {
            drawableRequestBuilder = drawableRequestBuilder.load((String) source);
        } else if (source instanceof Uri) {
            drawableRequestBuilder = drawableRequestBuilder.load((Uri) source);
        } else if (source instanceof Drawable) {
            drawableRequestBuilder = drawableRequestBuilder.load((Drawable) source);
        } else if (source instanceof Bitmap) {
            drawableRequestBuilder = drawableRequestBuilder.load((Bitmap) source);
        } else if (source instanceof Integer) {
            drawableRequestBuilder = drawableRequestBuilder.load((Integer) source);
        } else if (source instanceof File) {
            drawableRequestBuilder = drawableRequestBuilder.load((File) source);
        } else if (source instanceof byte[]) {
            drawableRequestBuilder = drawableRequestBuilder.load((byte[]) source);
        } else {
            drawableRequestBuilder = drawableRequestBuilder.load(source);
        }

        drawableRequestBuilder.apply(options).listener(requestListener).into(new SimpleTarget() {
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
            public void onResourceReady(@NonNull Object resource, @Nullable Transition transition) {
                target.onResourceReady((E) resource);
            }
        });
        return target;
    }

    @Override
    public <T extends ImageView> void into(T target) {
        RequestBuilder<Bitmap> requestBuilder = Glide.with(mContext).asBitmap().load(source);
        if (transitionAnim) {
            requestBuilder = requestBuilder.transition(BitmapTransitionOptions.withCrossFade());
        }
        requestBuilder.apply(options).listener(requestListener).into(target);
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
}
