package com.skx.tomike.tacticallaboratory.pattern.strategy;

import com.skx.tomike.tacticallaboratory.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

/**
 * 描述 : 策略模式-demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/18 1:43 PM
 */
public class StrategyPatternActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("策略模式").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pattern_strategy;
    }

    @Override
    protected void initView() {

    }
}
