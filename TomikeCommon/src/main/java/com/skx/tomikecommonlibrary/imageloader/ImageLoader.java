package com.skx.tomikecommonlibrary.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.skx.tomikecommonlibrary.imageloader.glide.GlideLoader;
import com.skx.tomikecommonlibrary.imageloader.target.Target;
import com.skx.tomikecommonlibrary.imageloader.transform.TransformStrategy;
import com.skx.tomikecommonlibrary.imageloader.transform.TransformAdapter;

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
        final Context mContext;
        LoadOptions mOptions = LoadOptions.getDefaultLoadOptions();
        Object mSource;
        Class<E> mTranscodeClass;

        private ILoader<E> iLoader;

        Builder(Context context) {
            this.mContext = context;
        }

        Builder<E> setTranscodeClass(Class<E> transcodeClass) {
            this.mTranscodeClass = transcodeClass;
            iLoader = new GlideLoader<>(transcodeClass);
            return this;
        }

        public Builder<E> load(Object source) {
            this.mSource = source;
            return this;
        }

        /**
         * 应用指定的可选配置对象，注：指定后会覆盖之前的配置项
         *
         * @param builder 可选配置对象
         * @return 构造器
         */
        public Builder<E> apply(LoadOptions builder) {
            mOptions = builder;
            return this;
        }

        public <T extends Target<E>> void into(T target) {
            iLoader.init(mContext).load(mSource).apply(mOptions).into(target);
        }

        public void into(ImageView targetImageView) {
            iLoader.init(mContext).load(mSource).apply(mOptions).into(targetImageView);
        }

        public Builder<E> noPlaceholder() {
            mOptions.noPlaceholder();
            return this;
        }

        public Builder<E> placeholder(@Nullable Drawable placeholderDrawable) {
            mOptions.placeholder(placeholderDrawable);
            return this;
        }

        public Builder<E> placeholder(int placeholderResId) {
            mOptions.placeholder(placeholderResId);
            return this;
        }

        public Builder<E> error(@Nullable Drawable errorDrawable) {
            mOptions.error(errorDrawable);
            return this;
        }

        public Builder<E> error(int errorResId) {
            mOptions.error(errorResId);
            return this;
        }

        public Builder<E> fallback(@Nullable Drawable fallbackDrawable) {
            mOptions.fallback(fallbackDrawable);
            return this;
        }

        public Builder<E> fallback(int fallbackResId) {
            mOptions.fallback(fallbackResId);
            return this;
        }

        public Builder<E> dontTransform() {
            mOptions.dontTransform();
            return this;
        }

        public Builder<E> transformStrategy(TransformStrategy transformStrategy) {
            mOptions.transformStrategy(transformStrategy);
            return this;
        }

        public Builder<E> transform(TransformAdapter transformAdapter) {
            mOptions.transform(transformAdapter);
            return this;
        }

        public Builder<E> transforms(TransformAdapter... transformAdapters) {
            mOptions.transform(transformAdapters);
            return this;
        }

        public Builder<E> useTransitionAnim() {
            mOptions.transitionAnim(true);
            return this;
        }

        public Builder<E> noTransitionAnim() {
            mOptions.transitionAnim(false);
            return this;
        }

        public Builder<E> resize(@IntRange(from = 0) int targetWidth, @IntRange(from = 0) int targetHeight) {
            mOptions.resize(targetWidth, targetHeight);
            return this;
        }

        public Builder<E> skipMemoryCache() {
            mOptions.memoryCacheable(false);
            return this;
        }

        public Builder<E> diskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
            mOptions.diskCacheStrategy(diskCacheStrategy);
            return this;
        }
    }
}
