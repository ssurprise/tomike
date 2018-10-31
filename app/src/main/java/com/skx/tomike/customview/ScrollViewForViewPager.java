package com.skx.tomike.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

public class ScrollViewForViewPager extends ScrollView {
    private float xDistance, yDistance, xLast, yLast;

    public ScrollViewForViewPager(Context context) {
        super(context);
    }

    public ScrollViewForViewPager(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ScrollViewForViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;

                if (xDistance > yDistance) {
                    return false; // 表示向下传递事件
                }
        }
        return super.onInterceptTouchEvent(ev);
    }
}