package com.skx.tomike.cannon.ui.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.PopupWindow
import android.widget.RelativeLayout
import androidx.core.view.ViewCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.common.utils.SkxDrawableUtil
import com.skx.tomike.cannon.R
import com.skx.tomike.cannon.ROUTE_PATH_POPWINDOW

@Route(path = ROUTE_PATH_POPWINDOW)
class PopupWindowActivity : SkxBaseActivity<BaseViewModel>(), View.OnClickListener {


    override fun initParams() {
    }

    override fun layoutId(): Int {
        return R.layout.activity_popupwindow
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("PopWindow demo").create()
    }

    override fun initView() {
        findViewById<Button>(R.id.popupWindow_btn1).also {
            it.setOnClickListener(this)
        }
        findViewById<Button>(R.id.popupWindow_btn2).also {
            it.setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.popupWindow_btn1 -> showPopupWindow(v)
            R.id.popupWindow_btn2 -> showPopupWindow2(v)
        }
    }

    private fun showPopupWindow(view: View?) {
        val contentView = LayoutInflater.from(this)
                .inflate(R.layout.popupwindow_test, null)
        ViewCompat.setBackground(contentView,
                SkxDrawableUtil().getBuilder(SkxDrawableUtil.Builder.RECTANGLE)
                        .setColor(Color.parseColor("#2cb298"))
                        .setStroke(Color.parseColor("#30c3a6"), 3)
                        .setCornerRadius(3f)
                        .create())
        // 这里最后一个参数设置成false,点击其他区域，popupWindow 不会消失，返回键也无效。
        // 只要给popupWindow 设置了背景，可以返回，点击其他区域无效
        // 设置成true, 没有设置背景，按返回键 和 点击其他区域无效
        val popupWindow = PopupWindow(contentView,
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                true)

        // popupWindow 需要设置背景  返回按钮才生效，否则返回按键不生效
        val dw = ColorDrawable(0x00000000)
        popupWindow.setBackgroundDrawable(dw)
        // PopupWindow 进入进出动画
        // popupWindow.setAnimationStyle(R.style.popup_window_anim_style);
        popupWindow.showAsDropDown(view)
    }

    private fun showPopupWindow2(view: View) {
        val contentView = LayoutInflater.from(this)
                .inflate(R.layout.popupwindow_test, null)
        ViewCompat.setBackground(contentView,
                SkxDrawableUtil().getBuilder(SkxDrawableUtil.Builder.RECTANGLE)
                        .setColor(Color.parseColor("#2cb298"))
                        .setStroke(Color.parseColor("#30c3a6"), 3)
                        .setCornerRadius(3f)
                        .create())
        // 这里最后一个参数设置成false,点击其他区域，popupWindow 不会消失，返回键也无效。只要给popupWindow 设置了背景，可以返回，点击其他区域无效
        // 设置成true, 没有设置背景，按返回键 和 点击其他区域无效
        val popupWindow = PopupWindow(contentView,
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                true)

        // popupWindow 需要设置背景  返回按钮才生效，否则返回按键不生效
        val dw = ColorDrawable(0x00000000)
        popupWindow.setBackgroundDrawable(dw)
        // PopupWindow 进入进出动画
//        popupWindow.animationStyle = R.style.popup_window_anim_style
        popupWindow.showAsDropDown(view,
                0,
                -(view.measuredHeight + 460),
                Gravity.START)
    }
}