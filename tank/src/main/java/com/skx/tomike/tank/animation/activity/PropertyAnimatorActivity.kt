package com.skx.tomike.tank.animation.activity

import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.ImageView
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
class PropertyAnimatorActivity : SkxBaseActivity<BaseViewModel>() {

    private val mTargetView: ImageView by lazy {
        findViewById(R.id.iv_propertyAnim_target)
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("属性动画").create()
    }

    override fun initParams() {}
    override fun getLayoutId(): Int {
        return R.layout.activity_property_animatior
    }

    override fun initView() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installListener()
    }

    private fun installListener() {
        mTargetView.setOnClickListener {
            ObjectAnimator.ofFloat(it, "translationY", 0.0f, 720.0f)
                    .setDuration(500)
                    .start()

//            val valueAnimator = ValueAnimator.ofInt(300, 500)
//            valueAnimator.duration = 500
//            valueAnimator.interpolator = LinearInterpolator()
//            valueAnimator.setTarget(textViewTest)
//            valueAnimator.start()
//            valueAnimator.addUpdateListener { animation: ValueAnimator ->
//                val animatedValue = animation.animatedValue as Int
//                textViewTest?.height = animatedValue
//            }


        }

    }
}