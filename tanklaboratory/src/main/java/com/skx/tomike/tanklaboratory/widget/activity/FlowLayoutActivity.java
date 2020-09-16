package com.skx.tomike.tanklaboratory.widget.activity;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomike.tanklaboratory.widget.adapter.FlowAdapter;
import com.skx.tomike.tanklaboratory.widget.view.FlowLayout;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

/**
 * 描述 : 流式布局
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/24 8:05 PM
 */
public class FlowLayoutActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("流式布局").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_flow_layout;
    }

    @Override
    protected void initView() {
        FlowLayout flowLayout = findViewById(R.id.fl_flowLayoutTest_content);
        flowLayout.setAdapter(new FlowAdapter());
    }
}
