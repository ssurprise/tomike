package com.skx.tomike.tank.widget.activity;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.ViewCompat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.tank.R;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_VECTOR_DRAWABLE;


/**
 * 描述 : 矢量图
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/7/8 5:36 下午
 */
@Route(path = ROUTE_PATH_VECTOR_DRAWABLE)
public class VectorDrawableActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("矢量图").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vector_drawable;
    }

    @Override
    protected void initView() {
        TextView mTextView = findViewById(R.id.vectorDrawableTest_TextView);
        VectorDrawableCompat vectorDrawableCompat = VectorDrawableCompat.create(getResources(), R.drawable.ic_christmas_candy, null);
        ViewCompat.setBackground(mTextView, vectorDrawableCompat);

        ImageView mAnimatedImageView = findViewById(R.id.vectorDrawableTest_animatedImageView);
        AnimatedVectorDrawableCompat animatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(this, R.drawable.animated_vector);
        mAnimatedImageView.setImageDrawable(animatedVectorDrawableCompat);
        if (animatedVectorDrawableCompat != null) {
            animatedVectorDrawableCompat.start();
        }
    }
}
