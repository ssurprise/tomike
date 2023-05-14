package com.skx.tomike.missile.pattern.strategy;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.missile.R;

import static com.skx.tomike.missile.RouteConstantsKt.ROUTE_PATH_STRATEGY;

/**
 * 描述 : 策略模式-demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/18 1:43 PM
 */
@Route(path = ROUTE_PATH_STRATEGY)
public class StrategyPatternActivity extends SkxBaseActivity<BaseViewModel<?>> {

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("策略模式").create();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_pattern_strategy;
    }

    @Override
    protected void initView() {

    }
}
