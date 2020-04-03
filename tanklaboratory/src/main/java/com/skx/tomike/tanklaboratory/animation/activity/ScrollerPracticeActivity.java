package com.skx.tomike.tanklaboratory.animation.activity;

import android.os.Handler;
import android.view.View;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomike.tanklaboratory.widget.view.TranslateImageView;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;

/**
 * 描述 : View 内容移动 - Scroller实践
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2016/4/19 5:59 PM
 */
public class ScrollerPracticeActivity extends SkxBaseActivity<BaseViewModel> {

    private TranslateImageView mIv;

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("View 内容移动 - Scroller实践").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scrollby_imgmove;
    }

    @Override
    protected void initView() {
        mIv = findViewById(R.id.tv_scroller_transImageView);
        mIv.setScaleTypeEx(TranslateImageView.ScaleTypeEx.CROP);
        mIv.setPosition(TranslateImageView.Position.RIGHT);

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
        mIv.smoothScrollAnimator((int) mIv.getTranslateOffsetX(), 0, 2000);
    }

    public void reset(View view) {
        mIv.reset();
    }
}
