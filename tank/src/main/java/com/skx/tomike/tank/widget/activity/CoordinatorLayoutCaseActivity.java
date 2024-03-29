package com.skx.tomike.tank.widget.activity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.appbar.AppBarLayout;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.tomike.tank.R;
import com.skx.tomike.tank.widget.view.CustomCommentLayoutView;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_COORDINATOR2;

@Route(path = ROUTE_PATH_COORDINATOR2)
public class CoordinatorLayoutCaseActivity extends SkxBaseActivity<BaseViewModel<?>> {

    int lastVerticalOffset = -1;

    @Override
    protected void initParams() {
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_coordinator_layout_case;
    }

    @Override
    protected void initView() {
        AppBarLayout appBarLayout = findViewById(R.id.customBehavior_appbar);
        final CustomCommentLayoutView nestedScrollingChild2 = findViewById(R.id.customBehavior_head);

        appBarLayout.addOnOffsetChangedListener((appBarLayout1, verticalOffset) -> {
            if (lastVerticalOffset != verticalOffset) {
                lastVerticalOffset = verticalOffset;
                nestedScrollingChild2.refreshChildrenDy(verticalOffset);
            }
        });
    }
}
