package com.skx.tomike.tanklaboratory.widget.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.utils.WidthHeightTool;

import java.util.Locale;

/**
 * 状态栏、底部导航栏高度Activity
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
    protected int getLayoutId() {
        return R.layout.activity_statusbar_navigation_height;
    }

    @Override
    protected void initView() {
        status_bar_height = findViewById(R.id.status_bar_height);
        status_bar_height_tv = findViewById(R.id.status_bar_height_tv);

        status_bar_height1 = findViewById(R.id.status_bar_height1);
        status_bar_height1_tv = findViewById(R.id.status_bar_height1_tv);

        status_bar_height2 = findViewById(R.id.status_bar_height2);
        status_bar_height2_tv = findViewById(R.id.status_bar_height2_tv);

        status_bar_height.setOnClickListener(this);
        status_bar_height1.setOnClickListener(this);
        status_bar_height2.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.status_bar_height) {
            int statusBarHeight = WidthHeightTool.getStatusBarHeight(StatusBarNavigationBarActivity.this);
            status_bar_height_tv.setText(String.format(Locale.getDefault(), "%d", statusBarHeight));

        } else if (id == R.id.status_bar_height1) {
            int statusBarHeight1 = WidthHeightTool.getStatusBarHeightByReflex(StatusBarNavigationBarActivity.this);
            status_bar_height1_tv.setText(String.format(Locale.getDefault(), "%d", statusBarHeight1));

        } else if (id == R.id.status_bar_height2) {
            int statusBarHeight2 = WidthHeightTool.getStatusBarHeightByWMS(StatusBarNavigationBarActivity.this);
            status_bar_height2_tv.setText(String.format(Locale.getDefault(), "%d", statusBarHeight2));
        }
    }
}
