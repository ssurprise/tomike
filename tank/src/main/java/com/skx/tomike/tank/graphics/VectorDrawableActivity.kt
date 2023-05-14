package com.skx.tomike.tank.graphics

import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_VECTOR_DRAWABLE

/**
 * 描述 : 矢量图
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/7/8 5:36 下午
 */
@Route(path = ROUTE_PATH_VECTOR_DRAWABLE)
class VectorDrawableActivity : SkxBaseActivity<BaseViewModel<*>>() {

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("矢量图-VectorDrawable").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_vector_drawable
    }

    override fun initView() {
        // 70*70 对比view
        val mTextView = findViewById<TextView>(R.id.vectorDrawableTest_TextView)
        val vectorDrawableCompat = VectorDrawableCompat.create(resources, R.drawable.ic_christmas_candy, null)
        ViewCompat.setBackground(mTextView, vectorDrawableCompat)

        // 50*50 大小view
        val mIvAnimatedVD = findViewById<ImageView>(R.id.iv_vectorDrawable_animated)
        val animatedVectorDrawableCompat = AnimatedVectorDrawableCompat.create(this, R.drawable.animated_vector)
        mIvAnimatedVD.setImageDrawable(animatedVectorDrawableCompat)
        animatedVectorDrawableCompat?.start()

        // 100*100 大小对比view
        val mIvAnimatedVD2 = findViewById<ImageView>(R.id.iv_vectorDrawable_animated2)
        val animatedVectorDrawableCompat2 = AnimatedVectorDrawableCompat.create(this, R.drawable.animated_vector)
        mIvAnimatedVD2.setImageDrawable(animatedVectorDrawableCompat2)
        animatedVectorDrawableCompat2?.start()
    }
}