package com.skx.tomikecommonlibrary.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
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


    public static Creator with(@NonNull Context context) {
        return new Creator(context);
    }

    public static class Creator {
        final Context context;
        LoadOptions options = LoadOptions.getDefaultLoadOptions();
        Object source;

        private ILoader iLoader = new GlideLoader();

        Creator(Context context) {
            this.context = context;
        }

        public Creator load(Object source) {
            this.source = source;
            return this;
        }

        /**
         * 应用指定的可选配置对象，注：指定后会覆盖之前的配置项
         *
         * @param builder 可选配置对象
         * @return 构造器
         */
        public Creator apply(LoadOptions builder) {
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

        public Creator noPlaceholder() {
            options.noPlaceholder();
            return this;
        }

        public Creator placeholder(@Nullable Drawable placeholderDrawable) {
            options.placeholder(placeholderDrawable);
            return this;
        }

        public Creator placeholder(int placeholderResId) {
            options.placeholder(placeholderResId);
            return this;
        }

        public Creator error(@Nullable Drawable errorDrawable) {
            options.error(errorDrawable);
            return this;
        }

        public Creator error(int errorResId) {
            options.error(errorResId);
            return this;
        }

        public Creator fallback(@Nullable Drawable fallbackDrawable) {
            options.fallback(fallbackDrawable);
            return this;
        }

        public Creator fallback(int fallbackResId) {
            options.fallback(fallbackResId);
            return this;
        }

        public Creator dontTransform() {
            options.dontTransform();
            return this;
        }

        public Creator transformStrategy(TransformStrategy transformStrategy) {
            options.transformStrategy(transformStrategy);
            return this;
        }

        public Creator transform(Transformation transformation) {
            options.transform(transformation);
            return this;
        }

        public Creator transforms(Transformation... transformation) {
            options.transform(transformation);
            return this;
        }

        public Creator useTransitionAnim() {
            options.transitionAnim(true);
            return this;
        }

        public Creator noTransitionAnim() {
            options.transitionAnim(false);
            return this;
        }

        public Creator resize(@IntRange(from = 0) int targetWidth, @IntRange(from = 0) int targetHeight) {
            options.resize(targetWidth, targetHeight);
            return this;
        }

        public Creator asFile() {
            options.setSourceType(File.class);
            return this;
        }

        public Creator asGif() {
            options.setSourceType(GifDrawable.class);
            return this;
        }

        public Creator asBitmap() {
            options.setSourceType(Bitmap.class);
            return this;
        }

        public Creator asDrawable() {
            options.setSourceType(Drawable.class);
            return this;
        }
    }
}
