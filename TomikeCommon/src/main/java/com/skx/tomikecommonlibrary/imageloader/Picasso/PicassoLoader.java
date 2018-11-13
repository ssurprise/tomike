package com.skx.tomikecommonlibrary.imageloader.Picasso;

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
public class PicassoLoader implements ILoader {

    private Object mSource;


    @Override
    public void init(Context context) {

    }

    @Override
    public <Source> void load(Source source) {
        this.mSource = source;
    }

    @Override
    public void apply(LoadOptions loadOptions) {

    }

    @Override
    public <E, T extends Target<E>> T into(T target) {
        Picasso.get().load((String) mSource).centerCrop().into(new com.squareup.picasso.Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {

            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

        return null;
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
