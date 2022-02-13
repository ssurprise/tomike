package com.skx.tomike.tank.animation.activity

import android.view.View
import android.view.animation.*
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_TWEEN

/**
 * 描述 : 补间动画
 *
 * 包括：alpha（淡入淡出），translate（位移），scale（缩放大小），rotate（旋转）以及组合动画效果
 *
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/3 2:19 PM
 */
@Route(path = ROUTE_PATH_TWEEN)
class TweenAnimationActivity : SkxBaseActivity<BaseViewModel?>(), View.OnClickListener {

    private var mIvTarget: ImageView? = null

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("补间动画").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_tween_animation
    }

    override fun initView() {
        mIvTarget = findViewById(R.id.iv_tweenAnimation_target)
        findViewById<View>(R.id.btn_tweenAnimation_translate).setOnClickListener(this)
        findViewById<View>(R.id.btn_tweenAnimation_scale).setOnClickListener(this)
        findViewById<View>(R.id.btn_tweenAnimation_rotate).setOnClickListener(this)
        findViewById<View>(R.id.btn_tweenAnimation_alpha).setOnClickListener(this)
        findViewById<View>(R.id.btn_tweenAnimation_set).setOnClickListener(this)
    }

    /**
     * 平移动画：TranslateAnimation
     */
    private fun translateAnimation() {
        val translateAnimation: Animation = TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.5f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f, TranslateAnimation.RELATIVE_TO_SELF, 0f)
        translateAnimation.duration = 1000
        mIvTarget?.startAnimation(translateAnimation)
    }

    /**
     * 缩放动画：ScaleAnimation.
     */
    private fun scaleAnimation() {
        val scaleAnimation = ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
        scaleAnimation.duration = 1000
        mIvTarget?.startAnimation(scaleAnimation)
        /*
        设置动画结束之后的状态是否是动画开始时的状态，true，表示是保持动画开始时的状态
        scaleAnimation.setFillBefore(true);

        设置动画结束之后的状态是否是动画的最终状态，true，表示是保持动画结束时的最终状态
        scaleAnimation.setFillAfter(true);
        */
    }

    /**
     * 旋转动画：RotateAnimation
     *
     * fromDegrees: 旋转开始角度，正代表顺时针度数，负代表逆时针度数
     * toDegrees: 旋转结束角度，正代表顺时针度数，负代表逆时针度数
     * pivotXType: 指定动画中心点的x轴类型。取值 Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_PARENT
     * pivotXValue: 设置动画中心点在x轴的偏移单位。
     * pivotYType: 指定动画中心点的y轴类型。取值 Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, Animation.RELATIVE_TO_PARENT
     * pivotYValue: 设置动画中心点在y轴的偏移单位。
     */
    private fun rotateAnimation() {
        val rotateAnimation = RotateAnimation(0.0f, 720.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation.duration = 1000
        mIvTarget?.startAnimation(rotateAnimation)
    }

    /**
     * 淡入淡出动画：AlphaAnimation
     * fromAlpha:动画开始时的透明度。范围在0.0-1.0之间，其中0.0表示完全透明，1.0表示完全不透明。
     * toAlpha:动画结束时的透明度。范围在0.0-1.0之间，其中0.0表示完全透明，1.0表示完全不透明。
     */
    private fun alphaAnimation() {
        val alphaAnimation = AlphaAnimation(1.0f, 0.0f)
        alphaAnimation.duration = 1000
        mIvTarget?.startAnimation(alphaAnimation)
    }

    /**
     * AnimationSet：动画集合，提供把多个动画组合成一个组合的机制，并可设置动画的时序关系，如同时播放、顺序播放或延迟播放
     */
    private fun setAnimation() {
        val animationSet = AnimationSet(false)

        val translateAnimation: Animation = TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.25f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f)

        val scaleAnimation = ScaleAnimation(
                1.0f, 2.0f,
                1.0f, 2.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)

        val alphaAnimation = AlphaAnimation(1.0f, 0.0f)

        animationSet.addAnimation(translateAnimation)
        animationSet.addAnimation(scaleAnimation)
        animationSet.addAnimation(alphaAnimation)
        animationSet.duration = 1000

        mIvTarget?.startAnimation(animationSet)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_tweenAnimation_translate -> translateAnimation()
            R.id.btn_tweenAnimation_scale -> scaleAnimation()
            R.id.btn_tweenAnimation_rotate -> rotateAnimation()
            R.id.btn_tweenAnimation_alpha -> alphaAnimation()
            R.id.btn_tweenAnimation_set -> setAnimation()
        }
    }
}