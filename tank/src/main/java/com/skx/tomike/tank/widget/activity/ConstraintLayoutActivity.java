package com.skx.tomike.tank.widget.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.tomike.tank.R;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTER_GROUP;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_CONSTRAINT_LAYOUT;

@Route(path = ROUTE_PATH_CONSTRAINT_LAYOUT, group = ROUTER_GROUP)
public class ConstraintLayoutActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_constraint_layout;
    }

    @Override
    protected void initView() {

    }
}
