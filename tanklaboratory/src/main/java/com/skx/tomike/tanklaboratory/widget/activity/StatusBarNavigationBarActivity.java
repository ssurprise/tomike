package com.skx.tomike.tanklaboratory.widget.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.skx.tomike.tanklaboratory.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.utils.ScreenUtilKt;

import java.util.Locale;

/**
 * 获取状态栏高度Activity
 */
public class StatusBarNavigationBarActivity extends SkxBaseActivity<BaseViewModel> implements View.OnClickListener {

    Button status_bar_height;
    TextView status_bar_height_tv;

    Button status_bar_height1;
    TextView status_bar_height1_tv;

    Button status_bar_height2;
    TextView status_bar_height2_tv;

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("状态栏高度").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_statusbar_navigation_height;
    }

    @Override
    protected void initView() {
        status_bar_height = findViewById(R.id.btn_statusBarHeight_byProperty);
        status_bar_height_tv = findViewById(R.id.tv_statusBarHeight_byProperty);

        status_bar_height1 = findViewById(R.id.btn_statusBarHeight_byReflex);
        status_bar_height1_tv = findViewById(R.id.tv_statusBarHeight_byReflex);

        status_bar_height2 = findViewById(R.id.btn_statusBarHeight_byWMS);
        status_bar_height2_tv = findViewById(R.id.tv_statusBarHeight_byWMS);

        status_bar_height.setOnClickListener(this);
        status_bar_height1.setOnClickListener(this);
        status_bar_height2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_statusBarHeight_byProperty) {
            int statusBarHeight = ScreenUtilKt.getStatusBarHeight(StatusBarNavigationBarActivity.this);
            status_bar_height_tv.setText(String.format(Locale.getDefault(), "%d", statusBarHeight));

        } else if (id == R.id.btn_statusBarHeight_byReflex) {
            int statusBarHeight1 = ScreenUtilKt.getStatusBarHeightByReflex(StatusBarNavigationBarActivity.this);
            status_bar_height1_tv.setText(String.format(Locale.getDefault(), "%d", statusBarHeight1));

        } else if (id == R.id.btn_statusBarHeight_byWMS) {
            int statusBarHeight2 = ScreenUtilKt.getStatusBarHeightByWMS(StatusBarNavigationBarActivity.this);
            status_bar_height2_tv.setText(String.format(Locale.getDefault(), "%d", statusBarHeight2));
        }
    }
}
