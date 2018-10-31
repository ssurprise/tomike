package com.skx.tomike.activity;

import android.support.design.widget.AppBarLayout;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;

import com.skx.tomike.R;
import com.skx.tomike.customview.CustomCommentLayoutView;


public class CoordinatorLayoutCaseActivity extends SkxBaseActivity {

    CustomCommentLayoutView nestedScrollingChild2;
    AppBarLayout appBarLayout;
    NestedScrollView customBehavior_body;
    int lastVerticalOffset = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_layout_case);

        appBarLayout = (AppBarLayout) findViewById(R.id.customBehavior_appbar);
        nestedScrollingChild2 = (CustomCommentLayoutView) findViewById(R.id.customBehavior_head);
        customBehavior_body = (NestedScrollView) findViewById(R.id.customBehavior_body);

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
