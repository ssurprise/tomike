package com.skx.tomike.tank.graphics

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.alibaba.android.arouter.facade.annotation.Route
import com.skx.common.base.BaseViewModel
import com.skx.common.base.SkxBaseActivity
import com.skx.common.base.TitleConfig
import com.skx.common.utils.tintDrawable
import com.skx.common.utils.tintListDrawable
import com.skx.tomike.tank.R
import com.skx.tomike.tank.ROUTE_PATH_TINT

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
 *
 *
 * 通过给Drawable 设置 tint 进行着色
 * blog :http://blog.csdn.net/jm_beizi/article/details/54916965
 * <p>
 * 参考资料：
 * https://developer.android.google.cn/reference/android/support/v4/graphics/drawable/DrawableCompat.html
 * http://www.cnblogs.com/helloandroid/p/4779061.html
 * http://blog.csdn.net/u010687392/article/details/47399719
 * http://www.jianshu.com/p/8c479ed24ca8
 * <p>
 * 涉及到的知识点：
 * 1.DrawableCompat.warp  对drawable进行包装，使drawable 可以跨越任何不同版本进行着色
 * 2.mutate  使drawable 可变，可变的drawable 不与其他使用相同资源的drawable 共享
 * 3.ConstantState 享元模式
 * 4.setTint()  setTintList() 着色方法
 */
@Route(path = ROUTE_PATH_TINT)
class DrawableTintActivity : SkxBaseActivity<BaseViewModel?>() {

    private val mIvCodeBg: ImageView by lazy {
        findViewById(R.id.mIv_tint2_code_bg)
    }
    private val mIvCodeSrc: ImageView by lazy {
        findViewById(R.id.mIv_tint2_code_src)
    }
    private val mIvCodeBgSelector by lazy {
        findViewById<ImageView>(R.id.mIv_tint2_code_bg_selector).also {
            it.isClickable = true
        }
    }
    private val mIvCodeSrcSelector by lazy {
        findViewById<ImageView>(R.id.mIv_tint2_code_src_selector).also {
            it.isClickable = true
        }
    }
    private val mAcBtnCodeSelector: AppCompatButton by lazy {
        findViewById(R.id.acbtn_tent2_code_bg)
    }

    private val mIv2BitmapBg: ImageView by lazy {
        findViewById(R.id.iv_tint_bg_transBitmap)
    }
    private val mIv2BitmapSrc: ImageView by lazy {
        findViewById(R.id.iv_tint_src_transBitmap)
    }

    override fun initParams() {}

    override fun getLayoutId(): Int {
        return R.layout.activity_drawable_tint
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
            ViewCompat.setBackground(mIvCodeBg, tintDrawable(this, Color.parseColor("#3396a8")))
        }

        ContextCompat.getDrawable(this, R.drawable.icon_beijing)?.run {
            mIvCodeSrc.setImageDrawable(tintDrawable(this, Color.parseColor("#866393")))
        }

        //--------------------------------------分割线 - selector----------------------------------------------------

        val selectorDrawable = ContextCompat.getDrawable(this, R.drawable.selector_drawable_button)
        val colorStateListSelector = ContextCompat.getColorStateList(this, R.color.selector_color_button)

        // ImageView 动态设置 selector
        selectorDrawable?.run {
            ViewCompat.setBackground(mIvCodeBgSelector,
                    tintListDrawable(this, colorStateListSelector!!))
            mIvCodeSrcSelector.setImageDrawable(tintListDrawable(this, colorStateListSelector))
        }

        //--------------------------------------分割线 - AppCompatButton-----------------------------------------------------

        ContextCompat.getDrawable(this, R.drawable.icon_beijing)?.run {
            ViewCompat.setBackground(mAcBtnCodeSelector, tintDrawable(this, Color.parseColor("#772741")))
        }

        //--------------------------------------分割线 - tint后的drawable->bitmap-----------------------------------------------------

        val originBitmapDrawable2 = ContextCompat.getDrawable(this, R.drawable.icon_beijing)
        val tintDrawable2 = tintDrawable(originBitmapDrawable2!!, Color.parseColor("#30c3a6"))
        val bitmap2 = (tintDrawable2 as BitmapDrawable).bitmap
        // 设置背景
        ViewCompat.setBackground(mIv2BitmapBg, BitmapDrawable(resources, bitmap2))
        // 设置src
        mIv2BitmapSrc.setImageBitmap(bitmap2)
    }

    /*
    mMutated 是个标签，已保证mutate 只会设置一次，也就解释了在Drawable中对mutate（）方法的一个解释，
   Calling this method on a mutable Drawable will have no effect.
   （在已经可变的drawable上调用此方法无效，因为返回的还是自己）
   */


    /*
    /**
     * Make this drawable mutable. This operation cannot be reversed. A mutable
     * drawable is guaranteed to not share its state with any other drawable.
     * This is especially useful when you need to modify properties of drawables
     * loaded from resources. By default, all drawables instances loaded from
     * the same resource share a common state; if you modify the state of one
     * instance, all the other instances will receive the same modification.
     *
     * 使这个drawable可变。 此操作无法撤消。一个可变的Drawable不会与其他Drawable共享它的状态。
     * 当你需要修改从资源加载的drawables的属性时，这是特别有用的。
     * 默认情况下，从同一资源加载的所有drawables实例都共享一个公共状态; 如果修改一个实例的状态，所有其他实例将接收相同的修改。
     *
     *
     * Calling this method on a mutable Drawable will have no effect.
     * 在可变的Drawable上调用此方法将没有效果。
     *
     * @return This drawable.
     * @see ConstantState
     * @see #getConstantState()
     */
    public @NonNull Drawable mutate() {
        return this;
    }
     */
}