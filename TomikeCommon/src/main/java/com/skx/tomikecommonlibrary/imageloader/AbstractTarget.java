package com.skx.tomikecommonlibrary.imageloader;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;

/**
 * 作者：shiguotao
 * 日期：2018/10/17 上午10:27
 * 描述：ImageLoader Target 骨架类
 */
public abstract class AbstractTarget<T> implements Target<T> {
    @Override
    public void onLoadStarted(@Nullable Drawable placeholder) {

    }

    @Override
    public void onLoadFailed(@Nullable Drawable errorDrawable) {

    }

    @Override
    public void onLoadCleared(@Nullable Drawable placeholder) {

    }
}
