package com.skx.tomike.tank.animation.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Build
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_CIRCULAR_REVEAL
import kotlin.math.hypot

@Route(path = ROUTE_PATH_CIRCULAR_REVEAL)
class CircularRevealActivity : SkxBaseActivity<BaseViewModel>() {

    private val mIvCircularReveal: ImageView by lazy {
        findViewById(R.id.circularReveal_1)
    }
    private var isShow = true

    override fun initParams() {
    }

    override fun layoutId(): Int {
        return R.layout.activity_circular_reveal
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("圆形揭示效果").create()
    }

    override fun initView() {
    }

    fun onBtnClick(view: View?) {
        isShow = !isShow
        if (isShow) {
            circularRevealShow()
        } else {
            circularRevealHidden()
        }
    }

    private fun circularRevealShow() {
        val finalRadius = hypot(mIvCircularReveal.width.toDouble(),
                mIvCircularReveal.height.toDouble()).toFloat()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val anim = ViewAnimationUtils.createCircularReveal(mIvCircularReveal,
                    0, 0, 0f, finalRadius)
            mIvCircularReveal.visibility = View.VISIBLE
            anim.start()
        }
    }

    private fun circularRevealHidden() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val finalRadius = hypot(mIvCircularReveal.width.toDouble(),
                    mIvCircularReveal.height.toDouble()).toFloat()
            val anim = ViewAnimationUtils.createCircularReveal(mIvCircularReveal,
                    0, 0, finalRadius, 0f)
            anim.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    mIvCircularReveal.visibility = View.INVISIBLE
                }
            })
            anim.start()
        }
    }

}