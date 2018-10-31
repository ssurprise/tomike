package com.skx.tomike.interf;

/**
 * Created by shiguotao on 2016/6/8.
 * <p/>
 * View的类型，可用于ListView 多条目分组，在adapter 重写getItemViewType 中，用于区分。
 * 使用时，可以先让数据类来实现这个接口，重写其getViewTpye()方法。
 */
public interface IViewType {
    int getViewType();
}
