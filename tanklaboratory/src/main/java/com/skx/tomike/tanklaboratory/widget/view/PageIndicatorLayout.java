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
import com.skx.tomikecommonlibrary.utils.DpPxSpTool;

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
    public int mOffsetDistance;
    /**
     * 最大偏移距离
     */
    public int mMaxOffsetDistance;

    private Bitmap mIndicatorBitmap;
    private Context mContent;
    private int mLeft = 0;

    public PageIndicatorLayout(Context context) {
        this(context, null);
    }

    public PageIndicatorLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PageIndicatorLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContent = context;
        mIndicatorBitmap = BitmapFactory.decodeResource(mContent.getResources(), R.drawable.page_indicator);
        mDefaultUnitWight = DpPxSpTool.INSTANCE.dip2px(mContent, 15);
        mDefaultUnitHeight = DpPxSpTool.INSTANCE.dip2px(mContent, 12);
        mOffsetDistance = mDefaultUnitWight;
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
            TextView textView = new TextView(mContent);
            textView.setTextSize(10);
            textView.setTextColor(Color.parseColor("#323232"));
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(lp);
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
        mLeft = (int) ((position + offset) * mOffsetDistance);
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
            canvas.drawBitmap(mIndicatorBitmap, mLeft, 3, null);
        }
    }


    /**
     * 指示器在起始位置和最后位置之间直接滑动。
     * <p>
     * 当需要在第一个位置和最后一个位置直接滑动需要满足几个条件：
     * 1.修改起始位置为0；
     * 2.修改偏移量为最大偏移距离。最大偏移距离为view的宽度减去单元view的宽度或者指示器的宽度
     *
     * @param positionOffset 偏移量
     */
    private void indicatorScrollBetweenFirstAndLastPos(float positionOffset) {
        if (mMaxOffsetDistance == 0) {
            // 注意：这里需要减去一个单位view的宽度，否则会页签指示器会滑动超过边界。
            mMaxOffsetDistance = getMeasuredWidth() - mDefaultUnitWight;
        }
        // 修改偏移距离为最大偏移距离
        if (mOffsetDistance != mMaxOffsetDistance) {
            mOffsetDistance = mMaxOffsetDistance;
        }
        int tempPosition = 0;
        indicatorScroll(tempPosition, positionOffset);
    }
}
