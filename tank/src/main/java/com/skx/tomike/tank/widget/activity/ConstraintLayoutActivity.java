package com.skx.tomike.tank.widget.activity;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_CONSTRAINT_LAYOUT;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.tank.R;

/**
 * 描述 : 约束布局
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/11/14 10:38 下午
 */
@Route(path = ROUTE_PATH_CONSTRAINT_LAYOUT)
public class ConstraintLayoutActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {

    }

    @Nullable
    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("ConstraintLayout Demo").create();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_constraint_layout;
    }

    @Override
    protected void initView() {

    }
}
