package com.skx.tomike.tank.widget.activity;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_STATUS_BAR_HEIGHT;

import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.utils.ScreenUtilKt;
import com.skx.tomike.tank.R;

import java.util.Locale;

/**
 * 获取状态栏高度Activity
 */
@Route(path = ROUTE_PATH_STATUS_BAR_HEIGHT)
public class StatusBarNavigationBarActivity extends SkxBaseActivity<BaseViewModel<?>> implements View.OnClickListener {

    private TextView mTvGetBarHeightByProperty;
    private TextView mTvGetBarHeightByReflex;
    private TextView mTvGetBarHeightByWMS;

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("状态栏高度").create();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_statusbar_navigation_height;
    }

    @Override
    protected void initView() {
        mTvGetBarHeightByProperty = findViewById(R.id.tv_statusBarHeight_byProperty);
        mTvGetBarHeightByReflex = findViewById(R.id.tv_statusBarHeight_byReflex);
        mTvGetBarHeightByWMS = findViewById(R.id.tv_statusBarHeight_byWMS);

        findViewById(R.id.btn_statusBarHeight_byProperty).setOnClickListener(this);
        findViewById(R.id.btn_statusBarHeight_byReflex).setOnClickListener(this);
        findViewById(R.id.btn_statusBarHeight_byWMS).setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_statusBarHeight_byProperty) {
            int statusBarHeight = ScreenUtilKt.getStatusBarHeight(StatusBarNavigationBarActivity.this);
            mTvGetBarHeightByProperty.setText(String.format(Locale.getDefault(), "%d", statusBarHeight));

        } else if (id == R.id.btn_statusBarHeight_byReflex) {
            int statusBarHeight1 = ScreenUtilKt.getStatusBarHeightByReflex(StatusBarNavigationBarActivity.this);
            mTvGetBarHeightByReflex.setText(String.format(Locale.getDefault(), "%d", statusBarHeight1));

        } else if (id == R.id.btn_statusBarHeight_byWMS) {
            int statusBarHeight2 = ScreenUtilKt.getStatusBarHeightByWMS(StatusBarNavigationBarActivity.this);
            mTvGetBarHeightByWMS.setText(String.format(Locale.getDefault(), "%d", statusBarHeight2));
        }
    }
}
