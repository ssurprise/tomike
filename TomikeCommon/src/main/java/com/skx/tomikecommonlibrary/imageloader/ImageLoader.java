package com.skx.tomikecommonlibrary.imageloader;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;


/**
 * 描述 : 图片加载库
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2018/9/18 下午5:21
 */
public class ImageLoader {


    public static <T, E> Builder<T, E> with(@NonNull Context context) {
        return new Builder<>(context);
    }

    public static class Builder<Source, E> {
        Context context;
        Activity activity;
        LoadOptions options = new LoadOptions();
        Source source;

        private ILoader iLoader = new GlideLoader();


        public Builder(Context context) {
            this.context = context;
        }

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder load(Source source) {
            this.source = source;
            return this;
        }

        public Builder apply(LoadOptions builder) {
            options = builder;
            return this;
        }

        public <T extends Target<E>> void into(T target) {
            if (iLoader instanceof GlideLoader) {
                ((GlideLoader) iLoader).init(activity);
            }
            iLoader.load(source);
            iLoader.apply(options);
            iLoader.into(target);
        }

        public void into(ImageView targetImageView) {
            if (iLoader instanceof GlideLoader) {
                ((GlideLoader) iLoader).init(activity);
            }
            iLoader.load(source);
            iLoader.apply(options);
//            iLoader.into(target);
        }


        public Builder showPlaceholder(boolean showPlaceholder) {
            options.showErrorPlaceholder(showPlaceholder);
            return this;
        }

        public Builder placeholder(@Nullable Drawable placeholderDrawable) {
            options.setPlaceholderDrawable(placeholderDrawable);
            return this;
        }

        public Builder placeholder(int placeholderResId) {
            options.setPlaceholderResId(placeholderResId);
            return this;
        }

        public Builder showErrorPlaceholder(boolean showErrorPlaceholder) {
            options.showErrorPlaceholder(showErrorPlaceholder);
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

        public Builder priority(@NonNull Priority priority) {
            options.setPriority(priority);
            return this;
        }

        public Builder diskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
            options.setDiskCacheStrategy(diskCacheStrategy);
            return this;
        }

        public Builder asFile() {
            options.setTargetType(TargetType.FILE);
            return this;
        }

        public Builder asGif() {
            options.setTargetType(TargetType.GIF);
            return this;
        }

        public Builder asBitmap() {
            options.setTargetType(TargetType.BITMAP);
            return this;
        }

        public Builder asDrawable() {
            options.setTargetType(TargetType.DRAWABLE);
            return this;
        }
    }
}
