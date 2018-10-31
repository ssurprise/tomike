package com.skx.tomike.customview;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.skx.tomikecommonlibrary.utils.DpPxSpTool;

/**
 * 自定义点评布局view
 *
 * @author shiguotao
 */
public class CustomCommentLayoutView extends LinearLayout {

    // 标题栏高度，固定值56dp
    private int mTitleHeightPx;

    /**
     * 总共滑动区间
     */
    private int mTotalScrollRange = 0;
    private int mSecondChildOffsetY = 0;
    /**
     * View 开始发生改变（大小、透明度等）的阈值
     */
    private int mChildStartChangeThreshold = 0;


    private Context mContext;

    public CustomCommentLayoutView(Context context) {
        this(context, null);
    }

    public CustomCommentLayoutView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomCommentLayoutView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        mTitleHeightPx = DpPxSpTool.dip2px(context, 56);
        mSecondChildOffsetY = DpPxSpTool.dip2px(context, 210);
        mChildStartChangeThreshold = DpPxSpTool.dip2px(context, 30);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (mSecondChildOffsetY == 0) {
            if (getChildCount() > 1) {
                View view = getChildAt(1);
                mSecondChildOffsetY = view.getTop();
            }
        }

        if (mTotalScrollRange == 0) {
            int minLocalHeight = mTitleHeightPx;
            for (int i = 0, j = getChildCount(); i < j; i++) {
                View view = getChildAt(i);
                if (i == getChildCount() - 1) {
                    if (view.getVisibility() != GONE) {
                        minLocalHeight += view.getMeasuredHeight() + DpPxSpTool.dip2px(mContext, 30);
                    }
                }
            }

            setMinimumHeight(minLocalHeight);
            mTotalScrollRange = getMeasuredHeight() - minLocalHeight;
        }
    }

    /**
     * 刷新子View
     *
     * @param verticalOffset 父View 消耗的事件
     */
    public void refreshChildrenDy(int verticalOffset) {
        Rect rect = new Rect();
        getLocalVisibleRect(rect);
//        Log.e("rect.top", rect.top + "");

        refreshFirstChildView(rect);
        refreshSecondChildView(rect);
    }

    int lastTop = 0;

    /**
     * 第一个子View位移、形状变化
     *
     * @param rect
     */
    private void refreshFirstChildView(Rect rect) {
        View child = getChildAt(0);
        if (child != null && rect.top <= mTotalScrollRange) {
            if (rect.top <= mChildStartChangeThreshold) {
                // 此view滑出屏幕距离小于view变化阈值时，通过设置translationY = （滑动距离） 保持子view仍然保持在屏幕中固定位置显示。
                child.setTranslationY(rect.top);

                if (lastTop >= mChildStartChangeThreshold) {
                    // 当上一次的top 在变化的临界范围之外，这次的移动使top 小于变化阈值，那么会有上次的top 至临界值的区间没有变化，
                    // 所以用一个标志位识别，使变化置于初始状态
                    if (child instanceof LuCommentScoreWidget) {
                        ((LuCommentScoreWidget) child).changeViewShape(0);
                    }
                    lastTop = rect.top;
                }

            } else {
                // 此view 滑出屏幕距离view变化阈值时，第一个子view 向标题栏做滑动，最终在标题栏高度的中间位置显示。
                if (child instanceof LuCommentScoreWidget) {

                    // 子view 滑动的区间
                    int moveRange = mTitleHeightPx / 2 + DpPxSpTool.dip2px(mContext, 25) / 2;

                    // 计算公式：子View 的滑动偏移量 = view的实际变化距离 * 子view的总滑动区间 / 子view 的总滑动区间
                    int i = (rect.top - mChildStartChangeThreshold) * moveRange / (mTotalScrollRange - mChildStartChangeThreshold);

                    // 子view 滑动，因为是像上滑动的，所以应当是 减去滑动偏移
                    child.setTranslationY(rect.top - i);
                    // 子View 形变
                    ((LuCommentScoreWidget) child).changeViewShape(i * 1.0f / moveRange);
                }
                lastTop = rect.top;
            }
        }
    }

    /**
     * 第二个子View位移、透明度变化
     *
     * @param rect
     */
    private void refreshSecondChildView(Rect rect) {
        View child = getChildAt(1);
        if (child != null && rect.top <= mTotalScrollRange) {
            if (rect.top <= mChildStartChangeThreshold) {
                //  此view滑出屏幕距离小于view变化阈值时，通过设置translationY保持子view仍然保持在屏幕中固定位置显示。
                child.setTranslationY(0);
                child.setAlpha(1);
            } else {

                // 子view 滑动的区间。计算公式：总偏移量 - 标题栏高度 - 和下一个View的间距。
                int moveRange = mSecondChildOffsetY - mTitleHeightPx - DpPxSpTool.dip2px(mContext, 30);

                // 子View 的滑动偏移量
                int i = (rect.top - mChildStartChangeThreshold) * moveRange / (mTotalScrollRange - mChildStartChangeThreshold);

                // 子view 滑动，因为是向上滑动的，所以应当是减去滑动偏移，同时要减去之前未滑动的距离
                child.setTranslationY(rect.top - DpPxSpTool.dip2px(mContext, 30) - i);
                // 子View 形变
                child.setAlpha(1 - i * 1.0f / moveRange);
            }
        }
    }
}
