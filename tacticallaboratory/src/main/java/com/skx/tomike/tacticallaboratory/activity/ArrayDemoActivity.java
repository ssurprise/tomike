package com.skx.tomike.tacticallaboratory.activity;

import com.skx.tomike.tacticallaboratory.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

/**
 * 描述 : 数据结构 - 数组 demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/6/29 10:26 AM
 */
public class ArrayDemoActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("数据结构 - 数组").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data_structure_array;
    }

    @Override
    protected void initView() {

    }

}
