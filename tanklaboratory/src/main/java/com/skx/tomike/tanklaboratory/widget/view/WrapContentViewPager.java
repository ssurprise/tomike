package com.skx.tomike.tanklaboratory.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * 描述 : 自适应高度的ViewPager.具体展示出的高度由适配器中的item来决定
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2016/4/25
 */
public class WrapContentViewPager extends ViewPager {

    public WrapContentViewPager(@NonNull Context context) {
        super(context);
    }

    public WrapContentViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            // child 的大小受自身的LayoutParams 和父view 的MeasureSpec 的双重限制！测量高度需要同时考虑这两个因素。
            ViewGroup.LayoutParams lp = child.getLayoutParams();
            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(lp.height, heightMeasureSpec));
            int h = child.getMeasuredHeight();
            if (h > height) height = h;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
