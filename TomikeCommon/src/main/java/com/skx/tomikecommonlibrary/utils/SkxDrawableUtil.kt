package com.skx.tomikecommonlibrary.utils

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.os.Build
import androidx.annotation.ColorInt
import androidx.annotation.IntDef
import androidx.core.graphics.drawable.DrawableCompat

/**
 * Drawable 工具类
 *
 * @author shiguotao
 */
class SkxDrawableUtil {

    // --------------------------------------------- 构造器模式 -------------------------------------------------

    /**
     * 获取建造者
     *
     * @param shape 图形
     * @return [Builder]
     */
    fun getBuilder(shape: Int): Builder {
        return Builder(shape)
    }

    /**
     * Drawable 建造者，链式结构，方便使用
     */
    class Builder constructor(val shape: Int) {


        private var mSolidColors: ColorStateList? = null

        /**
         * The color state list of the stroke
         */
        private var mStrokeColors: ColorStateList? = null

        @ColorInt
        private var mGradientColors: IntArray? = null

        @GradientType
        private var mGradient = LINEAR_GRADIENT
        private var mOrientation: GradientDrawable.Orientation? = null

        /**
         * The width in pixels of the stroke
         */
        private var mStrokeWidth = -1 // if >= 0 use stroking.

        /**
         * The length in pixels of the dashes, set to 0 to disable dashes
         */
        private var mStrokeDashWidth = 0.0f

        /**
         * The gap in pixels between dashes
         */
        private var mStrokeDashGap = 0.0f

        private var mRadius = 0.0f // use this if mRadiusArray is null
        private var mRadiusArray: FloatArray? = null

        internal var mGradientDrawable: GradientDrawable

        /**
         * @hide
         */
        @IntDef(LINEAR_GRADIENT, RADIAL_GRADIENT, SWEEP_GRADIENT)
        @kotlin.annotation.Retention(AnnotationRetention.SOURCE)
        annotation class GradientType

        init {
            mGradientDrawable = GradientDrawable()
            mGradientDrawable.shape = if (shape == RECTANGLE || shape == OVAL || shape == LINE) shape else RECTANGLE
        }

        /**
         * Changes this drawable to use a single color instead of a gradient.
         *
         *
         * **Note**: changing color will affect all instances of a
         * drawable loaded from a resource. It is recommended to invoke
         * [GradientDrawable.mutate()][.] before changing the color.
         *
         * @param argb The color used to fill the shape
         */
        fun setColor(@ColorInt argb: Int): Builder {
            this.mSolidColors = ColorStateList.valueOf(argb)
            this.mGradientColors = null
            return this
        }

        /**
         * Changes this drawable to use a single color state list instead of a
         * gradient. Calling this method with a null argument will clear the color
         * and is equivalent to calling [.setColor] with the argument
         * [Color.TRANSPARENT].
         *
         *
         * **Note**: changing color will affect all instances of a
         * drawable loaded from a resource. It is recommended to invoke
         * [GradientDrawable.mutate()()][.] before changing the color.
         *
         * @param colorStateList The color state list used to fill the shape
         */
        fun setColor(colorStateList: ColorStateList?): Builder {
            this.mSolidColors = colorStateList
            this.mGradientColors = null
            return this
        }

        /**
         * Sets the colors used to draw the gradient.
         *
         *
         * Each color is specified as an ARGB integer and the array must contain at
         * least 2 colors.
         *
         *
         * **Note**: changing colors will affect all instances of a
         * drawable loaded from a resource. It is recommended to invoke
         * [GradientDrawable.mutate()][.] before changing the colors.
         *
         * @param colors an array containing 2 or more ARGB colors
         * @see . mutate
         * @see .setColor
         */
        fun setGradientColors(colors: IntArray?): Builder {
            this.mGradientColors = colors
            this.mSolidColors = null
            return this
        }

        /**
         * Sets the type of gradient used by this drawable.
         *
         *
         * **Note**: changing this property will affect all instances
         * of a drawable loaded from a resource. It is recommended to invoke
         */
        fun setGradientType(@GradientType gradient: Int): Builder {
            this.mGradient = gradient
            return this
        }

        /**
         * Sets the orientation of the gradient defined in this drawable.
         *
         *
         * **Note**: changing orientation will affect all instances
         * of a drawable loaded from a resource. It is recommended to invoke
         */
        fun setOrientation(orientation: GradientDrawable.Orientation): Builder {
            this.mOrientation = orientation
            return this
        }

        /**
         *
         * Set the stroke width and color for the drawable. If width is zero,
         * then no stroke is drawn.
         *
         * **Note**: changing this property will affect all instances
         * of a drawable loaded from a resource. It is recommended to invoke
         * [mutate()][.] before changing this property.
         *
         * @param width The width in pixels of the stroke
         * @param color The color of the stroke
         * @see . mutate
         * @see .setStroke
         */
        fun setStroke(width: Int, @ColorInt color: Int): Builder {
            this.mStrokeWidth = width
            this.mStrokeColors = ColorStateList.valueOf(color)
            return this
        }

        /**
         *
         * Set the stroke width and color state list for the drawable. If width
         * is zero, then no stroke is drawn.
         *
         * **Note**: changing this property will affect all instances
         * of a drawable loaded from a resource. It is recommended to invoke
         * [mutate()][.] before changing this property.
         *
         * @param width          The width in pixels of the stroke
         * @param colorStateList The color state list of the stroke
         * @see .setStroke
         */
        fun setStroke(width: Int, colorStateList: ColorStateList): Builder {
            this.mStrokeWidth = width
            this.mStrokeColors = colorStateList
            return this
        }

        /**
         *
         * Set the stroke width and color for the drawable. If width is zero,
         * then no stroke is drawn. This method can also be used to dash the stroke.
         *
         * **Note**: changing this property will affect all instances
         * of a drawable loaded from a resource. It is recommended to invoke
         * [mutate()][.] before changing this property.
         *
         * @param width     The width in pixels of the stroke
         * @param color     The color of the stroke
         * @param dashWidth The length in pixels of the dashes, set to 0 to disable dashes
         * @param dashGap   The gap in pixels between dashes
         * @see .setStroke
         */
        fun setStroke(width: Int, @ColorInt color: Int, dashWidth: Float, dashGap: Float): Builder {
            this.mStrokeWidth = width
            this.mStrokeColors = ColorStateList.valueOf(color)
            this.mStrokeDashWidth = dashWidth
            this.mStrokeDashGap = dashGap
            return this
        }

        /**
         *
         * Set the stroke width and color state list for the drawable. If width
         * is zero, then no stroke is drawn. This method can also be used to dash
         * the stroke.
         *
         * **Note**: changing this property will affect all instances
         * of a drawable loaded from a resource. It is recommended to invoke
         * [mutate()][.] before changing this property.
         *
         * @param width          The width in pixels of the stroke
         * @param colorStateList The color state list of the stroke
         * @param dashWidth      The length in pixels of the dashes, set to 0 to disable dashes
         * @param dashGap        The gap in pixels between dashes
         * @see .setStroke
         */
        fun setStroke(width: Int, colorStateList: ColorStateList, dashWidth: Float, dashGap: Float): Builder {
            this.mStrokeWidth = width
            this.mStrokeColors = colorStateList
            this.mStrokeDashWidth = dashWidth
            this.mStrokeDashGap = dashGap
            return this
        }

        fun setCornerRadius(radius: Float): Builder {
            this.mRadius = if (radius < 0) 0f else radius
            this.mRadiusArray = null
            return this
        }

        /**
         * Specifies radii for each of the 4 corners. For each corner, the array
         * contains 2 values, `[X_radius, Y_radius]`. The corners are
         * ordered top-left, top-right, bottom-right, bottom-left. This property
         * is honored only when the shape is of type [.RECTANGLE].
         *
         *
         * **Note**: changing this property will affect all instances
         * of a drawable loaded from a resource. It is recommended to invoke
         * [mutate()][.] before changing this property.
         *
         * @param radii an array of length >= 8 containing 4 pairs of X and Y
         * radius for each corner, specified in pixels
         * @see .setCornerRadius
         */
        fun setCornerRadii(radii: FloatArray?): Builder {
            this.mRadiusArray = radii
            if (radii == null) {
                this.mRadius = 0f
            }
            return this
        }

        fun create(): Drawable {
            // 圆角
            if (mRadiusArray != null) {
                mGradientDrawable.cornerRadii = mRadiusArray

            } else if (mRadius > 0.0f) {
                mGradientDrawable.cornerRadius = mRadius
            }

            // 填充色
            if (mSolidColors != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mGradientDrawable.color = mSolidColors
                } else {
                    mGradientDrawable.setColor(mSolidColors!!.defaultColor)
                }

            } else if (mGradientColors != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    mGradientDrawable.colors = mGradientColors
                    mGradientDrawable.gradientType = mGradient
                    mGradientDrawable.orientation = mOrientation
                }
            } else {
                mGradientDrawable.setColor(Color.TRANSPARENT)
            }

            // 描边
            if (mStrokeColors != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    //显示一条虚线，破折线的宽度为dashWith，破折线之间的空隙的宽度为dashGap，当dashGap=0dp时，为实线
                    mGradientDrawable.setStroke(mStrokeWidth, mStrokeColors, mStrokeDashWidth, mStrokeDashGap)
                } else {
                    //显示一条虚线，破折线的宽度为dashWith，破折线之间的空隙的宽度为dashGap，当dashGap=0dp时，为实线
                    mGradientDrawable.setStroke(mStrokeWidth, mStrokeColors!!.defaultColor, mStrokeDashWidth, mStrokeDashGap)
                }
            }

            return mGradientDrawable
        }

        companion object {
            /**
             * Shape is a rectangle, possibly with rounded corners
             */
            const val RECTANGLE = GradientDrawable.RECTANGLE

            /**
             * Shape is an ellipse
             */
            const val OVAL = GradientDrawable.OVAL

            /**
             * Shape is a line
             */
            const val LINE = GradientDrawable.LINE

            /**
             * Gradient is linear (default.)
             */
            const val LINEAR_GRADIENT = GradientDrawable.LINEAR_GRADIENT

            /**
             * Gradient is circular.
             */
            const val RADIAL_GRADIENT = GradientDrawable.RADIAL_GRADIENT

            /**
             * Gradient is a sweep.
             */
            const val SWEEP_GRADIENT = GradientDrawable.SWEEP_GRADIENT
        }
    }
}


//----------------------------------------------- Drawable 着色 start -----------------------------------------------------------------

/**
 * 对目标Drawable 进行着色
 *
 * @param drawable 目标Drawable
 * @param color    着色的颜色值
 * @return 着色处理后的Drawable
 */
fun tintDrawable(drawable: Drawable, color: Int): Drawable {
    // 获取此drawable的共享状态实例
    val wrappedDrawable = getCanTintDrawable(drawable)
    // 对 drawable 进行着色
    DrawableCompat.setTint(wrappedDrawable, color)
    return wrappedDrawable
}

/**
 * 对目标Drawable 进行着色
 *
 * @param drawable 目标Drawable
 * @param colors   着色值
 * @return 着色处理后的Drawable
 */
fun tintListDrawable(drawable: Drawable, colors: ColorStateList): Drawable {
    val wrappedDrawable = getCanTintDrawable(drawable)
    // 对 drawable 进行着色
    DrawableCompat.setTintList(wrappedDrawable, colors)
    return wrappedDrawable
}

/**
 * 获取可以进行tint 的Drawable
 *
 *
 * 对原drawable进行重新实例化  newDrawable()
 * 包装  warp()
 * 可变操作 mutate()
 *
 * @param drawable 原始drawable
 * @return 可着色的drawable
 */
private fun getCanTintDrawable(drawable: Drawable): Drawable {
    // 获取此drawable的共享状态实例
    val state = drawable.constantState
    // 对drawable 进行重新实例化、包装、可变操作
    return DrawableCompat.wrap(if (state == null) drawable else state.newDrawable()).mutate()
}

//------------------------------------------------ Drawable 着色 end -----------------------------------------------------------------

//----------------------------------------------------- Drawable 选择器 start-----------------------------------------------------------

/**
 * 获得一个选择器Drawable.
 * Android 中 在xml中写的"selector"标签映射对象就是StateListDrawable 对象
 *
 * @param defaultDrawable 默认时显示的Drawable
 * @param pressedDrawable 按下时显示的Drawable
 * @return 选择器Drawable
 */
private fun getSelectorDrawable(defaultDrawable: Drawable?, pressedDrawable: Drawable?): StateListDrawable? {
    var pressedDrawable = pressedDrawable
    if (defaultDrawable == null) return null
    if (pressedDrawable == null) pressedDrawable = defaultDrawable
    val state = arrayOf(intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_pressed),
            intArrayOf(android.R.attr.state_enabled, android.R.attr.state_pressed))
    val stateListDrawable = StateListDrawable()
    stateListDrawable.addState(state[0], defaultDrawable)
    stateListDrawable.addState(state[1], pressedDrawable)
    return stateListDrawable
}

/**
 * 获得一个选择器Drawable.
 * Android 中 在xml中写的"selector"标签映射对象就是StateListDrawable 对象
 *
 * @param defaultColor 默认时显示的颜色
 * @param pressedColor 按下时显示的颜色
 * @param radius       圆角半径
 * @return 选择器Drawable
 */
fun getSelectorDrawable(defaultColor: Int, pressedColor: Int, radius: Float): StateListDrawable? {

    val defaultDrawable = SkxDrawableUtil.Builder(SkxDrawableUtil.Builder.RECTANGLE).setColor(defaultColor).setCornerRadius(radius).create()
    val pressedDrawable = SkxDrawableUtil.Builder(SkxDrawableUtil.Builder.RECTANGLE).setColor(pressedColor).setCornerRadius(radius).create()

    return getSelectorDrawable(defaultDrawable, pressedDrawable)
}

//----------------------------------------------------- Drawable 选择器 end---------------------------------------------------------------

