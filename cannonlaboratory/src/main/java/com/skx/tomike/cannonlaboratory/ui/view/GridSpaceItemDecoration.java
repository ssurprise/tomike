package com.skx.tomike.cannonlaboratory.ui.view;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * 描述 : RecyclerView Grid 间隔线
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/19 4:54 PM
 */
public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpanCount;//横条目数量
    private int mRowSpacing;//行间距
    private int mColumnSpacing;// 列间距

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
        int position = parent.getChildAdapterPosition(view); // item 位置
        int column = position % mSpanCount; // item 所在的列

        outRect.left = column == 0 ? 0 : mColumnSpacing;
        if (position >= mSpanCount) {
            outRect.top = mRowSpacing; // item top
        }
    }
}
