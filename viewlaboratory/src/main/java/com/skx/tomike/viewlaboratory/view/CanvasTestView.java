package com.skx.tomike.viewlaboratory.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.skx.tomike.viewlaboratory.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class CanvasTestView extends View {

    /**
     * Shape is a rectangle, possibly with rounded corners
     */
    public static final int RECTANGLE = 0;

    /**
     * Shape is an ellipse
     */
    public static final int OVAL = 1;

    /**
     * Shape is a line
     */
    public static final int LINE = 2;

    /**
     * Shape is a ring.
     */
    public static final int RING = 3;

    private final Paint mFillPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mStrokePaint;   // optional, set by the caller

    private int mShape;
    @ColorInt
    private int mSolidColor;
    @ColorInt
    private int mStrokeColor;
    private int mStrokeWidth;
    private int mRadius;

    @IntDef({RECTANGLE, OVAL, LINE, RING})
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
        mSolidColor = array.getColor(R.styleable.CanvasTestView_solid, Color.TRANSPARENT);
        mStrokeColor = array.getColor(R.styleable.CanvasTestView_stroke, Color.TRANSPARENT);
        mStrokeWidth = array.getInt(R.styleable.CanvasTestView_stroke_width, -1);
        mRadius = array.getInt(R.styleable.CanvasTestView_radius, 0);

        array.recycle();

        mFillPaint.setColor(mSolidColor);
        if (mStrokeWidth > 0) {
            mStrokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 120, mFillPaint);
    }
}
