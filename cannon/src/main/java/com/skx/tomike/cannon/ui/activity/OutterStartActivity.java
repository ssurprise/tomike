package com.skx.tomike.cannon.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.skx.tomike.cannon.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;


public class OutterStartActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("打开外部APP").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_outter_start;
    }

    @Override
    protected void initView() {

    }

    public void startXiaoZhu(View view) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("openapp.xzdz://xiaozhu/openpage?page=index"));
        startActivity(intent);
    }

}
