package com.skx.tomike.viewmodel;

import java.lang.ref.WeakReference;

/**
 * MVP模式 - 基类Presenter
 *
 * @author shiguotao
 *         Created on 2017/7/10.
 */
public abstract class AbsBasePresenter<T> implements IBasePresenter<T> {

    /**
     * 当内存不足释放view
     */
    private WeakReference<T> mViewRef;

    /**
     * 绑定View
     *
     * @param view
     */
    public void attachView(T view) {
        mViewRef = new WeakReference<>(view);
    }

    /**
     * 解除绑定的view
     */
    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    public T getView() {
        return mViewRef.get();
    }

    /**
     * 是否与View建立连接
     * 每次调用业务请求的时候都要出先调用方法检查是否与View建立连接
     */
    public boolean isViewAttached() {
        return mViewRef != null && getView() != null;
    }

}
