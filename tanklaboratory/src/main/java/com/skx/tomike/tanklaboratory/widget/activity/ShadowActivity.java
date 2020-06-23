package com.skx.tomike.tanklaboratory.widget.activity;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;

public class ShadowActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("投影").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shadow;
    }

    @Override
    protected void initView() {
    /*
        1. CardView 的投影颜色是默认的，并没有提供直接的修改方法。
        2. CardView 的投影是画在父View 上的。如果父View 直接包裹CardView 是不显示投影的

        3. 通过 layer-list 生成生成的drawable 也是不可能超过view的大小来渲染内容的

        4. 重写父View 的onDraw 方法通过 setShadowLayer 的方法

         */
    }
}
