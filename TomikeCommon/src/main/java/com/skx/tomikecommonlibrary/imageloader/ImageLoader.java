package com.skx.tomikecommonlibrary.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.skx.tomikecommonlibrary.imageloader.Glide.GlideLoader;
import com.skx.tomikecommonlibrary.imageloader.target.Target;
import com.skx.tomikecommonlibrary.imageloader.transform.TransformStrategy;
import com.skx.tomikecommonlibrary.imageloader.transform.Transformation;

import java.io.File;

/**
 * 描述 : 图片加载库
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2018/9/18 下午5:21
 */
public class ImageLoader {


    public static Builder<Drawable> with(@NonNull Context context) {
        return new Builder<Drawable>(context).setTranscodeClass(Drawable.class);
    }

    public static Builder<Bitmap> asBitmap(@NonNull Context context) {
        return new Builder<Bitmap>(context).setTranscodeClass(Bitmap.class);
    }

    public static Builder<File> asFile(@NonNull Context context) {
        return new Builder<File>(context).setTranscodeClass(File.class);
    }

    public static Builder<SGifDrawable> asGif(@NonNull Context context) {
        return new Builder<SGifDrawable>(context).setTranscodeClass(SGifDrawable.class);
    }

    public static class Builder<E> {
        final Context context;
        LoadOptions options = LoadOptions.getDefaultLoadOptions();
        Object source;
        Class<E> transcodeClass;

        private ILoader<E> iLoader;

        Builder(Context context) {
            this.context = context;
        }

        Builder<E> setTranscodeClass(Class<E> transcodeClass) {
            this.transcodeClass = transcodeClass;
            iLoader = new GlideLoader<>(transcodeClass);
            return this;
        }

        public Builder<E> load(Object source) {
            this.source = source;
            return this;
        }

        /**
         * 应用指定的可选配置对象，注：指定后会覆盖之前的配置项
         *
         * @param builder 可选配置对象
         * @return 构造器
         */
        public Builder<E> apply(LoadOptions builder) {
            options = builder;
            return this;
        }

        public <T extends Target<E>> void into(T target) {
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

        public Builder<E> noPlaceholder() {
            options.noPlaceholder();
            return this;
        }

        public Builder<E> placeholder(@Nullable Drawable placeholderDrawable) {
            options.placeholder(placeholderDrawable);
            return this;
        }

        public Builder<E> placeholder(int placeholderResId) {
            options.placeholder(placeholderResId);
            return this;
        }

        public Builder<E> error(@Nullable Drawable errorDrawable) {
            options.error(errorDrawable);
            return this;
        }

        public Builder<E> error(int errorResId) {
            options.error(errorResId);
            return this;
        }

        public Builder<E> fallback(@Nullable Drawable fallbackDrawable) {
            options.fallback(fallbackDrawable);
            return this;
        }

        public Builder<E> fallback(int fallbackResId) {
            options.fallback(fallbackResId);
            return this;
        }

        public Builder<E> dontTransform() {
            options.dontTransform();
            return this;
        }

        public Builder<E> transformStrategy(TransformStrategy transformStrategy) {
            options.transformStrategy(transformStrategy);
            return this;
        }

        public Builder<E> transform(Transformation transformation) {
            options.transform(transformation);
            return this;
        }

        public Builder<E> transforms(Transformation... transformation) {
            options.transform(transformation);
            return this;
        }

        public Builder<E> useTransitionAnim() {
            options.transitionAnim(true);
            return this;
        }

        public Builder<E> noTransitionAnim() {
            options.transitionAnim(false);
            return this;
        }

        public Builder<E> resize(@IntRange(from = 0) int targetWidth, @IntRange(from = 0) int targetHeight) {
            options.resize(targetWidth, targetHeight);
            return this;
        }
    }
}
