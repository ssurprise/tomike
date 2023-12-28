package com.skx.tomike.tank.animation.activity

import android.animation.ValueAnimator
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
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
    private var mType: Int = 2


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

        val mRgOptions = findViewById<RadioGroup>(R.id.rg_propertyAnimator_options)
        mRgOptions.check(R.id.tv_propertyAnimator_scan)
        mRgOptions.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.tv_propertyAnimator_rotate -> {
                    mType = 10
                }
                R.id.tv_propertyAnimator_rotateX -> {
                    mType = 11
                }
                R.id.tv_propertyAnimator_rotateY -> {
                    mType = 12
                }
                R.id.tv_propertyAnimator_scan -> {
                    mType = 20
                }
                R.id.tv_propertyAnimator_scanX -> {
                    mType = 21
                }
                R.id.tv_propertyAnimator_scanY -> {
                    mType = 22
                }
                R.id.tv_propertyAnimator_translation -> {
                    mType = 30
                }
                R.id.tv_propertyAnimator_translationX -> {
                    mType = 31
                }
                R.id.tv_propertyAnimator_translationY -> {
                    mType = 32
                }
                R.id.tv_propertyAnimator_alpha -> {
                    mType = 40
                }
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
            when (mType) {
                10 -> {
                    ivTarget.rotationX = it.animatedValue as Float
                }
                11 -> {
                    ivTarget.rotationY = it.animatedValue as Float
                }
                12 -> {
                    ivTarget.rotationY = it.animatedValue as Float
                }
                20 -> {
                    ivTarget.scaleX = it.animatedValue as Float
                    ivTarget.scaleY = it.animatedValue as Float
                }
                21 -> {
                    ivTarget.scaleX = it.animatedValue as Float
                }
                22 -> {
                    ivTarget.scaleY = it.animatedValue as Float
                }
                30 -> {
                    ivTarget.translationX = it.animatedValue as Float
                    ivTarget.translationY = it.animatedValue as Float
                }
                31 -> {
                    ivTarget.translationX = it.animatedValue as Float
                }
                32 -> {
                    ivTarget.translationY = it.animatedValue as Float
                }
                40 -> {
                    ivTarget.alpha = it.animatedValue as Float
                }
            }

        }
        valueAnimator.start()
    }
}