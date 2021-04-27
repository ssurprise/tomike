package com.skx.tomike.tank.animation.activity;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.skx.tomike.tank.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

/**
 * 描述 : 摇晃动画
 * <p>
 * 提供了两种实现方式：View Animation 和 Property Animator
 * <p>
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/23 4:55 PM
 */
public class ShakeAnimatorActivity extends SkxBaseActivity<BaseViewModel> {

    private ImageView mTargetView;

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("摇晃动画的两种实现").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shake_animatior;
    }

    @Override
    protected void initView() {
        mTargetView = findViewById(R.id.shakeAnimator_target);
    }

    public void propertyAnimator(View view) {
        //先往左再往右
        PropertyValuesHolder rotateValuesHolder = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(0.1f, -40),
                Keyframe.ofFloat(0.2f, 40),
                Keyframe.ofFloat(0.3f, -40),
                Keyframe.ofFloat(0.4f, 40),
                Keyframe.ofFloat(0.5f, -40),
                Keyframe.ofFloat(0.6f, 40),
                Keyframe.ofFloat(0.7f, -40),
                Keyframe.ofFloat(0.8f, 40),
                Keyframe.ofFloat(0.9f, -40),
                Keyframe.ofFloat(1.0f, 0f)
        );
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mTargetView, rotateValuesHolder);
        objectAnimator.setDuration(2000);
        objectAnimator.start();
    }

    public void viewAnimation(View view) {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
        mTargetView.startAnimation(animation);
    }
}
