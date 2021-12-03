package com.skx.tomike.tank.widget.view

import android.content.Context
import android.graphics.Rect
import android.text.TextUtils
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

/**
 * 默认textview跑马灯
 */
class AlwaysMarqueeTextView : AppCompatTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    init {
        //设置单行
        setSingleLine()
        //获取焦点
        isFocusable = true
        //强制获得焦点
        isFocusableInTouchMode = true
        //设置Ellipsize
        ellipsize = TextUtils.TruncateAt.MARQUEE
        //走马灯的重复次数，-1代表无限重复
        marqueeRepeatLimit = -1
        isSelected = true
    }

    override fun isFocused(): Boolean {
        return true
    }


    //防止Dialog抢占焦点
    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        if (hasWindowFocus) super.onWindowFocusChanged(hasWindowFocus)
    }

    //防止EditText抢占焦点
    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        if (focused) {
            super.onFocusChanged(focused, direction, previouslyFocusedRect)
        }
    }


    private var isLayout = false
    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        if (!isLayout) {
            super.layout(l, t, r, b)
            isLayout = true
        }

    }


}