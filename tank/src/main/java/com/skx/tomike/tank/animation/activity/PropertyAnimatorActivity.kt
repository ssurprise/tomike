package com.skx.tomike.tank.animation.activity

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.Log
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_PROPERTY

/**
 * 属性动画事例
 */
@Route(path = ROUTE_PATH_PROPERTY)
class PropertyAnimatorActivity : SkxBaseActivity<BaseViewModel<*>>() {

    // 头像
    private val cvHeader: CardView by lazy {
        findViewById(R.id.cvHeader)
    }
    private val ivHeaderBgRing: ImageView by lazy {
        findViewById(R.id.ivHeaderBgRing)
    }
    private val ivHeaderShadow: ImageView by lazy {
        findViewById(R.id.ivHeaderShadow)
    }

    // 推荐内容
    private val clContent: ConstraintLayout by lazy {
        findViewById(R.id.clGuideContent)
    }

    // 点击进入按钮
    private val tvGotoBtn: TextView by lazy {
        findViewById(R.id.tvGotoBtn)
    }

    // 取消按钮
    private val ivCancelBtn: ImageView by lazy {
        findViewById(R.id.tvCancelBtn)
    }

    // 显示倒计时提示语view
    private val tvCountdownTime: TextView by lazy {
        findViewById(R.id.tvCountdownTime)
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("属性动画").create()
    }

    override fun initParams() {}
    override fun layoutId(): Int {
        return R.layout.activity_property_animatior
    }

    override fun initView() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cvHeader.post {
            // 头像
            val headerAnim = ObjectAnimator.ofFloat(cvHeader, "alpha", 0.0f, 1.0f)
            headerAnim.duration = 1000
            val headerBgAnim = ObjectAnimator.ofFloat(ivHeaderBgRing, "alpha", 0.0f, 1.0f)
            headerBgAnim.duration = 600
            val headerShadowAnim = ObjectAnimator.ofFloat(ivHeaderShadow, "alpha", 0.0f, 1.0f)
            headerShadowAnim.duration = 1500

            // 推荐内容
            val contentAnim = ObjectAnimator.ofFloat(clContent, "alpha", 0.0f, 1.0f)
            contentAnim.duration = 600

            // 跳出按钮
            val gotoAnim = ObjectAnimator.ofFloat(tvGotoBtn, "alpha", 0.0f, 1.0f)
            gotoAnim.duration = 600

            // 倒计时显示
            val countdownAnim = ObjectAnimator.ofFloat(tvCountdownTime, "alpha", 0.0f, 1.0f)
            countdownAnim.duration = 600

            // 取消按钮
            val cancelAnim = ObjectAnimator.ofFloat(ivCancelBtn, "alpha", 0.0f, 1.0f)
            cancelAnim.duration = 600

            val animatorSet = AnimatorSet()
            animatorSet.play(headerAnim).with(headerBgAnim).with(headerShadowAnim).after(200)
            animatorSet.play(contentAnim).with(cancelAnim).after(500)
            animatorSet.play(gotoAnim).after(1000)
            animatorSet.play(countdownAnim).after(1500)
            animatorSet.interpolator = AccelerateDecelerateInterpolator()
            animatorSet.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    Log.e(TAG, "动画开始")
                }

                override fun onAnimationEnd(animation: Animator) {
                    Log.e(TAG, "动画结束")
                }

                override fun onAnimationCancel(animation: Animator) {}
                override fun onAnimationRepeat(animation: Animator) {}
            })
            animatorSet.start()
        }
    }
}