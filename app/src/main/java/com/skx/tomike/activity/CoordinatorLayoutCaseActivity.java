package com.skx.tomike.activity;

import android.os.Bundle;

import androidx.core.widget.NestedScrollView;

import com.google.android.material.appbar.AppBarLayout;
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

        appBarLayout = findViewById(R.id.customBehavior_appbar);
        nestedScrollingChild2 = findViewById(R.id.customBehavior_head);
        customBehavior_body = findViewById(R.id.customBehavior_body);

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
