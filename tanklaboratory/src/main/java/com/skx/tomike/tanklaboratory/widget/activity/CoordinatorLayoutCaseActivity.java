package com.skx.tomike.tanklaboratory.widget.activity;

import com.google.android.material.appbar.AppBarLayout;
import com.skx.tomike.tanklaboratory.R;
import com.skx.tomike.tanklaboratory.widget.view.CustomCommentLayoutView;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;

public class CoordinatorLayoutCaseActivity extends SkxBaseActivity<BaseViewModel> {

    int lastVerticalOffset = -1;

    @Override
    protected void initParams() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coordinator_layout_case;
    }

    @Override
    protected void initView() {
        AppBarLayout appBarLayout = findViewById(R.id.customBehavior_appbar);
        final CustomCommentLayoutView nestedScrollingChild2 = findViewById(R.id.customBehavior_head);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (lastVerticalOffset != verticalOffset) {
                    lastVerticalOffset = verticalOffset;
                    nestedScrollingChild2.refreshChildrenDy(verticalOffset);
                }
            }
        });
    }
}
