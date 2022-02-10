package com.skx.tomike.tank.animation.activity

import android.os.Handler
import android.os.Looper
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_SCROLLER
import com.skx.tomike.tank.widget.view.TranslateImageView

/**
 * 描述 : View 内容移动 - Scroller实践
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2016/4/19 5:59 PM
 */
@Route(path = ROUTE_PATH_SCROLLER)
class ScrollerPracticeActivity : SkxBaseActivity<BaseViewModel?>() {

    private val mIv: TranslateImageView by lazy {
        findViewById(R.id.tv_scroller_transImageView)
    }

    override fun initParams() {}
    override fun configHeaderTitle(): TitleConfig? {
        return TitleConfig.Builder().setTitleText("View 内容移动 - Scroller实践").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_scrollby_imgmove
    }

    override fun initView() {
        mIv.setScaleTypeEx(TranslateImageView.ScaleTypeEx.CROP)
        mIv.setPosition(TranslateImageView.Position.RIGHT)
        mIv.setImageResource(R.drawable.image_03)
        mIv.post {
            Handler(Looper.myLooper()!!).postDelayed({
                mIv.smoothScrollAnimator(
                    mIv.getTranslateOffsetX().toInt(), 0, 2000
                )
            }, 500)
        }
    }

    fun startTranslate(view: View?) {
        mIv.smoothScrollAnimator(mIv.translateOffsetX.toInt(), 0, 2000)
    }

    fun reset(view: View?) {
        mIv.reset()
    }
}