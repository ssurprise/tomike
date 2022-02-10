package com.skx.tomike.tank.animation.activity

import com.airbnb.lottie.LottieAnimationView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_LOTTIE

/**
 * 描述 : Airbnb 动画库 - lottie
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/12/18 2:36 PM
 */
@Route(path = ROUTE_PATH_LOTTIE)
class LottieActivity : SkxBaseActivity<BaseViewModel>() {

    private val lottieView by lazy {
        findViewById<LottieAnimationView>(R.id.lottieView)
    }
    private val lottieView2 by lazy {
        findViewById<LottieAnimationView>(R.id.lottieView2)
    }

    override fun initParams() {
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("Airbnb 动画库-lottie").create()
    }

    override fun layoutId(): Int {
        return R.layout.activity_lottie_anim
    }

    override fun initView() {
        // 从 asset 文件夹添加
        lottieView.imageAssetsFolder = "gift/"
        lottieView.setAnimation("gift/voice_newcomer_guide_gift.json")

        // 从 raw 文件夹添加
        lottieView2.imageAssetsFolder = "mic/"
        lottieView2.setAnimation(R.raw.voice_newcomer_guide_mic)

//        LottieCompositionFactory.fromRawRes(
//            this,
//            .voice_newcomer_guide_gift
//        ).addListener {
//            lottieView.setComposition(it)
//        }
//        lottieView.imageAssetsFolder
//        lottieView.playAnimation()
    }
}