package com.skx.tomike.tank.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * ListView item中包含ViewPager
 *
 * @author shiguotao
 */
public class ListViewWithViewPager extends ListView {
    private float xDistance, yDistance, xLast, yLast;

    public ListViewWithViewPager(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public ListViewWithViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public ListViewWithViewPager(Context context) {
        super(context);
        initView();
    }

    private void initView() {
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
