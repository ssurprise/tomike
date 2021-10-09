package com.skx.tomike.tank.animation.activity

import android.animation.Keyframe
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R

/**
 * 描述 : 摇晃动画
 *
 *
 * 提供了两种实现方式：View Animation 和 Property Animator
 *
 *
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/23 4:55 PM
 */
class ShakeAnimatorActivity : SkxBaseActivity<BaseViewModel>() {

    private val mTargetView: ImageView by lazy {
        findViewById(R.id.shakeAnimator_target)
    }

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("摇晃动画的两种实现").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_shake_animatior
    }

    override fun initView() {
    }

    fun propertyAnimator(view: View?) {
        //先往左再往右
        val rotateValuesHolder = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                Keyframe.ofFloat(0f, 0f),
                Keyframe.ofFloat(0.1f, -40f),
                Keyframe.ofFloat(0.2f, 40f),
                Keyframe.ofFloat(0.3f, -40f),
                Keyframe.ofFloat(0.4f, 40f),
                Keyframe.ofFloat(0.5f, -40f),
                Keyframe.ofFloat(0.6f, 40f),
                Keyframe.ofFloat(0.7f, -40f),
                Keyframe.ofFloat(0.8f, 40f),
                Keyframe.ofFloat(0.9f, -40f),
                Keyframe.ofFloat(1.0f, 0f)
        )
        val objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mTargetView, rotateValuesHolder)
        objectAnimator.duration = 2000
        objectAnimator.start()
    }

    fun viewAnimation(view: View?) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.shake)
        mTargetView.startAnimation(animation)
    }
}