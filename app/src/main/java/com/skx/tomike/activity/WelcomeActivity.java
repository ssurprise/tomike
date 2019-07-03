package com.skx.tomike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.skx.tomike.R;

/**
 * 启动图、欢迎页
 */
public class WelcomeActivity extends SkxBaseActivity implements View.OnClickListener {

    private final Handler handler = new Handler();
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            gotoHomepage();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ImageView imageView = findViewById(R.id.iv_welcome_mainImg);
        imageView.setOnClickListener(this);

        handler.postDelayed(runnable, 3000);
    }

    private void gotoHomepage() {
        Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
        startActivity(intent);
        WelcomeActivity.this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_welcome_mainImg:
                handler.removeCallbacks(runnable);
                gotoHomepage();
                break;
        }
    }
}
