package com.skx.tomike.tank.widget.view;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @author shiguotao 能和 SrcollView 配合使用的ListView
 * 自定义一个类继承自ListView，通过重写其onMeasure方法，达到对ScrollView适配的效果
 */
public class ListViewForScrollView extends ListView {

    public ListViewForScrollView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public ListViewForScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    public ListViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /*
         * 重写该方法，达到使ListView适应ScrollView的效果
         */
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
