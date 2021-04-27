package com.skx.tomike.tank.animation.activity;

import android.content.Intent;
import android.view.View;

import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.tomike.tank.R;

/**
 * 动效组 activity
 *
 * @author shiguotao
 */
public class EffectGroupActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_effect_group;
    }

    @Override
    protected void initView() {

    }

    /**
     * 滑动改变标题栏
     *
     * @param view
     */
    public void onScrollChangeTitle(View view) {
        Intent intent = new Intent(this, ScrollChangeTitleActivity.class);
        startActivity(intent);
    }

    public void onScrollZoomImage2(View view) {
        Intent intent = new Intent(this, ScrollZoomImage2Activity.class);
        startActivity(intent);
    }

    public void onScrollZoomImage3(View view) {
        Intent intent = new Intent(this, ScrollZoomImage3Activity.class);
        startActivity(intent);
    }
}
