package com.skx.tomike.cannon.ui.activity

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_IMAGE_zoom
import com.skx.tomike.cannon.ui.view.ZoomImageView

/**
 * 描述 : 图片缩放
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020-03-20 23:14
 */
@Route(path = ROUTE_PATH_IMAGE_zoom)
class ZoomImageActivity : SkxBaseActivity<BaseViewModel?>() {

    private val mImageArray: IntArray = intArrayOf(
            R.drawable.image_02,
            R.drawable.kuantu,
            R.drawable.changtu
    )

    override fun initParams() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_zoom_image
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("图片缩放").create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    public override fun initView() {
        val mViewPager = findViewById<ViewPager>(R.id.id_viewpager)
        mViewPager.adapter = object : PagerAdapter() {
            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val imageView = ZoomImageView(applicationContext)
                imageView.setImageResource(mImageArray[position])
                container.addView(imageView)
                return imageView
            }

            override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
                val view = obj as View
                container.removeView(view)
            }

            override fun isViewFromObject(arg0: View, arg1: Any): Boolean {
                return arg0 === arg1
            }

            override fun getCount(): Int {
                return mImageArray.size
            }
        }
    }
}