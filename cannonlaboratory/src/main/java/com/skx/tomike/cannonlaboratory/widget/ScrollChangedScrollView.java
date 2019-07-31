package com.skx.tomike.cannonlaboratory.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

import java.lang.ref.WeakReference;

/**
 * 作者：shiguotao
 * 日期：2018/11/5 10:42 AM
 * 描述：滑动监听的ScrollView
 */
public class ScrollChangedScrollView extends ScrollView {

    private ScrollViewListener scrollViewListener = null;
    private int handlerWhatId = 65984;
    private int timeInterval = 20;
    private int lastY = 0;

    private ScrollViewListenerHandler mHandler = new ScrollViewListenerHandler(this);

    public ScrollChangedScrollView(Context context) {
        super(context);
    }

    public ScrollChangedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollChangedScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    public interface ScrollViewListener {
        /**
         * 滑动监听
         *
         * @param scrollView ScrollView控件
         * @param x          x轴坐标
         * @param y          y轴坐标
         * @param oldx       上一个x轴坐标
         * @param oldy       上一个y轴坐标
         */
        void onScrollChanged(ScrollView scrollView, int x, int y, int oldx, int oldy);

        /**
         * 是否滑动停止
         *
         * @param isScrollStop true:滑动停止;false:未滑动停止
         */
        void onScrollStop(boolean isScrollStop);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            mHandler.sendMessageDelayed(mHandler.obtainMessage(handlerWhatId, this), timeInterval);
        }
        return super.onTouchEvent(ev);
    }


    private static class ScrollViewListenerHandler extends Handler {

        private WeakReference<ScrollChangedScrollView> reference;


        ScrollViewListenerHandler(ScrollChangedScrollView context) {
            reference = new WeakReference<>(context);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (reference != null && reference.get() != null) {
                ScrollChangedScrollView scrollChangedScrollView = reference.get();
                if (msg.what == scrollChangedScrollView.handlerWhatId) {
                    if (scrollChangedScrollView.lastY == scrollChangedScrollView.getScrollY()) {
                        if (scrollChangedScrollView.scrollViewListener != null) {
                            scrollChangedScrollView.scrollViewListener.onScrollStop(true);
                        }
                    } else {
                        if (scrollChangedScrollView.scrollViewListener != null) {
                            scrollChangedScrollView.scrollViewListener.onScrollStop(false);
                        }
                        scrollChangedScrollView.mHandler.sendMessageDelayed(
                                scrollChangedScrollView.mHandler.obtainMessage(scrollChangedScrollView.handlerWhatId, this),
                                scrollChangedScrollView.timeInterval);
                        scrollChangedScrollView.lastY = scrollChangedScrollView.getScrollY();
                    }
                }
            }
        }
    }
}
