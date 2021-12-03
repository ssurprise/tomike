package com.skx.tomike.tank.widget.view

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * 描述 : 跑马灯TextView
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/12/3 5:56 下午
 */
class MarqueeTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        this.ellipsize = TextUtils.TruncateAt.MARQUEE//设置跑马等效果
        this.marqueeRepeatLimit = -1//设置跑马灯重复次数
        this.isSingleLine = true//设置单行
    }

    /** 滚动次数 */
    private var marqueeNum: Int = -1//-1为永久循环，大于0是循环次数。`

    fun setMarqueeNum(marqueeNum: Int) {
        this.marqueeNum = marqueeNum
    }

    /**
     * 始终获取焦点
     * 跑马灯在TextView处于焦点状态的时候才会滚动
     */
    override fun isFocused(): Boolean {
        return true
    }
}