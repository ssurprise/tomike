package com.skx.tomike.tank.widget.activity

import android.view.View
import com.skx.tomike.tank.R
import com.skx.tomike.tank.widget.view.TranslateImageView
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig

/**
 * 描述 : 自定义显示方向的ImageView
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/6/23 2:16 PM
 */
class MatrixImageActivity : SkxBaseActivity<BaseViewModel?>(), View.OnClickListener {

    private var mImage: TranslateImageView? = null

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("自定义Image 显示位置的ImageView").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_martix_image
    }

    override fun initView() {
        mImage = findViewById(R.id.matrixImage_mainImage)
        mImage?.setImageResource(R.drawable.image_01)
        mImage?.setPosition(TranslateImageView.Position.RIGHT_BOTTOM)

        findViewById<View>(R.id.tv_matrixImage_topLeftBtn).setOnClickListener(this)
        findViewById<View>(R.id.tv_matrixImage_topRightBtn).setOnClickListener(this)
        findViewById<View>(R.id.tv_matrixImage_bottomLeftBtn).setOnClickListener(this)
        findViewById<View>(R.id.tv_matrixImage_bottomRightBtn).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_matrixImage_topLeftBtn -> {
                mImage?.setPositionAndUpdate(TranslateImageView.Position.LEFT)
            }
            R.id.tv_matrixImage_topRightBtn -> {
                mImage?.setPositionAndUpdate(TranslateImageView.Position.RIGHT)
            }
            R.id.tv_matrixImage_bottomLeftBtn -> {
                mImage?.setPositionAndUpdate(TranslateImageView.Position.BOTTOM)
            }
            R.id.tv_matrixImage_bottomRightBtn -> {
                mImage?.setPositionAndUpdate(TranslateImageView.Position.RIGHT_BOTTOM)
            }
        }
    }

    /*
     * ScaleType.CENTER 图片居中显示。查看源码 可以看到只居中，并没有进行缩放
     if (ScaleType.CENTER == mScaleType) {
     // Center bitmap in view, no scaling.
     mDrawMatrix = mMatrix;
     mDrawMatrix.setTranslate(Math.round((vwidth - dwidth) * 0.5f),
     Math.round((vheight - dheight) * 0.5f));
     }
     */
}