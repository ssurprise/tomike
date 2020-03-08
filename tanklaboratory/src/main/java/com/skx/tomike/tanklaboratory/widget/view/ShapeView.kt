package com.skx.tomike.tanklaboratory.widget.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.IntDef
import com.skx.tomike.tanklaboratory.R
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * 描述 : 提供基本图形的View。包括：矩形、圆角矩形、圆、椭圆
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019-09-19 17:34
 */
class ShapeView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr) {


    private val mSolidPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mStrokePaint: Paint? = null   // optional, set by the caller

    @Shape
    private val mShape: Int

    private val mPath = Path()
    private val mRect = RectF()

    private val mSolidColor: ColorStateList?
    private val mStrokeColor: ColorStateList?
    private val mStrokeWidth: Float // if >= 0 use stroking.
    private val mStrokeDashWidth = 0.0f
    private val mStrokeDashGap = 0.0f

    private var mStartAngle: Float = 0.0f
    private var mSweepAngle: Float = 0.0f

    private var mRadius = 0.0f // use this if mRadiusArray is null
    private var mRadiusArray: FloatArray? = null

    @IntDef(RECTANGLE, TRIANGLE, CIRCLE, OVAL, ARC)
    @Retention(RetentionPolicy.SOURCE)
    annotation class Shape

    init {
        val array = context.obtainStyledAttributes(attrs, R.styleable.ShapeView)

        mShape = array.getInt(R.styleable.ShapeView_shape, 0)
        // 类似的可查看TextView 的颜色设置
        mSolidColor = array.getColorStateList(R.styleable.ShapeView_solid_color)
        mStrokeColor = array.getColorStateList(R.styleable.ShapeView_stroke_color)
        // 参考 CircularProgressLayout.java:154
        mStrokeWidth = array.getDimensionPixelSize(R.styleable.ShapeView_stroke_width, 0).toFloat()
        // 类似的可查看CardView 的 CardView_cardCornerRadius 设置
        mRadius = array.getDimension(R.styleable.ShapeView_radius, 0.0f)

        mStartAngle = array.getFloat(R.styleable.ShapeView_arc_startAngle, 0.0f)
        mSweepAngle = array.getFloat(R.styleable.ShapeView_arc_sweepAngle, 0.0f)

        array.recycle()

        // 设置填充色
        mSolidPaint.isAntiAlias = true// 消除锯齿
        mSolidPaint.style = Paint.Style.FILL
        mSolidPaint.color = mSolidColor?.defaultColor ?: Color.TRANSPARENT

        // 设置描边色
        if (mStrokePaint == null) {
            mStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        }
        mStrokePaint?.style = Paint.Style.STROKE
        mStrokePaint?.isAntiAlias = true// 消除锯齿
        mStrokePaint?.strokeWidth = mStrokeWidth
        mStrokePaint?.color = mStrokeColor?.defaultColor ?: Color.TRANSPARENT
    }

    fun setCornerRadius(radius: Float) {
        var radius = radius
        if (radius < 0) {
            radius = 0f
        }
        mRadius = radius
        mRadiusArray = null
        invalidate()
    }

    fun setCornerRadii(radii: FloatArray?) {
        mRadiusArray = radii
        if (radii == null) {
            mRadius = 0f
        }
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val haveStroke = mStrokePaint != null && mStrokePaint!!.color != 0 && mStrokePaint!!.strokeWidth > 0

        mRect.set(paddingLeft.toFloat(), paddingTop.toFloat(), (width - paddingRight).toFloat(), (height - paddingBottom).toFloat())
        when (mShape) {
            RECTANGLE -> if (mRadiusArray != null) {
                //                    canvas.drawPath();

            } else if (mRadius > 0.0f) {
                val rad = Math.min(mRadius,
                        Math.min(mRect.width(), mRect.height()) * 0.5f)
                canvas.drawRoundRect(mRect, rad, rad, mSolidPaint)
                if (haveStroke) {
                    canvas.drawRoundRect(mRect, rad, rad, mStrokePaint!!)
                }

            } else {
                if (mSolidPaint.color != 0) {
                    canvas.drawRect(mRect, mSolidPaint)
                }
                if (haveStroke) {
                    canvas.drawRect(mRect, mStrokePaint!!)
                }
            }
            TRIANGLE -> {
            }
            CIRCLE -> {
                val rad = Math.min(mRadius,
                        Math.min(mRect.width(), mRect.height()) * 0.5f)
                val posX = paddingLeft + mRect.width() / 2
                val posY = paddingTop + mRect.height() / 2
                if (mSolidPaint.color != 0) {
                    canvas.drawCircle(posX, posY, rad, mSolidPaint)
                }

                if (haveStroke) {
                    canvas.drawCircle(posX, posY, rad, mStrokePaint!!)
                }
            }
            OVAL -> {
                if (mSolidPaint.color != 0) {
                    canvas.drawOval(mRect, mSolidPaint)
                }
                if (haveStroke) {
                    canvas.drawOval(mRect, mStrokePaint!!)
                }
            }
            ARC -> {
                if (mSolidPaint.color != 0) {
                    canvas.drawArc(mRect, mStartAngle, mSweepAngle, true, mSolidPaint)
                }
                if (haveStroke) {
                    canvas.drawArc(mRect, mStartAngle, mSweepAngle, true, mStrokePaint!!)
                }
            }
            else -> {
            }
        }
    }

    companion object {

        /**
         * Shape is a rectangle, possibly with rounded corners
         */
        const val RECTANGLE = 0
        /**
         * Shape is a triangle.
         */
        const val TRIANGLE = 1

        /**
         * Shape is an ellipse
         */
        const val CIRCLE = 2
        /**
         * Shape is an oval
         */
        const val OVAL = 3


        const val ARC = 4
    }
}
