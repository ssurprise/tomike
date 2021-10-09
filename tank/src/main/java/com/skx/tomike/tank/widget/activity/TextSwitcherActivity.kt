package com.skx.tomike.tank.widget.activity

import android.graphics.Color
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextSwitcher
import android.widget.TextView
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R

/**
 * 描述 : TextSwitcher demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/18 8:31 PM
 */
class TextSwitcherActivity : SkxBaseActivity<BaseViewModel>() {

    private var textSwitcher: TextSwitcher? = null
    private var textSwitcher2: TextSwitcher? = null
    private var index = NAME_COFFEE

    override fun initParams() {}

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("TextSwitcher 实现效果").create()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_text_switcher
    }

    override fun initView() {
        textSwitcher = findViewById(R.id.ts_textSwitcher_xml_implement)
        textSwitcher?.run {
            inAnimation = AnimationUtils.loadAnimation(mActivity, android.R.anim.fade_in)
            outAnimation = AnimationUtils.loadAnimation(mActivity, android.R.anim.fade_out)
            setCurrentText(index)
        }

        textSwitcher2 = findViewById(R.id.ts_textSwitcher_code_implement)
        textSwitcher2?.run {
            setInAnimation(mActivity, R.anim.show)
            setOutAnimation(mActivity, R.anim.hide)
            setFactory {
                val textView = TextView(this@TextSwitcherActivity)
                textView.textSize = 14f
                textView.setTextColor(Color.BLACK)
                textView
            }
            setCurrentText(index)
        }

        findViewById<View>(R.id.btn_textSwitcher_start).setOnClickListener {
            if (NAME_COFFEE.equals(index, ignoreCase = true)) {
                index = NAME_CAKE
            } else if (NAME_CAKE.equals(index, ignoreCase = true)) {
                index = NAME_COFFEE
            }
            textSwitcher?.setText(index)
            textSwitcher2?.setText(index)
        }
    }

    companion object {
        const val NAME_COFFEE = "咖啡机"
        const val NAME_CAKE = "面包机"
    }
}