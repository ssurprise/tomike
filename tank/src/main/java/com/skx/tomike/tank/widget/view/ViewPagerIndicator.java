package com.skx.tomike.tank.widget.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.skx.tomike.tank.R;

/**
 * @author shiguotao
 * <p>
 * ViewPager指示器
 */
public class ViewPagerIndicator extends LinearLayout {

    private Paint mPaint; // 画指示符的paint

    private int mTop; // 指示符的top
    private int mLeft; // 指示符的left
    private int mWidth; // 指示符的width
    private int mHeight = 5; // 指示符的高度，固定了
    private int mColor; // 指示符的颜色
    private int mChildCount; // 子item的个数，用于计算指示符的宽度

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.WHITE); // 必须设置背景，否则onDraw不执行

        // 获取自定义属性 指示符的颜色
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator, 0, 0);
        mColor = ta.getColor(R.styleable.ViewPagerIndicator_color2, 0X0000FF);
        ta.recycle();

        // 初始化paint
        mPaint = new Paint();
        mPaint.setColor(mColor);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mChildCount = getChildCount(); // 获取子item的个数
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mTop = getMeasuredHeight(); // 测量的高度即指示符的顶部位置
        int width = getMeasuredWidth(); // 获取测量的总宽度
        int height = mTop + mHeight; // 重新定义一下测量的高度
        mWidth = width / mChildCount; // 指示符的宽度为总宽度/item的个数

        setMeasuredDimension(width, height);
    }

    /**
     * 指示符滚动
     *
     * @param position 现在的位置
     * @param offset   偏移量 0 ~ 1
     */
    public void scroll(int position, float offset) {
        mLeft = (int) ((position + offset) * mWidth);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 圈出一个矩形
        Rect rect = new Rect(mLeft, mTop, mLeft + mWidth, mTop + mHeight);
        canvas.drawRect(rect, mPaint); // 绘制该矩形
        super.onDraw(canvas);
    }
}
