package com.skx.tomikecommonlibrary.imageloader;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;


/**
 * 作者：shiguotao
 * 日期：2018/10/15 下午3:54
 * 描述：
 */
class GlideLoader<E> implements ILoader<E> {


    private RequestOptions options;
    private Context mContext;
    private Object source;
    private TargetType targetType = TargetType.DRAWABLE;


    public void init(Context context) {
        this.mContext = context;
    }

    @Override
    public <T> void load(T t) {
        source = t;
    }

    @Override
    public void apply(LoadOptions loadOptions) {
//        if (loadOptions == null) {
//            options = new RequestOptions();
//            return;
//        }
        options = new RequestOptions()
                .fallback(new ColorDrawable(Color.YELLOW))
                .error(new ColorDrawable(Color.RED))
                .placeholder(new ColorDrawable(Color.GREEN))
                .transforms(new CenterCrop(), new RoundedCorners(120))
//                .transform(new BitmapTransformation() {
//                    @Override
//                    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
//                        return null;
//                    }
//
//                    @Override
//                    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
//                    }
//                })
                .timeout(loadOptions.getTimeout())
//                .circleCrop()
//                .sizeMultiplier(0.20f);
        ;

        targetType = loadOptions.getTargetType();

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


    @SuppressWarnings("unchecked")
    @Override
    public <T extends Target<E>> T into(final T target) {
        RequestBuilder<E> drawableRequestBuilder;

        // 输出类型
//        switch (targetType) {
//            case DRAWABLE:
//                drawableRequestBuilder = manager.asDrawable();
//                break;
//            case BITMAP:
//                drawableRequestBuilder = manager.asBitmap();
//                break;
//            case GIF:
//                drawableRequestBuilder = manager.asGif();
//                break;
//            case FILE:
//                drawableRequestBuilder = manager.asFile();
//                break;
//            default:
//                drawableRequestBuilder = manager.asDrawable();
//                break;
//        }

        // 加载类型
//        if (source instanceof String) {
//        } else if (source instanceof Uri) {
//
//            drawableRequestBuilder = drawableRequestBuilder.load((Uri) source);
//        } else if (source instanceof Drawable) {
//            drawableRequestBuilder = drawableRequestBuilder.load((Drawable) source);
//        } else if (source instanceof Bitmap) {
//            drawableRequestBuilder = drawableRequestBuilder.load((Bitmap) source);
//        } else if (source instanceof Integer) {
//            drawableRequestBuilder = drawableRequestBuilder.load((Integer) source);
//        } else if (source instanceof File) {
//            drawableRequestBuilder = drawableRequestBuilder.load((File) source);
//        } else if (source instanceof byte[]) {
//            drawableRequestBuilder = drawableRequestBuilder.load((byte[]) source);
//        } else {
//            drawableRequestBuilder = drawableRequestBuilder.load(source);
//        }

        drawableRequestBuilder = (RequestBuilder<E>) Glide.with(mContext).asBitmap().load(source).apply(options);
        drawableRequestBuilder.into(new SimpleTarget<E>() {
            @Override
            public void onResourceReady(@NonNull E resource, @Nullable Transition<? super E> transition) {
                target.onResourceReady(resource);
            }
        });

        return target;
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

    @Override
    public void cancel() {
    }

    @Override
    public void release() {

    }
}
