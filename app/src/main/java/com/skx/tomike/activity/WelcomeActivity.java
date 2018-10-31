package com.skx.tomike.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.skx.tomike.R;

/**
 * 启动图、欢迎页
 */
public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            gotoHomepage();
        }
    };
    Integer integer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ImageView imageView = (ImageView) findViewById(R.id.welcome_logoImg);

//        int resourceId = R.drawable.welcome_logo;
//        Glide.with(this)
//                .load(resourceId)
//                .asBitmap()
//                .placeholder(R.mipmap.ic_launcher)
////                .crossFade(10000)// 过渡动画时间
////                .dontAnimate()// 取消动画
////                .fitCenter()//图片会被完整显示，可能不能完全填充整个ImageView。
////                .centerCrop()// 图片不缩放，图片可能会显示不全
//                .into(imageView);

        handler.postDelayed(runnable, 3000);
        if (imageView != null) {
            imageView.setOnClickListener(this);
        }
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
            case R.id.welcome_logoImg:
                handler.removeCallbacks(runnable);
                gotoHomepage();
                break;
        }
    }
}
