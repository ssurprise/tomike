package com.skx.tomike.viewlaboratory.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
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
public class CanvasTestView extends View {

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
     * Shape is a ring.
     */
    public static final int RING = 3;


    private final Paint mFillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mStrokePaint;   // optional, set by the caller

    @Shape
    private int mShape;

    private ColorStateList mSolidColor;
    private ColorStateList mStrokeColor;
    private float mStrokeWidth;
    private float mRadius;

    @IntDef({RECTANGLE, TRIANGLE, CIRCLE, RING,})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Shape {
    }


    public CanvasTestView(Context context) {
        this(context, null);
    }

    public CanvasTestView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasTestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CanvasTestView);

        mShape = array.getInt(R.styleable.CanvasTestView_shape, 0);
        // 类似的可查看TextView 的颜色设置
        mSolidColor = array.getColorStateList(R.styleable.CanvasTestView_solid_color);
        mStrokeColor = array.getColorStateList(R.styleable.CanvasTestView_stroke_color);
        // 参考 CircularProgressLayout.java:154
        mStrokeWidth = array.getDimensionPixelSize(R.styleable.CanvasTestView_stroke_width, 30);
        // 类似的可查看CardView 的 CardView_cardCornerRadius 设置
        mRadius = array.getDimension(R.styleable.CanvasTestView_radius, 0.0F);

        array.recycle();

        // 设置填充色
        mFillPaint.setAntiAlias(true);// 消除锯齿
        mFillPaint.setStyle(Paint.Style.FILL);
        mFillPaint.setColor(mSolidColor.getDefaultColor());

        // 设置描边色

        if (mStrokePaint == null) {
            mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mStrokePaint.setStyle(Paint.Style.STROKE);
            mStrokePaint.setAntiAlias(true);// 消除锯齿
            mStrokePaint.setStrokeWidth(mStrokeWidth);
            mStrokePaint.setColor(mStrokeColor.getDefaultColor());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (mShape) {
            case RECTANGLE:
                if (mStrokeWidth > 0 && mStrokePaint != null) {
                    canvas.drawRect(getPaddingLeft(), getPaddingTop(), getRight() - getPaddingRight(), getBottom() - getPaddingBottom(), mStrokePaint);

                } else {
                    canvas.drawRect(getPaddingLeft(), getPaddingTop(), getWidth() - getPaddingRight(), getHeight() - getPaddingBottom(), mFillPaint);
                }
                break;
            case TRIANGLE:
                break;
            case CIRCLE:
                canvas.drawCircle(getWidth() >> 1, getHeight() >> 1, mRadius, mFillPaint);
                break;
            case RING:
                break;
        }
    }
}
