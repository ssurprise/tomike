package com.skx.tomike.tank.widget.activity;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTER_GROUP;
import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_FLOATINGACTIONBUTTON;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.tomike.tank.R;

@Route(path = ROUTE_PATH_FLOATINGACTIONBUTTON, group = ROUTER_GROUP)
public class FloatingActionButtonActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_floating_action_button;
    }

    @Override
    protected void initView() {

    }
}
