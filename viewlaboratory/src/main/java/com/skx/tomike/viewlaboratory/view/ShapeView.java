package com.skx.tomike.viewlaboratory.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.skx.tomike.viewlaboratory.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 描述 : 自定义View demo类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2019-09-19 17:34
 */
public class ShapeView extends View {

    /**
     * Shape is a rectangle, possibly with rounded corners
     */
    public static final int RECTANGLE = 0;
    /**
     * Shape is a triangle.
     */
    public static final int TRIANGLE = 1;

    /**
     * Shape is an ellipse
     */
    public static final int CIRCLE = 2;
    /**
     * Shape is an oval
     */
    public static final int OVAL = 3;

    /**
     * Shape is a ring.
     */
    public static final int RING = 4;


    private final Paint mSolidPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mStrokePaint;   // optional, set by the caller

    @Shape
    private int mShape;

    private final Path mPath = new Path();
    private final RectF mRect = new RectF();

    private ColorStateList mSolidColor;
    private ColorStateList mStrokeColor;
    private float mStrokeWidth; // if >= 0 use stroking.
    private float mStrokeDashWidth = 0.0f;
    private float mStrokeDashGap = 0.0f;

    private float mRadius = 0.0f; // use this if mRadiusArray is null
    private float[] mRadiusArray = null;

    @IntDef({RECTANGLE, TRIANGLE, CIRCLE, OVAL, RING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Shape {
    }


    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ShapeView);

        mShape = array.getInt(R.styleable.ShapeView_shape, 0);
        // 类似的可查看TextView 的颜色设置
        mSolidColor = array.getColorStateList(R.styleable.ShapeView_solid_color);
        mStrokeColor = array.getColorStateList(R.styleable.ShapeView_stroke_color);
        // 参考 CircularProgressLayout.java:154
        mStrokeWidth = array.getDimensionPixelSize(R.styleable.ShapeView_stroke_width, 0);
        // 类似的可查看CardView 的 CardView_cardCornerRadius 设置
        mRadius = array.getDimension(R.styleable.ShapeView_radius, 0.0F);

        array.recycle();

        // 设置填充色
        mSolidPaint.setAntiAlias(true);// 消除锯齿
        mSolidPaint.setStyle(Paint.Style.FILL);
        mSolidPaint.setColor(mSolidColor != null ? mSolidColor.getDefaultColor() : Color.TRANSPARENT);

        // 设置描边色
        if (mStrokePaint == null) {
            mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }
        mStrokePaint.setStyle(Paint.Style.STROKE);
        mStrokePaint.setAntiAlias(true);// 消除锯齿
        mStrokePaint.setStrokeWidth(mStrokeWidth);
        mStrokePaint.setColor(mStrokeColor != null ? mStrokeColor.getDefaultColor() : Color.TRANSPARENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);

    }

    public void setCornerRadius(float radius) {
        if (radius < 0) {
            radius = 0;
        }
        mRadius = radius;
        mRadiusArray = null;
    }

    public void setCornerRadii(float[] radii) {
        mRadiusArray = radii;
        if (radii == null) {
            mRadius = 0;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final boolean haveStroke = mStrokePaint != null && mStrokePaint.getStrokeWidth() > 0;

        mRect.set(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom());
        switch (mShape) {
            case RECTANGLE:
                if (mRadiusArray != null) {
//                    canvas.drawPath();

                } else if (mRadius > 0.0f) {
                    float rad = Math.min(mRadius,
                            Math.min(mRect.width(), mRect.height()) * 0.5f);
                    canvas.drawRoundRect(mRect, rad, rad, mSolidPaint);
                    if (haveStroke) {
                        canvas.drawRoundRect(mRect, rad, rad, mStrokePaint);
                    }

                } else {
                    if (mSolidPaint.getColor() != 0) {
                        canvas.drawRect(mRect, mSolidPaint);
                    }
                    if (haveStroke) {
                        canvas.drawRect(mRect, mStrokePaint);
                    }
                }
                break;
            case TRIANGLE:
                break;
            case CIRCLE:
                float rad = Math.min(mRadius,
                        Math.min(mRect.width(), mRect.height()) * 0.5f);
                float posX = getPaddingLeft() + (mRect.width() / 2);
                float posY = getPaddingTop() + (mRect.height() / 2);
                canvas.drawCircle(posX, posY, rad, mSolidPaint);

                if (haveStroke) {
                    canvas.drawCircle(posX, posY, rad, mStrokePaint);
                }

                break;
            case OVAL:
                canvas.drawOval(mRect, mSolidPaint);
                if (haveStroke) {
                    canvas.drawOval(mRect, mStrokePaint);
                }
                break;
            case RING:
                break;
            default:
                break;
        }
    }
}
