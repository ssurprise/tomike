package com.skx.tomike.tacticallaboratory.pattern.proxy;

import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;

/**
 * 描述 : 代理模式
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/14 9:22 AM
 */
public class ProxyActivity extends SkxBaseActivity<BaseViewModel> {
    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("代理模式").create();
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView() {

    }
}
