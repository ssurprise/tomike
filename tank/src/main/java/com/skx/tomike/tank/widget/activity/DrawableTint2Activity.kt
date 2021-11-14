package com.skx.tomike.tank.widget.activity

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.common.utils.tintListDrawable
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_TINT2

/**
 * 着色测试2
 *
 *
 * 1.xml 着色
 * android:background="@drawable/ drawable选择器"
 * app:backgroundTint="@color/ 颜色选择器"
 *
 * 如果是AppCompatButton AppCompatTextView 只需要设置 app:backgroundTint="@color/ 颜色选择器"
 *
 *
 * 2.code 着色
 * setBackgroundTintList（colorStateListSelector）
 * setSupportBackgroundTintList（colorStateListSelector）
 */
@Route(path = ROUTE_PATH_TINT2)
class DrawableTint2Activity : SkxBaseActivity<BaseViewModel?>() {

    private val mIvCodeBg: ImageView by lazy {
        findViewById(R.id.mIv_tint2_code_bg)
    }
    private val mBtnCodeBg by lazy {
        findViewById<Button>(R.id.btn_tint2_code_bg)
    }
    private val mAcBtnCodeBg: AppCompatButton by lazy {
        findViewById(R.id.acbtn_tent2_code_bg)
    }
    private val mIvCodeSelector by lazy {
        findViewById<ImageView>(R.id.iv_tint2_code_selector).also {
            it.isClickable = true
        }
    }
    private val mBtnCodeSelector: Button by lazy {
        findViewById(R.id.btn_tint2_code_selector)
    }
    private val mAcBtnCodeSelector: AppCompatButton by lazy {
        findViewById(R.id.acbtn_tint2_code_selector)
    }

    override fun initParams() {}

    override fun getLayoutId(): Int {
        return R.layout.activity_drawable_compat_tint2
    }

    override fun configHeaderTitle(): TitleConfig {
        return TitleConfig.Builder().setTitleText("着色Tint").create()
    }

    override fun initView() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        renderView()
    }

    private fun renderView() {
        ContextCompat.getDrawable(this, R.drawable.icon_beijing)?.run {
            val colorStateList = ColorStateList.valueOf(Color.parseColor("#3396a8"))
            ViewCompat.setBackground(mIvCodeBg, tintListDrawable(this, colorStateList))
        }

        ContextCompat.getDrawable(this, R.drawable.icon_beijing)?.run {
            val colorStateList2 = ColorStateList.valueOf(Color.parseColor("#866393"))
            ViewCompat.setBackground(mBtnCodeBg, tintListDrawable(this, colorStateList2))
        }
        val bgDrawable4 = ContextCompat.getDrawable(this, R.drawable.icon_beijing)
        ViewCompat.setBackground(mAcBtnCodeBg, bgDrawable4)

//--------------------------------------分割线-------------------------------------------------------

        val selectorDrawable = ContextCompat.getDrawable(this, R.drawable.selector_drawable_button)
        val colorStateListSelector = ContextCompat.getColorStateList(this, R.color.selector_color_button)

        // ImageView 动态设置 selector
        selectorDrawable?.run {
            ViewCompat.setBackground(mIvCodeSelector,
                    tintListDrawable(this, colorStateListSelector!!))
        }


        // Button 动态设置selector
        val selectorDrawable2 = ContextCompat.getDrawable(this, R.drawable.selector_drawable_button)
        selectorDrawable2?.run {
            ViewCompat.setBackground(mBtnCodeSelector,
                    tintListDrawable(this, colorStateListSelector!!))
        }


        // AppCompatButton 动态设置selector
        val bgDrawable = ContextCompat.getDrawable(this, R.drawable.icon_beijing)
        mAcBtnCodeSelector.background = bgDrawable
        mAcBtnCodeSelector.backgroundTintList = colorStateListSelector
    }
}