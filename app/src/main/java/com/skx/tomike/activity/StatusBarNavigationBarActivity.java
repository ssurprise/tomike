package com.skx.tomike.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomikecommonlibrary.utils.WidthHeightTool;

/**
 * 状态栏、底部导航栏高度Activity
 */
public class StatusBarNavigationBarActivity extends SkxBaseActivity implements View.OnClickListener {

    Button status_bar_height;
    TextView status_bar_height_tv;

    Button status_bar_height1;
    TextView status_bar_height1_tv;

    Button status_bar_height2;
    TextView status_bar_height2_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statusbar_navigation_height);

        initializeView();
        installListener();
    }

    @Override
    public void initializeView() {
        super.initializeView();
        status_bar_height = (Button) findViewById(R.id.status_bar_height);
        status_bar_height_tv = (TextView) findViewById(R.id.status_bar_height_tv);

        status_bar_height1 = (Button) findViewById(R.id.status_bar_height1);
        status_bar_height1_tv = (TextView) findViewById(R.id.status_bar_height1_tv);

        status_bar_height2 = (Button) findViewById(R.id.status_bar_height2);
        status_bar_height2_tv = (TextView) findViewById(R.id.status_bar_height2_tv);
    }

    @Override
    public void installListener() {
        super.installListener();
        status_bar_height.setOnClickListener(this);
        status_bar_height1.setOnClickListener(this);
        status_bar_height2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.status_bar_height:
                int statusBarHeight = WidthHeightTool.getStatusBarHeight(StatusBarNavigationBarActivity.this);
                status_bar_height_tv.setText(String.format("%d", statusBarHeight));
                break;
            case R.id.status_bar_height1:
                int statusBarHeight1 = WidthHeightTool.getStatusBarHeightByReflex(StatusBarNavigationBarActivity.this);
                status_bar_height1_tv.setText(String.format("%d", statusBarHeight1));
                break;
            case R.id.status_bar_height2:
                int statusBarHeight2 = WidthHeightTool.getStatusBarHeightByWMS(StatusBarNavigationBarActivity.this);
                status_bar_height2_tv.setText(String.format("%d", statusBarHeight2));
                break;
            default:
                break;
        }
    }
}
