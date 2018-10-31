package com.skx.tomike.viewmodel;

public interface IBasePresenter<T> {

    /**
     * 绑定View
     *
     * @param view
     */
    void attachView(T view);

    /**
     * 解除绑定的view
     */
    void detachView();

    /**
     * 初始化数据
     */
    void initData();
}
