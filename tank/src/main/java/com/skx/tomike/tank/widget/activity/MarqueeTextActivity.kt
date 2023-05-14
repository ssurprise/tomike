package com.skx.tomike.tank.widget.activity

import android.text.TextUtils
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_TEXTVIEW_MARQUEE


/**
 * 描述 : TextView 跑马灯效果
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/12/3 5:23 下午
 */
@Route(path = ROUTE_PATH_TEXTVIEW_MARQUEE)
class MarqueeTextActivity : SkxBaseActivity<BaseViewModel<*>>() {

    private val mTvMarqueeText by lazy {
        findViewById<TextView>(R.id.tv_marquee_test1_value)
    }

    private val mTvMarqueeText2 by lazy {
        findViewById<TextView>(R.id.tv_marquee_test2_value)
    }

    private val textArray = arrayOf(
        "填不满TextView宽度",
        "超过宽度 - 文案不够长怎么办？背一首诗吧：白发渔樵江渚上，惯看秋月春风..."
    )

    override fun initParams() {
    }

    override fun layoutId() = R.layout.activity_marquee_text

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("TextView 跑马灯效果").create()
    }

    override fun initView() {
        findViewById<TextView>(R.id.tv_marquee_text_1).setOnClickListener {
            mTvMarqueeText.text = textArray[0]
            mTvMarqueeText2.text = textArray[0]
        }
        findViewById<TextView>(R.id.tv_marquee_text_2).setOnClickListener {
            mTvMarqueeText.text = textArray[1]
            mTvMarqueeText2.text = textArray[1]
        }
        mTvMarqueeText.run {
            isSingleLine = true
            isFocusable = true
            isSelected = true
            isFocusableInTouchMode = true
            ellipsize = TextUtils.TruncateAt.MARQUEE
            marqueeRepeatLimit = -1
            text = "君不见，高阳酒徒起草中，长揖山东隆准公。"
        }
    }

}