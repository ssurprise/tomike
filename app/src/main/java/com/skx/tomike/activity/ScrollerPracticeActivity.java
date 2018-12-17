package com.skx.tomike.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.skx.tomike.R;
import com.skx.tomike.customview.TranslateImageView;

/**
 * 描述 : Scroller实践页面
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2016/4/19 5:59 PM
 */
public class ScrollerPracticeActivity extends SkxBaseActivity {
    private TranslateImageView mIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        refreshView();
    }

    @Override
    public void initializeView() {
        super.initializeView();
        setContentView(R.layout.activity_scrollby_imgmove);
        mIv = findViewById(R.id.transImageView);
        mIv.setScaleTypeEx(TranslateImageView.ScaleTypeEx.CROP);
        mIv.setPosition(TranslateImageView.Position.RIGHT);
    }


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e("onAttachedToWindow", "onAttachedToWindow");
    }

    @Override
    public void refreshView() {
        super.refreshView();
        mIv.setImageResource(R.drawable.image_03);
        mIv.post(new Runnable() {
            @Override
            public void run() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mIv.smoothScrollAnimator((int) mIv.getTranslateOffsetX(), 0, 2000);
                    }
                }, 500);
            }
        });
    }

    public void startTranslate(View view) {
        mIv.smoothScrollAnimator((int) mIv.getTranslateOffsetX(), 100, 2000);
    }

    public void reset(View view) {
        mIv.reset();
    }
}
