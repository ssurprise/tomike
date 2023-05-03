package com.skx.common.widget;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 描述 : RecyclerView GridLayoutManager 等间距。
 * <p>
 * 等间距需满足两个条件：
 * 1.各个模块的大小相等，即 各列的left+right 值相等；
 * 2.各列的间距相等，即 前列的right + 后列的left = 列间距；
 * <p>
 * 在{@link #getItemOffsets(Rect, View, RecyclerView, RecyclerView.State)} 中针对 outRect 的left 和right 满足这两个条件即可
 * <p>
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/19 4:54 PM
 */
public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private static final String TAG = "GridSpaceItemDecoration";

    private final int mSpanCount;// 横条目数量
    private final int mRowSpacing;// 行间距
    private final int mColumnSpacing;// 列间距

    /**
     * @param spanCount     列数
     * @param rowSpacing    行间距
     * @param columnSpacing 列间距
     */
    public GridSpaceItemDecoration(int spanCount, int rowSpacing, int columnSpacing) {
        this.mSpanCount = spanCount;
        this.mRowSpacing = rowSpacing;
        this.mColumnSpacing = columnSpacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (mSpanCount == 0) return;
        int position = parent.getChildAdapterPosition(view); // 获取view 在adapter中的位置。
        int column = position % mSpanCount; // view 所在的列

        // 左边距：column * (列间距 * (1f / 列数))
        outRect.left = column * mColumnSpacing / mSpanCount;
        // 右边距：列间距 - (column + 1) * (列间距 * (1f /列数))
        outRect.right = mColumnSpacing - (column + 1) * mColumnSpacing / mSpanCount;

        Log.d(TAG, "position:" + position
                + "    columnIndex: " + column
                + "    outRect.left=" + outRect.left + ", outRect.right=" + outRect.right);

        // 如果position > 行数，说明不是在第一行，则不指定行高，其他行的上间距为 top=mRowSpacing
        if (position >= mSpanCount) {
            outRect.top = mRowSpacing; // item top
        }
    }
}
