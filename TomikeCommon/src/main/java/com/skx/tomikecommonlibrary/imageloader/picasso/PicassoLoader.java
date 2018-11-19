package com.skx.tomikecommonlibrary.imageloader.picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.skx.tomikecommonlibrary.imageloader.ILoader;
import com.skx.tomikecommonlibrary.imageloader.LoadOptions;
import com.skx.tomikecommonlibrary.imageloader.target.Target;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 * 作者：shiguotao
 * 日期：2018/11/7 4:55 PM
 * 描述：Picasso 图片加载库封装类
 */
public class PicassoLoader<TranscodeType extends Bitmap> implements ILoader<TranscodeType> {

    private Object mSource;
    private LoadOptions mLoadOptions;

    @Override
    public ILoader<TranscodeType> init(Context context) {
        return this;
    }

    @Override
    public <Source> ILoader<TranscodeType> load(Source source) {
        this.mSource = source;
        return this;
    }

    @Override
    public ILoader<TranscodeType> apply(LoadOptions loadOptions) {
        this.mLoadOptions = loadOptions;
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Target<TranscodeType>> T into(final T target) {
        Picasso.get().load((String) mSource).centerCrop().into(new com.squareup.picasso.Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                target.onResourceReady((TranscodeType) bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                target.onLoadFailed(errorDrawable);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                target.onLoadStarted(placeHolderDrawable);
            }
        });
        return target;
    }

    @Override
    public <T extends ImageView> void into(T target) {
        Picasso.get().load((String) mSource).transform(new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                return source;
            }

            @Override
            public String key() {
                return null;
            }
        }).into(target);
    }

    @Override
    public void onlyDownload() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
