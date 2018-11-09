package com.skx.tomikecommonlibrary.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.skx.tomikecommonlibrary.imageloader.Glide.GlideLoader;
import com.skx.tomikecommonlibrary.imageloader.transform.Transformation;

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
        final Context context;
        LoadOptions options = LoadOptions.getDefaultLoadOptions();
        Object source;

        private ILoader iLoader = new GlideLoader();

        public Builder(Context context) {
            this.context = context;
        }

        public Builder load(Object source) {
            this.source = source;
            return this;
        }

        /**
         * 应用指定的可选配置对象，注：指定后会覆盖之前的配置项
         *
         * @param builder 可选配置对象
         * @return 构造器
         */
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

        public void into(ImageView targetImageView) {
            iLoader.init(context);
            iLoader.load(source);
            iLoader.apply(options);
            iLoader.into(targetImageView);
        }

        public Builder noPlaceholder() {
            options.noPlaceholder();
            return this;
        }

        public Builder placeholder(@Nullable Drawable placeholderDrawable) {
            options.placeholder(placeholderDrawable);
            return this;
        }

        public Builder placeholder(int placeholderResId) {
            options.placeholder(placeholderResId);
            return this;
        }

        public Builder error(@Nullable Drawable errorDrawable) {
            options.error(errorDrawable);
            return this;
        }

        public Builder error(int errorResId) {
            options.error(errorResId);
            return this;
        }

        public Builder fallback(@Nullable Drawable fallbackDrawable) {
            options.fallback(fallbackDrawable);
            return this;
        }

        public Builder fallback(int fallbackResId) {
            options.fallback(fallbackResId);
            return this;
        }

        public Builder dontTransform() {
            options.dontTransform();
            return this;
        }

        public Builder transformStrategy(TransformStrategy transformStrategy) {
            options.transformStrategy(transformStrategy);
            return this;
        }

        public Builder transform(Transformation transformation) {
            options.transform(transformation);
            return this;
        }

        public Builder transforms(Transformation... transformation) {
            options.transform(transformation);
            return this;
        }

        public Builder useTransitionAnim() {
            options.transitionAnim(true);
            return this;
        }

        public Builder noTransitionAnim() {
            options.transitionAnim(false);
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
