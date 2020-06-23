package com.skx.tomike.tanklaboratory.widget.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomikecommonlibrary.utils.DpPxSpToolKt;

/**
 * Created by shiguotao on 2017/3/15.
 * <p>
 * 页签指示器
 */
public class PageIndicatorLayout extends LinearLayout {

    /**
     * 页数
     */
    public int mPageCount = 1;
    /**
     * 默认单元宽度
     */
    public int mDefaultUnitWight;
    /**
     * 默认单元高度
     */
    public int mDefaultUnitHeight;
    /**
     * 偏移距离
     */
    public int mIndicatorWidth;
    public int mIndicatorTopOffset;

    private Bitmap mIndicatorBitmap;
    private int mLeft = 0;

    public PageIndicatorLayout(Context context) {
        this(context, null);
    }

    public PageIndicatorLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PageIndicatorLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mIndicatorBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.page_indicator);


        mDefaultUnitWight = DpPxSpToolKt.dip2px(context, 15);
        mDefaultUnitHeight = DpPxSpToolKt.dip2px(context, 12);
        mIndicatorWidth = mDefaultUnitWight;

        setGravity(Gravity.CENTER_VERTICAL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        // 因为添加的页签是TextView，而TextView的内容默认上下是有间距的，如果直接使用的话，效果会有点偏下。为了UI调整的参数。
        int height = getMeasuredHeight() + 3;
        setMeasuredDimension(width, height);
    }

    /**
     * 设置页签数量。当数量和现有的数量不一致时，移除之前的view，重新添加页数view
     *
     * @param pageCount 页数
     */
    public void setPageCount(int pageCount) {
        if (pageCount != this.mPageCount) {
            removeAllViews();
            this.mPageCount = pageCount;
            addPageView();
        }
    }

    /**
     * 设置页签指示器bitmap
     *
     * @param pageIndicatorBitmap 页签指示器bitmap
     */
    public void setPageIndicatorBitmap(Bitmap pageIndicatorBitmap) {
        mIndicatorBitmap = pageIndicatorBitmap;
    }

    private void addPageView() {
        for (int i = 0; i < mPageCount; i++) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(mDefaultUnitWight, mDefaultUnitHeight);
            TextView textView = new TextView(getContext());
            textView.setLayoutParams(lp);
            textView.setTextSize(10);
            textView.setTextColor(Color.parseColor("#323232"));
            textView.setGravity(Gravity.CENTER);
            textView.setText(String.valueOf(i + 1));
            this.addView(textView);
        }
    }

    /**
     * 指示符滚动
     *
     * @param position 现在的位置
     * @param offset   偏移量 0 ~ 1
     */
    public void indicatorScroll(int position, float offset) {
        mLeft = (int) ((position + offset) * mIndicatorWidth);
        invalidate();
    }

    public void initPageIndicatorPosition(int position) {
        indicatorScroll(position, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mIndicatorBitmap != null) {
            // 这里top的位置是3，同样是因为添加的子view是TextView,内容上下有默认间距。所以把页签指示器向下位移一点距离，使其达到居中的效果
            canvas.drawBitmap(mIndicatorBitmap, mLeft, (getMeasuredHeight() >> 1) - 15, null);
        }
    }

}
