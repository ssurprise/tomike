package com.skx.tomike.tank.widget.view

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * 描述 : 支持禁止左右滑动的ViewPager
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2023/4/6 10:42 下午
 *
 * 实现方案参考：{@link ViewPager2.RecyclerViewImpl}
 */
class NoScrollViewPager : ViewPager {

    private var mUserInputEnabled = true

    constructor(context: Context) : super(context) {}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {}

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return isUserInputEnabled() && super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return isUserInputEnabled() && super.onInterceptTouchEvent(ev)
    }

    fun setUserInputEnabled(enabled: Boolean) {
        mUserInputEnabled = enabled
    }

    /**
     * Returns if user initiated scrolling between pages is enabled. Enabled by default.
     *
     * @return `true` if users can scroll the ViewPager2, `false` otherwise
     * @see .setUserInputEnabled
     */
    fun isUserInputEnabled(): Boolean {
        return mUserInputEnabled
    }
}