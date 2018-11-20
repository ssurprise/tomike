package com.skx.tomikecommonlibrary.imageloader.target;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


/**
 * 作者：shiguotao
 * 日期：2018/10/12 下午6:40
 * 描述：ImageLoader 可以在加载期间将资源加载到相关生命周期事件并通知相关生命周期事件的接口。
 */
public interface Target<T> {

    /**
     * Indicates that we want the resource in its original unmodified width and/or height.
     */
    int SIZE_ORIGINAL = Integer.MIN_VALUE;

    /**
     * A lifecycle callback that is called when a load is started.
     * <p>
     * <p> Note - This may not be called for every load, it is possible for example for loads to fail
     * before the load starts (when the model object is null).
     * <p>
     * <p> Note - This method may be called multiple times before any other lifecycle method is
     * called. Loads can be paused and restarted due to lifecycle or connectivity events and each
     * restart may cause a call here.
     * <p>
     * <p>You must ensure that any current Drawable received in {@link #onResourceReady(Object
     *)} is no longer displayed before redrawing the container (usually a View) or
     * changing its visibility.
     *
     * @param placeholder The placeholder drawable to optionally show, or null.
     */
    void onLoadStarted(@Nullable Drawable placeholder);

    /**
     * A lifecycle callback that is called when a load fails.
     * <p>
     * <p> Note - This may be called before {@link #onLoadStarted(android.graphics.drawable.Drawable)
     * } if the model object is null.
     * <p>
     * <p>You must ensure that any current Drawable received in {@link #onResourceReady(Object
     *)} is no longer displayed before redrawing the container (usually a View) or
     * changing its visibility.
     *
     * @param errorDrawable The error drawable to optionally show, or null.
     */
    void onLoadFailed(@Nullable Drawable errorDrawable);

    /**
     * The method that will be called when the resource load has finished.
     *
     * @param resource the loaded resource.
     */
    void onResourceReady(@NonNull T resource);

}
