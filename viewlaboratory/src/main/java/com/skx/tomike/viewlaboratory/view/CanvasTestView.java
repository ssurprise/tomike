package com.skx.tomike.viewlaboratory.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.skx.tomike.viewlaboratory.R;

public class CanvasTestView extends View {

    private Paint mPaint;

    private int mShape;
    @ColorInt
    private int mSolidColor;
    @ColorInt
    private int mStrokeColor;
    private int mStrokeWidth;
    private int mRadius;


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

        mPaint = new Paint();
        mPaint.setColor(getResources().getColor(R.color.skx_3a9173));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 120, mPaint);
    }
}
