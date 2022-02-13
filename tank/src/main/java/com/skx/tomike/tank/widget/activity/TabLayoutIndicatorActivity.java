package com.skx.tomike.tank.widget.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.tank.R;

import java.lang.reflect.Field;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_TABLELAYOUT_INDICATOR;

/**
 * 描述 : TabLayout 修改 indicator
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/5/30 10:55 AM
 */
@Route(path = ROUTE_PATH_TABLELAYOUT_INDICATOR)
public class TabLayoutIndicatorActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("TabLayout 指示器").create();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_tablayout_indicator;
    }

    @Override
    protected void initView() {
        TabLayout tabLayout = findViewById(R.id.tl_tabLayoutIndicator_reflect);
        changeTabLayoutIndicatorWidth(tabLayout);
    }

    private void changeTabLayoutIndicatorWidth(TabLayout tabLayout) {
        if (tabLayout == null) return;
        try {
            Field mTabStrip = tabLayout.getClass().getDeclaredField("slidingTabIndicator");
            mTabStrip.setAccessible(true);

            LinearLayout mLlTabStrip = (LinearLayout) mTabStrip.get(tabLayout);
            if (mLlTabStrip == null) {
                return;
            }
            for (int i = 0, j = mLlTabStrip.getChildCount(); i < j; i++) {
                View tabItem = mLlTabStrip.getChildAt(i);
                //拿到tabView的mTextView属性
                Field textViewField = tabItem.getClass().getDeclaredField("textView");
                textViewField.setAccessible(true);
                TextView mTextView = (TextView) textViewField.get(tabItem);
                tabItem.setPadding(0, 0, 0, 0);
                // 因为想要的效果是字多宽线就多宽，所以测量mTextView的宽度
                int width = mTextView.getWidth();
                if (width == 0) {
                    mTextView.measure(0, 0);
                    width = mTextView.getMeasuredWidth();
                }
                // 设置tab左右间距,注意这里不能使用Padding,因为源码中线的宽度是根据tabView的宽度来设置的
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabItem.getLayoutParams();
                params.width = width;
                params.leftMargin = 100;
                params.rightMargin = 100;
                mTextView.setLayoutParams(params);
                mTextView.invalidate();
            }

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
