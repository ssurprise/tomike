package com.skx.tomike.customview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.skx.tomikecommonlibrary.utils.DpPxSpTool;


/**
 * 缩放头图layout
 *
 * @author shiguotao
 *         Created on 2017/7/31.
 */
public class ZoomImageNestedScrollingParent extends RelativeLayout implements NestedScrollingParent {

    private final String TAG = "ZommImageNested";
    private NestedScrollingParentHelper mNestedScrollingParentHelper;
    private ImageView mHeaderChild;
    private NestedScrollView mNestedScrollView;
    private boolean mSkipNestedPreScroll;

    private int mInitTop;
    private int mMaxTop;
    private float mParallaxMult = 0.3f;


    public ZoomImageNestedScrollingParent(Context context) {
        super(context);
        init(context, null, 0);
    }

    public ZoomImageNestedScrollingParent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public ZoomImageNestedScrollingParent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, 0);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
        mInitTop = DpPxSpTool.INSTANCE.dip2px(context, 90);
        mMaxTop = DpPxSpTool.INSTANCE.dip2px(context, 240);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mHeaderChild = (ImageView) this.getChildAt(0);
        mNestedScrollView = (NestedScrollView) this.getChildAt(1);
        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.e(TAG, "onScrollChange-scrollY:" + scrollY);
            }
        });
    }


    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return target instanceof NestedScrollView && (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        mNestedScrollingParentHelper.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        Log.e(TAG, "dy:" + dy);

        int topMargin = mNestedScrollView.getPaddingTop();

        if (dy != 0 && !mSkipNestedPreScroll) {
            if (dy > 0) {// 手势向上，页面上滑
                if (topMargin > mInitTop) {// 在滑动范围内。
                    if (topMargin - dy < mInitTop) {

                        int tempDy = topMargin - mInitTop;
                        consumed[1] = tempDy;
                        Log.e(TAG, "< mInitTop:" + tempDy);
                        changeNestedScrollViewTopMargin(mInitTop);

                    } else {
                        Log.e(TAG, "> mInitTop:" + dy);

                        changeNestedScrollViewTopMargin(topMargin - dy);
                        consumed[1] = dy;
                    }

                } else {
                    Log.e(TAG, "dy > 0 , 在滑动范围外");
                }

            } else if (dy < 0) {// 手势下滑，页面下滑
                boolean b = ViewCompat.canScrollVertically(mNestedScrollView, -1);
                if (topMargin >= mInitTop && topMargin <= mMaxTop && !b) {// 在滑动范围内。
                    if (topMargin + (-dy) > mMaxTop) {
                        int tempDy = mMaxTop - topMargin;
                        consumed[1] = -tempDy;
                        Log.e(TAG, "> mMaxTop:" + (-tempDy));

                        changeNestedScrollViewTopMargin(mMaxTop);

                    } else {
                        Log.e(TAG, " <= mMaxTop" + (dy));

                        consumed[1] = dy;
                        changeNestedScrollViewTopMargin(topMargin - dy);
                    }

                } else {
                    Log.e(TAG, "dy < 0 , 在滑动范围外");
                }
            }
        } else {
            Log.e(TAG, "dy == 0 || mSkipNestedPreScroll :" + mSkipNestedPreScroll);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e(TAG, "onSizeChanged:h-" + h);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.e(TAG, "onLayout:1111111111111");
    }

    public void changeNestedScrollViewTopMargin(int dy) {
//        Log.e(TAG, "getPaddingTop:" + mNestedScrollView.getPaddingTop());
//        Log.e(TAG, "newPaddingTop:" + topMargin);
//        int nTop = mInitTop + (int) ((dy - mInitTop) * mParallaxMult);
        mNestedScrollView.setPadding(0, dy, 0, 0);
//
//
//        ViewGroup.LayoutParams lp = mHeaderChild.getLayoutParams();
//        lp.height += 3;
//        mHeaderChild.setLayoutParams(lp);
    }

    @Override
    public void onStopNestedScroll(View child) {
        super.onStopNestedScroll(child);
        mNestedScrollingParentHelper.onStopNestedScroll(child);
        mSkipNestedPreScroll = false;
    }

    /*
    // padding
      @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        Log.e(TAG, "dy:" + dy);

        int topMargin = mNestedScrollView.getPaddingTop();

        if (dy != 0 && !mSkipNestedPreScroll) {
            if (dy > 0) {// 手势向上，页面上滑
                if (topMargin > mInitTop) {// 在滑动范围内。
                    if (topMargin - dy < mInitTop) {

                        int tempDy = topMargin - mInitTop;
                        consumed[1] = tempDy;
                        Log.e(TAG, "< mInitTop:" + tempDy);
                        changeNestedScrollViewTopMargin(mInitTop);

                    } else {
                        Log.e(TAG, "> mInitTop:" + dy);

                        changeNestedScrollViewTopMargin(topMargin - getParallaxMultDis(dy));
                        consumed[1] = dy;
                    }

                } else {
                    Log.e(TAG, "dy > 0 , 在滑动范围外");
                }

            } else if (dy < 0) {// 手势下滑，页面下滑
                boolean b = ViewCompat.canScrollVertically(mNestedScrollView, -1);
                if (topMargin >= mInitTop && topMargin <= mMaxTop && !b) {// 在滑动范围内。
                    if (topMargin + (-dy) > mMaxTop) {
                        int tempDy = mMaxTop - topMargin;
                        consumed[1] = -tempDy;
                        Log.e(TAG, "> mMaxTop:" + (-tempDy));

                        changeNestedScrollViewTopMargin(mMaxTop);

                    } else {
                        Log.e(TAG, " <= mMaxTop" + (dy));

                        consumed[1] = dy;
                        changeNestedScrollViewTopMargin(topMargin - getParallaxMultDis(dy));
                    }

                } else {
                    Log.e(TAG, "dy < 0 , 在滑动范围外");
                }
            }
        } else {
            Log.e(TAG, "dy == 0 || mSkipNestedPreScroll :" + mSkipNestedPreScroll);
        }
    }


     */
}
