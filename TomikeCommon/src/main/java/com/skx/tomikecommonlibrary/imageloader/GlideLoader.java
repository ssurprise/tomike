package com.skx.tomikecommonlibrary.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;


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

        switch (loadOptions.getPriority()) {
            case HIGH:
                options.priority(com.bumptech.glide.Priority.HIGH);
                break;
            case LOW:
                options.priority(com.bumptech.glide.Priority.LOW);
                break;
            default:
                options.priority(com.bumptech.glide.Priority.NORMAL);
                break;
        }

        switch (loadOptions.getDiskCacheStrategy()) {
            case ALL:
                options.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL);
                break;
            case NONE:
                options.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.NONE);
                break;
            case DATA:
                options.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.DATA);
                break;
            case RESOURCE:
                options.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.RESOURCE);
                break;
            case AUTOMATIC:
                options.diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.RESOURCE);
                break;
        }
    }


    @SuppressWarnings("unchecked")
    @Override
    public <T extends Target<E>> void into(final T target) {
        RequestManager manager = Glide.with(mContext);
        RequestBuilder<?> drawableRequestBuilder;

        // 输出类型
        switch (targetType) {
            case DRAWABLE:
                drawableRequestBuilder = manager.asDrawable();
                break;
            case BITMAP:
                drawableRequestBuilder = manager.asBitmap();
                break;
            case GIF:
                drawableRequestBuilder = manager.asGif();
                break;
            case FILE:
                drawableRequestBuilder = manager.asFile();
                break;
            default:
                drawableRequestBuilder = manager.asDrawable();
                break;
        }

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

        drawableRequestBuilder = drawableRequestBuilder.load(source).apply(options);
//        drawableRequestBuilder = drawableRequestBuilder.apply(options);

//        Type e;
//        if(e instanceof Bitmap){
//            drawableRequestBuilder.into(new SimpleTarget<e>()){
//                public void onResourceReady(@NonNull E resource, @Nullable Transition<e> transition) {
//                    target.onResourceReady(resource);
//                }
//            }
//        }



        switch (targetType) {
            case BITMAP:
                ((RequestBuilder<Bitmap>) drawableRequestBuilder).into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        target.onResourceReady((E) resource);
                    }
                });
                break;
            case GIF:
                ((RequestBuilder<GifDrawable>) drawableRequestBuilder).into(new SimpleTarget<GifDrawable>() {
                    @Override
                    public void onResourceReady(@NonNull GifDrawable resource, @Nullable Transition<? super GifDrawable> transition) {
                        target.onResourceReady((E) resource);
                    }
                });
                break;
            case FILE:
                ((RequestBuilder<File>) drawableRequestBuilder).into(new SimpleTarget<File>() {
                    @Override
                    public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                        target.onResourceReady((E) resource);
                    }
                });
                break;
            default:
                ((RequestBuilder<Drawable>) drawableRequestBuilder).into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        target.onResourceReady((E) resource);
                    }
                });
                break;
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void release() {

    }
}
