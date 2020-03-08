package com.skx.tomikecommonlibrary.imageloader.target;

import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;

/**
 * 作者：shiguotao
 * 日期：2018/10/17 上午10:27
 * 描述：ImageLoader Target 骨架类，空实现{@link AbstractTarget#onLoadStarted(android.graphics.drawable.Drawable)}
 * 和 {@link AbstractTarget#onLoadFailed(android.graphics.drawable.Drawable)}函数
 */
public abstract class AbstractTarget<T> implements Target<T> {
    @Override
    public void onLoadStarted(@Nullable Drawable placeholder) {

    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {

    }
}
