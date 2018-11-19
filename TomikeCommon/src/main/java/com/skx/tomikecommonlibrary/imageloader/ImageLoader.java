package com.skx.tomikecommonlibrary.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.CheckResult;
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
public final class ImageLoader {

    private ImageLoader() {
    }

    public static Manager with(@NonNull Context context) {
        return new Manager(context);
    }

    /**
     * 描述 : 图片加载管理者
     * 作者 : shiguotao
     * 版本 : V1
     * 创建时间 : 2018/11/19 4:17 PM
     */
    public final static class Manager implements SourceType<Builder<Drawable>> {

        private final Context context;

        public Manager(Context context) {
            this.context = context;
        }

        public Builder<Drawable> load(Object source) {
            return as(Drawable.class).load(source);
        }

        public Builder<Drawable> asDrawable() {
            return as(Drawable.class);
        }

        public Builder<Bitmap> asBitmap() {
            return as(Bitmap.class);
        }

        public Builder<File> asFile() {
            return as(File.class);
        }

        public Builder<SGifDrawable> asGif() {
            return as(SGifDrawable.class);
        }

        /**
         * 图片文件下载，产出的是参数类型为File的 Builder 实例
         *
         * @return 参数类型为File的 Builder 实例
         */
        public Builder<File> download() {
            return as(File.class);
        }

        @NonNull
        @CheckResult
        private <TranscodeType> Builder<TranscodeType> as(
                @NonNull Class<TranscodeType> transcodeType) {
            return new Builder<>(context, transcodeType);
        }
    }

    /**
     * 描述 : 图片加载请求构造器
     * 作者 : shiguotao
     * 版本 : V1
     * 创建时间 : 2018/11/19 4:15 PM
     */
    public final static class Builder<TranscodeType> implements SourceType<Builder<TranscodeType>> {
        private final Context mContext;
        private final ILoader<TranscodeType> iLoader;
        private LoadOptions mOptions = LoadOptions.getDefaultLoadOptions();
        private Object mSource;

        public Builder(Context mContext, Class<TranscodeType> TranscodeType) {
            this.mContext = mContext;
            this.iLoader = new GlideLoader<>(TranscodeType);
        }

        @NonNull
        @Override
        public Builder<TranscodeType> load(Object source) {
            this.mSource = source;
            return this;
        }

        /**
         * 应用指定的可选配置对象，注：指定后会覆盖之前的配置项
         *
         * @param builder 可选配置对象
         * @return 构造器
         */
        public Builder<TranscodeType> apply(LoadOptions builder) {
            mOptions = builder;
            return this;
        }

        public <T extends Target<TranscodeType>> void into(T target) {
            iLoader.init(mContext).load(mSource).apply(mOptions).into(target);
        }

        public void into(ImageView targetImageView) {
            iLoader.init(mContext).load(mSource).apply(mOptions).into(targetImageView);
        }

        public Builder<TranscodeType> noPlaceholder() {
            mOptions.noPlaceholder();
            return this;
        }

        public Builder<TranscodeType> placeholder(@Nullable Drawable placeholderDrawable) {
            mOptions.placeholder(placeholderDrawable);
            return this;
        }

        public Builder<TranscodeType> placeholder(int placeholderResId) {
            mOptions.placeholder(placeholderResId);
            return this;
        }

        public Builder<TranscodeType> error(@Nullable Drawable errorDrawable) {
            mOptions.error(errorDrawable);
            return this;
        }

        public Builder<TranscodeType> error(int errorResId) {
            mOptions.error(errorResId);
            return this;
        }

        public Builder<TranscodeType> fallback(@Nullable Drawable fallbackDrawable) {
            mOptions.fallback(fallbackDrawable);
            return this;
        }

        public Builder<TranscodeType> fallback(int fallbackResId) {
            mOptions.fallback(fallbackResId);
            return this;
        }

        public Builder<TranscodeType> dontTransform() {
            mOptions.dontTransform();
            return this;
        }

        public Builder<TranscodeType> transformStrategy(TransformStrategy transformStrategy) {
            mOptions.transformStrategy(transformStrategy);
            return this;
        }

        public Builder<TranscodeType> transform(TransformAdapter transformAdapter) {
            mOptions.transform(transformAdapter);
            return this;
        }

        public Builder<TranscodeType> transforms(TransformAdapter... transformAdapters) {
            mOptions.transform(transformAdapters);
            return this;
        }

        public Builder<TranscodeType> useTransitionAnim() {
            mOptions.transitionAnim(true);
            return this;
        }

        public Builder<TranscodeType> noTransitionAnim() {
            mOptions.transitionAnim(false);
            return this;
        }

        public Builder<TranscodeType> resize(@IntRange(from = 0) int targetWidth, @IntRange(from = 0) int targetHeight) {
            mOptions.resize(targetWidth, targetHeight);
            return this;
        }

        public Builder<TranscodeType> skipMemoryCache() {
            mOptions.memoryCacheable(false);
            return this;
        }

        public Builder<TranscodeType> diskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
            mOptions.diskCacheStrategy(diskCacheStrategy);
            return this;
        }
    }
}
