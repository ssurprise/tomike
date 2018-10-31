package com.skx.tomike.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.skx.tomikecommonlibrary.utils.DpPxSpTool;

/**
 * Created by shiguotao on 2017/7/25.
 * <p>
 * 点评分数指示器
 */
public class ScoreView extends View {

    private int pointWidth = 2;
    private int pointHeight = 2;
    private int pointSpace = 4;
    private float indicatorHeight = 15.0f;
    private int pointCount = 20;
    private int indicatorPos = 10;

    Paint defaultPaint;
    Paint processPaint;

    public ScoreView(Context context) {
        this(context, null);
    }

    public ScoreView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        defaultPaint = new Paint();
        defaultPaint.setColor(Color.parseColor("#BDBDBD"));
//        defaultPaint.setStrokeWidth(6);

        processPaint = new Paint();
        processPaint.setColor(Color.parseColor("#FF4081"));
//        processPaint.setStrokeWidth(6);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {//MATCH_PARENT
            width = widthSize;

        } else {
            int pointTotalWidth = DpPxSpTool.dip2px(this.getContext(), pointWidth) * pointCount;
            int pointSpaceTotalWidth = DpPxSpTool.dip2px(this.getContext(), pointSpace) * (pointCount - 1);
            width = getPaddingLeft() + pointTotalWidth + pointSpaceTotalWidth + getPaddingRight();
        }

        if (heightMode == MeasureSpec.EXACTLY) {//MATCH_PARENT
            height = heightSize;

        } else {
            height = DpPxSpTool.dip2px(this.getContext(), indicatorHeight);
        }

        setMeasuredDimension(width, height);
    }

    /**
     * 设置指示器位置
     *
     * @param indicatorPos
     */
    public void setIndicatorPos(int indicatorPos) {
        if (indicatorPos > pointCount) {
            indicatorPos = pointCount;

        } else if (indicatorPos < 0) {
            indicatorPos = 0;
        }
        this.indicatorPos = indicatorPos;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int pointSpacePix = DpPxSpTool.dip2px(this.getContext(), pointSpace);
        int pointWidthPix = DpPxSpTool.dip2px(this.getContext(), pointWidth);
        int pointHeightPix = DpPxSpTool.dip2px(this.getContext(), pointHeight);
        int pointTopPos = DpPxSpTool.dip2px(this.getContext(), indicatorHeight - pointHeight) / 2;

        // 画背景层
        for (int i = 0, j = pointCount - 1; i <= j; i++) {
            int topLeft = i * (pointSpacePix + pointWidthPix);
            canvas.drawRect(topLeft, pointTopPos, topLeft + pointHeightPix, pointTopPos + pointWidthPix, defaultPaint);
        }

        // 画进度层
        for (int i = 0, j = indicatorPos - 1; i <= j; i++) {
            int topLeft = i * (pointSpacePix + pointWidthPix);
            canvas.drawRect(topLeft, pointTopPos, topLeft + pointHeightPix, pointTopPos + pointWidthPix, processPaint);
        }

        // 画指示器
        canvas.drawRect(indicatorPos * (pointSpacePix + pointWidthPix), 0,
                indicatorPos * (pointSpacePix + pointWidthPix) + pointWidthPix,
                DpPxSpTool.dip2px(this.getContext(), indicatorHeight), processPaint);

    }
}
