package com.skx.tomike.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.tomike.R;

/**
 * 启动图、欢迎页
 */
public class WelcomeActivity extends SkxBaseActivity<BaseViewModel> implements View.OnClickListener {

    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            gotoHomepage();
        }
    };

    @Override
    protected void initParams() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        findViewById(R.id.iv_welcome_mainImg).setOnClickListener(this);
        handler.postDelayed(runnable, 3000);
    }

    private void gotoHomepage() {
        Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
        startActivity(intent);
        WelcomeActivity.this.finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_welcome_mainImg) {
            handler.removeCallbacks(runnable);
            gotoHomepage();
        }
    }
}
