package com.skx.tomike.tank.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by shiguotao on 2016/11/26.
 */
public class Pull2ZoomListView extends ListView {

    private View mImageView;
    private int defaultHeaderHeight = 300;
    private static final int MAX_Y_OVERSCROLL_DISTANCE = 200;
    private int mMaxYOverscrollDistance;// 初始高度

    public Pull2ZoomListView(Context context) {
        super(context);
        init(context, null);
    }

    public Pull2ZoomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public Pull2ZoomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void initBounceListView(Context context) {
        final DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        final float density = metrics.density;
        mMaxYOverscrollDistance = (int) (density * MAX_Y_OVERSCROLL_DISTANCE);
    }

    private void init(Context context, AttributeSet attrs) {
        initBounceListView(context);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if (deltaY < 0) {// 下拉过度
            mImageView.getLayoutParams().height = mImageView.getHeight() - deltaY;
            mImageView.requestLayout();

        } else {// 上拉过度

        }

        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View header = (View) mImageView.getParent();
        // 向上推的时候 ListView会滑出屏幕，滑出的高度负值
        int deltaY = header.getTop();
        Log.e("deltaY", deltaY + "");

//        if (mImageView.getHeight() > defaultHeaderHeight) {
//            mImageView.getLayoutParams().height = mImageView.getHeight() + deltaY;
//
//            // 犹豫花出去了一节，所以要让header重新摆放为0
//            header.layout(mImageView.getLeft(), 0, mImageView.getRight(), mImageView.getHeight());
//
//            mImageView.requestLayout();
//        }
        super.onScrollChanged(l, t, oldl, oldt);
    }

    @Override
    public void addHeaderView(View v, Object data, boolean isSelectable) {
        super.addHeaderView(v, data, isSelectable);
        mImageView = v;
        if (mImageView instanceof ImageView) {
            defaultHeaderHeight = ((ImageView) mImageView).getDrawable().getIntrinsicHeight();
        }
    }
}
