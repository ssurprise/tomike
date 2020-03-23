package com.skx.tomike.tanklaboratory.widget.view;

import android.content.Context;
import androidx.core.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * Created by shiguotao on 2017/12/22.
 */
public class AdditionRollingScrollView extends NestedScrollView {


    public AdditionRollingScrollView(Context context) {
        super(context);
    }

    public AdditionRollingScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    private boolean mScaling;
    private OnRollingListener mOnRollingListener;
    private float mScrollRate = 0.5f;
    private int mTouchSlop;

    private float mLastX;
    private float mLastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (getScrollY() == 0) {
                    mLastX = event.getX();
                    mLastY = event.getY();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (mScaling) {
                    mScaling = false;
                    if (mOnRollingListener != null) {
                        mOnRollingListener.onReplyImage();
                    }
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
//                if (!mScaling) {
//                    if (getScrollY() != 0) {
//                        break;
//                    }
//                }
                float eventX = event.getX();
                float eventY = event.getY();


                if ((!mScaling && getScrollY() == 0) || mScaling) {
                    //mTouchSlop解决点击问题


                    float diffX = eventX - mLastX;
                    float diffY = eventY - mLastY;

                    if (!mScaling && (diffY < mTouchSlop || diffX > diffY)) {
                        //防止在最顶端时,不能上滑
                        break;
                    }

                    mScaling = true;
                    mLastY = eventY;
                    mLastX = eventX;

                    //当滑到超过顶部，向下滑时
                    if (diffY > 0 && getScrollY() > 0) {
                        return super.onTouchEvent(event);
                    }

                    //正常处理下拉放大
                    boolean handle = false;
                    if (mOnRollingListener != null) {
                        //distance为总长度
                        handle = mOnRollingListener.onRolling((int) (diffY * mScrollRate));
                    }
                    //根据监听器返回进行不同操作
                    if (handle) {
                        return true;
                    } else {
                        //继续向下传递
                        return super.onTouchEvent(event);
                    }
                } else {
                    mLastY = eventY;
                    mLastX = eventX;
                }
                break;
        }

        return super.onTouchEvent(event);
    }

    private float interceptDownX;
    private float interceptDownY;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            interceptDownX = ev.getX();
            interceptDownY = ev.getY();

            mLastX = ev.getX();
            mLastY = ev.getY();
        } else if (action == MotionEvent.ACTION_MOVE) {
            //解决蓝边问题
            float distanceY = ev.getY() - interceptDownY;

            distanceY = Math.abs(distanceY);
            if (distanceY > mTouchSlop) {
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    public void setOnRollingListener(OnRollingListener listener) {
        mOnRollingListener = listener;
    }

    public interface OnRollingListener {
        void onReplyImage();

        boolean onRolling(int distance);
    }
}
