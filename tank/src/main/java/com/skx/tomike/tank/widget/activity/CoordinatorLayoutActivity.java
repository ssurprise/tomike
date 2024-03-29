package com.skx.tomike.tank.widget.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.tomike.tank.R;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_COORDINATOR;

/**
 * 描述 : CoordinatorLayout - 联动
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/11/14 10:38 下午
 */
@Route(path = ROUTE_PATH_COORDINATOR)
public class CoordinatorLayoutActivity extends SkxBaseActivity<BaseViewModel<?>> {

    @Override
    protected void initParams() {

    }

    @Override
    protected int layoutId() {
        return R.layout.activity_coordinator_layout;
    }

    @Override
    protected void initView() {

    }
}
