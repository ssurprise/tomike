package com.skx.tomike.tank.animation.activity

import android.animation.ValueAnimator
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
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

    private val ivTarget: ImageView by lazy {
        findViewById(R.id.iv_propertyAnimator_target)
    }
    private val tvStartBtn: TextView by lazy {
        findViewById(R.id.iv_propertyAnimator_start)
    }

    private var mStartValue: Float = 0.0f
    private var mEndValue: Float = 0.0f
    private var mPivotX: Float = 0.0f
    private var mPivotY: Float = 0.0f
    private var mDuration: Long = 0


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
        tvStartBtn.setOnClickListener {
            onAnimStart()
        }
        findViewById<EditText>(R.id.et_propertyAnimator_start).addTextChangedListener {
            mStartValue = if (it != null && "" != it.toString()) {
                java.lang.Float.parseFloat(it.toString())
            } else {
                0.0f
            }
        }
        findViewById<EditText>(R.id.et_propertyAnimator_end).addTextChangedListener {
            mEndValue = if (it != null && "" != it.toString()) {
                java.lang.Float.parseFloat(it.toString())
            } else {
                0.0f
            }
        }
        findViewById<EditText>(R.id.et_propertyAnimator_pivotX).addTextChangedListener {
            mPivotX = if (it != null && "" != it.toString()) {
                java.lang.Float.parseFloat(it.toString())
            } else {
                0.0f
            }
        }
        findViewById<EditText>(R.id.et_propertyAnimator_pivotY).addTextChangedListener {
            mPivotY = if (it != null && "" != it.toString()) {
                java.lang.Float.parseFloat(it.toString())
            } else {
                0.0f
            }
        }
        findViewById<EditText>(R.id.et_propertyAnimator_duration).addTextChangedListener {
            mDuration = if (it != null && "" != it.toString()) {
                java.lang.Long.parseLong(it.toString())
            } else {
                0
            }
        }

    }

    private fun onAnimStart() {
        ivTarget.pivotX = mPivotX
        ivTarget.pivotY = mPivotY
        val valueAnimator = ValueAnimator.ofFloat(mStartValue, mEndValue).setDuration(mDuration)
//        valueAnimator.repeatCount = 1
        valueAnimator.setTarget(ivTarget)
        valueAnimator.addUpdateListener {
            ivTarget.scaleX = it.animatedValue as Float
            ivTarget.scaleY = it.animatedValue as Float
        }
        valueAnimator.start()
    }
}