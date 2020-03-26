package com.skx.tomike.tanklaboratory.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 描述 : 流式view
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/24 8:08 PM
 */
public class FlowLayout extends ViewGroup {

    private final String TAG = "FlowLayout";

    private BaseAdapter mAdapter;
    private float mRowSpacing = 0.0f;// 行间距
    private float mColumnSpacing = 0.0f;// 列间距

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int expectHeight = 0;// 期望高度，累加 child 的 height
        int lineWidth = 0;// 单行宽度，动态计算当前行的宽度。
        int lineHeight = 0;// 单行高度，取该行中高度最大的view

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            // 测量子view 的宽高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            // 这里进行的是预判断。追加该child 后，行宽
            // 若未超过提供的最大宽度，则行宽需要追加child 的宽度，并且计算该行的最大高度。
            // 若超过提供的最大宽度，则需要追加该行的行高，并且更新下一行的行宽为当前child 的测量宽度。
            if (lineWidth + childWidth + getPaddingLeft() + getPaddingRight() <= widthSize) {// 未超过一行
                // 追加行宽。
                lineWidth += childWidth;
                // 不断对比，获取该行的最大高度
                lineHeight = Math.max(lineHeight, childHeight);

            } else {// 超过一行
                // 更新最新一行的宽度为此child 的测量宽度
                lineWidth = childWidth;
                // 期望高度追加当前行的行高。
                expectHeight += lineHeight + mRowSpacing;
            }
        }

        // 这里添加的是最后一行的高度。因为上面是在换行时才追加的行高，在不需要换行时并没有追加行高，丢失了不满足换行条件下的行高。
        // 举例说明：比如一行最多显示5个，但是当前只有1个，或者当前有6个的情况下，少了一行的行高。
        expectHeight += lineHeight;

        // 追加ViewGroup 的内边距
        expectHeight += getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(widthSize, resolveSize(expectHeight, heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int width = r - l;
        int childLeftOffset = getPaddingLeft();// child view 的left偏移距离，用于记录左边位置。
        int childTopOffset = getPaddingTop();// child view 的top偏移距离，用于记录顶部位置。

        int lineHeight = 0;// 行高

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            //跳过View.GONE的子View
            if (child.getVisibility() == View.GONE) {
                continue;
            }

            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            if (childLeftOffset + childWidth + getPaddingRight() <= width) {// 该child 加入后未超过一行宽度。
                // 行高取这一行中最高的child height
                lineHeight = Math.max(lineHeight, childHeight);

            } else {// 超过一行，换行显示。换行后的左侧偏移为初始值，顶部偏移为当前偏移量+当前行高
                childLeftOffset = getPaddingLeft();
                childTopOffset += lineHeight + mRowSpacing;
                lineHeight = childHeight;
            }
            child.layout(childLeftOffset, childTopOffset, childLeftOffset + childWidth, childTopOffset + childHeight);

            // 更新左侧偏移距离
            childLeftOffset += childWidth;
        }
    }

    public void setAdapter(BaseAdapter mAdapter) {
        this.mAdapter = mAdapter;
        this.removeAllViews();
        for (int i = 0; i < mAdapter.getCount(); i++) {
            View view = mAdapter.getView(i, null, this);
            this.addView(view);
        }
        requestLayout();
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
