package com.skx.tomike.tank.widget.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.tomike.tank.R;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_FLOATINGACTIONBUTTON;

@Route(path = ROUTE_PATH_FLOATINGACTIONBUTTON)
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
