package com.skx.tomike.tacticallaboratory.activity;

import com.skx.tomike.tacticallaboratory.R;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;

/**
 * 描述 : 数据结构 - 队列 demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/6/29 10:26 AM
 */
public class QueueDemoActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("数据结构 - 队列").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_data_structure_queue;
    }

    @Override
    protected void initView() {

    }
}
