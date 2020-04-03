package com.skx.tomike.tanklaboratory.animation.activity;

import android.widget.ImageView;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;

/**
 * 描述 : 补间动画
 * <p>
 * 包括：alpha（淡入淡出），translate（位移），scale（缩放大小），rotate（旋转）
 * <p>
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/3 2:19 PM
 */
public class TweenAnimationActivity extends SkxBaseActivity<BaseViewModel> {
    @Override
    protected void initParams() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tween_animation;
    }

    @Override
    protected void initView() {
        ImageView mIvTarget = findViewById(R.id.iv_tweenAnimation_target);
    }
}
