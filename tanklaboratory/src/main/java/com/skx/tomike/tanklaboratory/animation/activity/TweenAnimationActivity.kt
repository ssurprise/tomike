package com.skx.tomike.tanklaboratory.animation.activity

import android.view.View
import android.view.animation.*
import android.widget.ImageView
import com.skx.tomike.tanklaboratory.R
import com.skx.tomikecommonlibrary.base.BaseViewModel
import com.skx.tomikecommonlibrary.base.SkxBaseActivity
import com.skx.tomikecommonlibrary.base.TitleConfig

/**
 * 描述 : 补间动画
 *
 *
 * 包括：alpha（淡入淡出），translate（位移），scale（缩放大小），rotate（旋转）
 *
 *
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/3 2:19 PM
 */
class TweenAnimationActivity : SkxBaseActivity<BaseViewModel?>(), View.OnClickListener {

    private var mIvTarget: ImageView? = null

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("补间动画").create()
    }

    override fun getLayoutId(): Int {
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
     * 平移动画
     */
    private fun translateAnimation() {
        val translateAnimation: Animation = TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.5f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f
                , TranslateAnimation.RELATIVE_TO_SELF, 0f)
        translateAnimation.duration = 1000
        mIvTarget?.startAnimation(translateAnimation)
    }

    /**
     * 缩放动画
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
     * 缩放动画
     */
    private fun rotateAnimation() {
        val rotateAnimation = RotateAnimation(0.0f, 720.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
        rotateAnimation.duration = 1000
        mIvTarget?.startAnimation(rotateAnimation)
    }

    /**
     * 淡入淡出动画
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

        val translateAnimation: Animation = TranslateAnimation(TranslateAnimation.RELATIVE_TO_PARENT, 0.0f,
                TranslateAnimation.RELATIVE_TO_PARENT, 0.25f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f
                , TranslateAnimation.RELATIVE_TO_SELF, 0f)

        val scaleAnimation = ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f,
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