package com.skx.tomike.tank.widget.view;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

/**
 * 描述 : RecyclerView 实现ViewPager 翻页功能帮助类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/6/5 7:46 PM
 */
public class RecyclerViewPageChangeListenerHelper extends RecyclerView.OnScrollListener {

    private SnapHelper mSnapHelper;
    private OnPageChangeListener onPageChangeListener;
    private int oldPosition = -1;

    public RecyclerViewPageChangeListenerHelper(SnapHelper snapHelper, OnPageChangeListener onPageChangeListener) {
        this.mSnapHelper = snapHelper;
        this.onPageChangeListener = onPageChangeListener;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        if (onPageChangeListener != null) {
            onPageChangeListener.onScrolled(recyclerView, dx, dy);
        }
    }

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);

        if (onPageChangeListener != null) {
            onPageChangeListener.onScrollStateChanged(recyclerView, newState);
        }

        // 滑动停止时检查当前显示的那个item
        if (newState == RecyclerView.SCROLL_STATE_IDLE) { // RecyclerView.SCROLL_STATE_IDLE 当滚动停止时触发防止在滚动过程中不停触发
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager == null) {
                return;
            }

            // 1.获取当前对齐的item view
            View snapView = mSnapHelper.findSnapView(layoutManager);
            if (snapView == null) {
                return;
            }
            // 2.获取 item view 对应的位置
            int position = layoutManager.getPosition(snapView);
            if (oldPosition != position) {
                onPageChangeListener.onPageSelected(position);
                oldPosition = position;
            }
        }
    }

    public interface OnPageChangeListener {
        void onScrollStateChanged(RecyclerView recyclerView, int newState);

        void onScrolled(RecyclerView recyclerView, int dx, int dy);

        void onPageSelected(int position);
    }
}
