package com.skx.tomike.tank.widget.activity

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ScaleXSpan
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTER_GROUP
import com.skx.tomike.tank.ROUTE_PATH_TEXTVIEW_WORD_SPACE
import java.util.*

/**
 * 描述 : TextView 设置字间距
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/18 8:27 PM
 *
 * 1、android:lineSpacingExtra
 *   设置行间距，如”3dp”。
 *
 * 2、android:lineSpacingMultiplier
 * 设置行间距的倍数，如”1.2″。
 */
@Route(path = ROUTE_PATH_TEXTVIEW_WORD_SPACE, group = ROUTER_GROUP)
class TextWordSpaceActivity : SkxBaseActivity<BaseViewModel?>() {

    companion object {
        const val TEST_CONTENT = "不自见，故明；不自是，故彰；不自伐，故有功；不自矜，故长。"
    }

    private var mTvMutable: TextView? = null
    private val mTvMutableLabel by lazy {
        findViewById<TextView>(R.id.tv_textSpacing_mutable_factor).apply {
            text = "字间距：0"
        }
    }

    override fun initParams() {}

    override fun getLayoutId(): Int {
        return R.layout.activity_text_view_word_spacing
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("TextView 设置字间距").create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    public override fun initView() {
        findViewById<TextView>(R.id.tv_textSpacing_standard).apply {
            text = TEST_CONTENT
        }

        mTvMutable = findViewById<TextView>(R.id.tv_textSpacing_mutable).apply {
            text = TEST_CONTENT
        }
        findViewById<SeekBar>(R.id.sb_textSpacing_mutable_progress)
            .setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    mTvMutableLabel?.text = String.format(Locale.getDefault(), "字间距：%d", progress)
                    mTvMutable?.text = applyKerning(TEST_CONTENT, progress.toFloat())
                }

                override fun onStartTrackingTouch(seekBar: SeekBar) {}
                override fun onStopTrackingTouch(seekBar: SeekBar) {}
            })
    }

    /**
     * 调整内容间距。
     * 实现原理：在相邻的2个内容之间插入一个空字符，通过控制空字符的横向缩放倍数来实现字间距大小的调整。
     *
     * @param src     目标源
     * @param kerning 缩放倍数
     * @return 调整字间距后的内容
     */
    fun applyKerning(src: CharSequence?, kerning: Float): Spannable? {
        if (src == null) return null
        // 当间距数 <= 0，直接返回
        if (kerning <= 0) return if (src is Spannable) src else SpannableString(src)
        val srcLength = src.length
        // 当源内容长度不足2位时，返回原内容
        if (srcLength < 2) return if (src is Spannable) src else SpannableString(src)
        val nonBreakingSpace = "\u00A0"
        val builder = if (src is SpannableStringBuilder) src else SpannableStringBuilder(src)

        // 插入间距的
        for (i in src.length - 1 downTo 1) {
            builder.insert(i, nonBreakingSpace)
            builder.setSpan(ScaleXSpan(kerning), i, i + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return builder
    }
}