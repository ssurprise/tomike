package com.skx.tomikecommonlibrary.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.gif.GifDrawable;

import java.io.File;

/**
 * 描述 : 图片加载库
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2018/9/18 下午5:21
 */
public class ImageLoader {


    public static Builder with(@NonNull Context context) {
        return new Builder(context);
    }

    public static class Builder {
        Context context;
        LoadOptions options = new LoadOptions();
        Object source;

        private ILoader iLoader = new GlideLoader();

        public Builder(Context context) {
            this.context = context;
        }

        public Builder load(Object source) {
            this.source = source;
            return this;
        }

        public Builder apply(LoadOptions builder) {
            options = builder;
            return this;
        }

        public <E, T extends Target<E>> void into(T target) {
            iLoader.init(context);
            iLoader.load(source);
            iLoader.apply(options);
            iLoader.into(target);
        }

        public void into(final ImageView targetImageView) {
            iLoader.init(context);
            iLoader.load(source);
            iLoader.apply(options);
            iLoader.into(targetImageView);
        }

        public Builder showPlaceholder(boolean showPlaceholder) {
            options.showPlaceholder(showPlaceholder);
            return this;
        }

        public Builder placeholder(@Nullable Drawable placeholderDrawable) {
            options.showPlaceholder(true).setPlaceholderDrawable(placeholderDrawable);
            return this;
        }

        public Builder placeholder(int placeholderResId) {
            options.showPlaceholder(true).setPlaceholderResId(placeholderResId);
            return this;
        }

        public Builder error(@Nullable Drawable errorDrawable) {
            options.setErrorDrawable(errorDrawable);
            return this;
        }

        public Builder error(int errorResId) {
            options.setErrorResId(errorResId);
            return this;
        }

        public Builder fallback(@Nullable Drawable fallbackDrawable) {
            options.setFallbackDrawable(fallbackDrawable);
            return this;
        }

        public Builder fallback(int fallbackResId) {
            options.setFallbackResId(fallbackResId);
            return this;
        }

        public Builder asFile() {
            options.setSourceType(File.class);
            return this;
        }

        public Builder asGif() {
            options.setSourceType(GifDrawable.class);
            return this;
        }

        public Builder asBitmap() {
            options.setSourceType(Bitmap.class);
            return this;
        }

        public Builder asDrawable() {
            options.setSourceType(Drawable.class);
            return this;
        }
    }
}
