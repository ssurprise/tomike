package com.skx.tomike.tank.widget.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.tank.R;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_CARDVIEW;

/**
 * 卡片布局
 * <p/>
 * 如果要在您的布局中设置圆角半径，请使用 card_view:cardCornerRadius 属性。
 * 如果要在您的代码中设置圆角半径，请使用 CardView.setRadius 方法。
 */
@Route(path = ROUTE_PATH_CARDVIEW)
public class CardViewActivity extends SkxBaseActivity<BaseViewModel<?>> {

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("CardView demo").create();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_card_view;
    }

    @Override
    protected void initView() {

    }

}
