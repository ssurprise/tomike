package com.skx.tomike.tanklaboratory.widget.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;


/**
 * Created by shiguotao on 2017/9/4.
 * <p>
 * 导航栏 View
 */
public class NavigationBarLayout<T> extends TabLayout {

    protected TabLayoutHelper<T> mTabLayoutHelper;

    public NavigationBarLayout(@NonNull Context context) {
        this(context, null);
    }

    public NavigationBarLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationBarLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTabLayoutHelper = new TabLayoutHelper<>(this);
    }

    public TabLayoutHelper getTabLayoutHelper() {
        return mTabLayoutHelper;
    }

    public void setTabLayoutHelper(TabLayoutHelper<T> mTabLayoutHelper) {
        this.mTabLayoutHelper = mTabLayoutHelper;
    }

    /**
     * 设置adapter
     *
     * @param resId   资源id
     * @param adapter Tab自定义view适配器
     * @return
     */
    public TabLayoutHelper setCustomAdapter(@LayoutRes int resId, TabLayoutHelper.TabLayoutAdapter<T> adapter) {
        if (mTabLayoutHelper == null) {
            throw new IllegalArgumentException("mTabLayoutHelper为null");
        }
        mTabLayoutHelper.setCustomAdapter(resId, adapter);
        return mTabLayoutHelper;
    }

    /**
     * 刷新TabLayout
     */
    public void notifyDataChanged() {
        mTabLayoutHelper.notifyDataChanged();
    }

    /**
     * 设置Tab 点击监听
     *
     * @param onItemClickListener Tab单击事件监听
     * @return
     */
    public TabLayoutHelper setOnItemClickListener(TabLayoutHelper.OnItemClickListener onItemClickListener) {
        mTabLayoutHelper.setOnItemClickListener(onItemClickListener);
        return mTabLayoutHelper;
    }

    /**
     * 设置Tab 自定义view选择器监听
     *
     * @param onCustomItemSelectorListener Tab 自定View选择器监听
     * @return
     */
    public TabLayoutHelper setOnCustomItemSelectorListener(TabLayoutHelper.OnCustomItemSelectorListener onCustomItemSelectorListener) {
        mTabLayoutHelper.setOnCustomItemSelectorListener(onCustomItemSelectorListener);
        return mTabLayoutHelper;
    }

    /**
     * 设置导航栏指示器的高度、颜色。当高度为0时，不显示指示器
     *
     * @param indicatorHeight           指示器颜色
     * @param selectedTabIndicatorColor 指示器高度
     */
    public TabLayoutHelper navigationBarBaseConfig(int indicatorHeight, @ColorInt int selectedTabIndicatorColor) {
        mTabLayoutHelper.setSelectedTabIndicatorHeight(indicatorHeight)
                .setSelectedTabIndicatorColor(selectedTabIndicatorColor);
        return mTabLayoutHelper;
    }
}
