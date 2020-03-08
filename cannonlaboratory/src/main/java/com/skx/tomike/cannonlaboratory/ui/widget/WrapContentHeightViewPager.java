package com.skx.tomike.cannonlaboratory.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.viewpager.widget.ViewPager;

/**
 * Created by shiguotao on 2016/4/25.
 * <p/>
 * 自适应高度的ViewPager.具体展示出的高度由适配器中的item来决定
 */
public class WrapContentHeightViewPager extends ViewPager {
    /**
     * Constructor
     *
     * @param context the context
     */
    public WrapContentHeightViewPager(Context context) {
        super(context);
    }

    /**
     * Constructor
     *
     * @param context the context
     * @param attrs   the attribute set
     */
    public WrapContentHeightViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            int h = child.getMeasuredHeight();
            if (h > height) height = h;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
