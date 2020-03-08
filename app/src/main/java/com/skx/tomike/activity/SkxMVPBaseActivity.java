package com.skx.tomike.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.skx.tomike.viewmodel.AbsBasePresenter;

/**
 * Created by shiguotao on 2017/7/10.
 * <p>
 * MVP 模式的基类Activity
 */
public abstract class SkxMVPBaseActivity<V, T extends AbsBasePresenter> extends SkxBaseActivity {

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    /**
     * 创建一个 presenter
     *
     * @return MVP模式的主导器
     */
    protected abstract T createPresenter();
}
