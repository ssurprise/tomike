package com.skx.tomike.tank.widget.activity;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.view.ViewCompat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import com.skx.tomike.tank.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;


public class VectorDrawableActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vector_drawable;
    }

    @Override
    protected void initView() {
        ImageView mImageView = findViewById(R.id.vectorDrawableTest_ImageView);
        ImageView mAnimatedImageView = findViewById(R.id.vectorDrawableTest_animatedImageView);
        TextView mTextView = findViewById(R.id.vectorDrawableTest_TextView);

        VectorDrawableCompat vectorDrawableCompat = VectorDrawableCompat.create(getResources(), R.drawable.ic_christmas_candy, null);
        ViewCompat.setBackground(mTextView, vectorDrawableCompat);


        AnimatedVectorDrawableCompat animatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(this, R.drawable.animated_vector);
        mAnimatedImageView.setImageDrawable(animatedVectorDrawableCompat);
        if (animatedVectorDrawableCompat != null) {
            animatedVectorDrawableCompat.start();
        }
    }
}
