package com.skx.tomike.util;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by shiguotao on 2017/8/30.
 * <p>
 * TabLayout 帮助类
 */
public class TabLayoutHelper<T> {
    private TabLayout mTabLayout;
    private TabLayoutAdapter<T> mAdapter;
    private int mResId;

    public TabLayoutHelper(TabLayout tabLayout) {
        if (tabLayout == null) {
            throw new IllegalArgumentException("TabLayout 不能为null");
        }
        this.mTabLayout = tabLayout;
    }

    public TabLayoutHelper<T> setSimpleAdapter(List<? extends CharSequence> tabTitleArray) {
        if (tabTitleArray == null) {
            throw new IllegalArgumentException("无Tab数据");
        }

        for (int i = 0, j = tabTitleArray.size(); i < j; i++) {
            CharSequence tabTitle = tabTitleArray.get(i);
            TabLayout.Tab tab = mTabLayout.newTab().setText(tabTitle != null ? tabTitle : "");

            mTabLayout.addTab(tab);
        }
        return this;
    }

    public TabLayoutHelper<T> setCustomAdapter(@LayoutRes int resId, TabLayoutAdapter<T> adapter) {

        // 当原来有Tab 时，先把原来的全部删除掉！
        if (mTabLayout.getTabCount() > 0) {
            mTabLayout.removeAllTabs();
        }
        if (mResId != resId) {
            mResId = resId;
        }
        if (mAdapter != adapter) {
            mAdapter = adapter;
        }
        // 重新渲染tab
        if (mAdapter != null) {
            for (int i = 0, j = mAdapter.getSize(); i < j; i++) {
                TabLayout.Tab tab = mTabLayout.newTab().setCustomView(resId);
                mAdapter.bindData(i, tab.getCustomView(), adapter.getItem(i));

                mTabLayout.addTab(tab);
            }
        }
        return this;
    }

    public TabLayoutAdapter<T> getAdapter() {
        return mAdapter;
    }

    /**
     * 刷新TabLayout
     * 当适配器为null 时，移除原来已添加的tab.
     */
    public void notifyDataChanged() {
        if (mAdapter == null) {
            if (mTabLayout.getTabCount() > 0) {
                mTabLayout.removeAllTabs();
            }
        } else {
            if (mAdapter.getSize() > mTabLayout.getTabCount()) {//新的tab数量多于原来的tab数量，需要新增加Tab

                // 更新已有的tab
                for (int i = 0, j = mTabLayout.getTabCount(); i < j; i++) {
                    TabLayout.Tab tab = mTabLayout.getTabAt(i);
                    mAdapter.bindData(i, tab.getCustomView(), mAdapter.getItem(i));
                }

                // 添加新的tab
                for (int i = mTabLayout.getTabCount(), j = mAdapter.getSize(); i < j; i++) {
                    TabLayout.Tab tab = mTabLayout.newTab().setCustomView(mResId);
                    mAdapter.bindData(i, tab.getCustomView(), mAdapter.getItem(i));
                    mTabLayout.addTab(tab);
                }

            } else if (mAdapter.getSize() < mTabLayout.getTabCount()) { //新的tab数量少于原来的tab数量，需要删除多于的tab
                // 更新已有的tab
                for (int i = 0, j = mAdapter.getSize(); i < j; i++) {
                    TabLayout.Tab tab = mTabLayout.getTabAt(i);
                    mAdapter.bindData(i, tab.getCustomView(), mAdapter.getItem(i));
                }
                // 删除多于的tab
                // 注：删除tab时，TabLayout自动处理了tab选择的问题，这里我们不需要额外处理
                for (int i = mTabLayout.getTabCount() - 1, j = mAdapter.getSize(); i >= j; i--) {
                    mTabLayout.removeTabAt(i);
                }

            } else {// 新的tab数目和原来的tab数目一致
                for (int i = 0, j = mAdapter.getSize(); i < j; i++) {
                    TabLayout.Tab tab = mTabLayout.getTabAt(i);
                    mAdapter.bindData(i, tab.getCustomView(), mAdapter.getItem(i));
                }
            }
        }
    }

    /**
     * 添加一个Tab，插入到指定位置。
     * 当插入位置小于0时，插入在第一个的位置；当插入位置大于当前的tab数量时，插入在最后一个的位置。
     *
     * @param charSequence 内容
     * @param position     插入位置
     * @return
     */
    public TabLayoutHelper<T> insertTab(CharSequence charSequence, int position) {
        // 插入位置如果<0 或者>tabCount，会角标越界
        if (position > mTabLayout.getTabCount()) {
            position = mTabLayout.getTabCount();

        } else if (position < 0) {
            position = 0;
        }

        TabLayout.Tab tab = mTabLayout.newTab().setText(charSequence != null ? charSequence : "");
        mTabLayout.addTab(tab, position);
        return this;
    }

    /**
     * 添加一个自定义内容的Tab，插入到指定位置。
     * 当插入位置小于0时，插入在第一个的位置；当插入位置大于当前的tab数量时，插入在最后一个的位置。
     *
     * @param t        数据对象
     * @param resId    自定义的布局文件资源id
     * @param position 插入位置
     * @return
     */
    public TabLayoutHelper<T> insertCustomTab(T t, @LayoutRes int resId, int position) {
        // 插入位置如果<0 或者>tabCount，会角标越界
        if (position > mTabLayout.getTabCount()) {
            position = mTabLayout.getTabCount();

        } else if (position < 0) {
            position = 0;
        }

        TabLayout.Tab tab = mTabLayout.newTab().setCustomView(resId);
        if (mAdapter != null) {
            mAdapter.bindData(position, tab.getCustomView(), t);
        }
        mTabLayout.addTab(tab, position);
        return this;
    }

    /**
     * 设置选择的tan
     *
     * @param position tab位置
     * @return
     */
    public TabLayoutHelper<T> setSelectedTab(int position) {
        if (position < 0 || position >= mTabLayout.getTabCount()) {
            return this;
        }
        TabLayout.Tab tab = mTabLayout.getTabAt(position);
        if (tab != null) {
            tab.select();
        }
        return this;
    }

    /**
     * 初始化选择的自定义tab标签。
     * 注：不能作为tab选择的设置，如果需要设置tab的选择请使用{@link #setSelectedTab)}方法进行设置。
     *
     * @param position 初始时选择的标签位置
     * @return
     */
    public TabLayoutHelper<T> initSelectedCustomTab(int position) {
        position = position < 0 ? 0 : position >= mTabLayout.getTabCount() ? mTabLayout.getTabCount() - 1 : position;
        TabLayout.Tab tab = mTabLayout.getTabAt(position);
        if (tab != null) {
            if (tab.isSelected()) {
                int tempSelectedPosition = position - 1 >= 0 ? position - 1 : position + 1 < mTabLayout.getTabCount() ? position + 1 : position;
                TabLayout.Tab tampSelectedTab = mTabLayout.getTabAt(tempSelectedPosition);
                if (tampSelectedTab != null) {
                    tampSelectedTab.select();
                }
            }
            tab.select();
        }
        return this;
    }

    /**
     * 获取指定位置Tab的自定义view
     *
     * @param position 位置
     * @return Tab中的自定义view
     */
    public View getTabCustomView(int position) {
        if (mTabLayout != null && position > 0 && position < mTabLayout.getTabCount()) {
            TabLayout.Tab tab = mTabLayout.getTabAt(position);
            if (tab != null) {
                return tab.getCustomView();
            }
        }
        return null;
    }

    /**
     * 添加tab变更选择时的监听
     *
     * @param listener 添加的监听
     * @return this
     */
    public TabLayoutHelper<T> addOnTabSelectedListener(TabLayout.OnTabSelectedListener listener) {
        if (listener != null) {
            mTabLayout.addOnTabSelectedListener(listener);
        }
        return this;
    }

    /**
     * 删除先前通过{@link #addOnTabSelectedListener（OnTabSelectedListener）}添加的给定的{@link TabLayout.OnTabSelectedListener}。
     *
     * @param listener 移除的监听
     * @return this
     */
    public TabLayoutHelper<T> removeOnTabSelectedListener(@NonNull TabLayout.OnTabSelectedListener listener) {
        mTabLayout.removeOnTabSelectedListener(listener);
        return this;
    }

    /**
     * 设置item点击事件,默认去掉TabLayout的点击效果。
     * <p>
     * 之所有没有采用addOnTabSelectedListener，然后转接onTabSelected（）的方式，是因为这样的话不能达到拦截的效果。比如：点击按钮
     * 如果没有登录的话需要跳转到登录界面。
     * <p>
     * 现在采用的重写子view 的点击，消费掉点击事件。而选择的动作实际上是通过 tab.select() 来触发的，所以如果有添加
     * OnTabSelectedListener 监听的话，还是会执行这个监听的。这个需要注意！！！
     * <p>
     */
    public TabLayoutHelper<T> setOnItemClickListener(final OnItemClickListener itemClickListener) {
        if (itemClickListener != null) {
            for (int i = 0, j = mTabLayout.getTabCount(); i < j; i++) {
                final TabLayout.Tab tab = mTabLayout.getTabAt(i);

                if (tab != null && tab.getCustomView() != null) {
                    ViewGroup viewGroup = (ViewGroup) tab.getCustomView().getParent();
                    viewGroup.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (itemClickListener.onItemPerformClick(tab, tab.getPosition(), tab.getCustomView())) {
                                itemClickListener.onItemClick(tab, tab.getPosition(), tab.getCustomView());
                                tab.select();
                            }
                        }
                    });
                }
            }
        }
        return this;
    }

    /**
     * 设置Tab自定义view的选择器监听。
     * 此动作监听是跟随着OnTabSelectedListener监听。
     *
     * @param itemSelectorListener Tab自定义View的选择器监听
     * @return this
     */
    public TabLayoutHelper<T> setOnCustomItemSelectorListener(final OnCustomItemSelectorListener itemSelectorListener) {
        if (itemSelectorListener != null) {
            mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    if (tab != null && tab.getCustomView() != null) {
                        itemSelectorListener.onTabSelected(tab, tab.getPosition(), tab.getCustomView());
                    }
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    if (tab != null && tab.getCustomView() != null) {
                        itemSelectorListener.onTabUnselected(tab, tab.getPosition(), tab.getCustomView());
                    }
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {

                }
            });
        }
        return this;
    }

    /**
     * 设置当前选定tab的指示器颜色。
     *
     * @param color 用于指示器的颜色
     */
    public TabLayoutHelper<T> initTabIndicator(@ColorInt int color, int height) {
        setSelectedTabIndicatorColor(color);
        setSelectedTabIndicatorHeight(height);
        return this;
    }

    /**
     * 设置当前选定tab的指示器颜色。
     *
     * @param color 用于指示器的颜色
     */
    public TabLayoutHelper<T> setSelectedTabIndicatorColor(@ColorInt int color) {
        mTabLayout.setSelectedTabIndicatorColor(color);
        return this;
    }

    /**
     * 为当前选定的选项卡设置选项卡指示器的高度。
     *
     * @param height 指示符高度，单位：像素
     */
    public TabLayoutHelper<T> setSelectedTabIndicatorHeight(int height) {
        mTabLayout.setSelectedTabIndicatorHeight(height);
        return this;
    }

    /**
     * 返回当前选中的tab的位置
     *
     * @return 所选标签位置，如果没有选定的tab 返回-1。
     */
    public int getSelectedTabPosition() {
        return mTabLayout.getSelectedTabPosition();
    }

    /**
     * 设置一个drawable作为项目之间的分隔符。
     *
     * @param divider 用于分割符的drawable
     */
    public TabLayoutHelper<T> setTabDividerLine(Drawable divider) {
        LinearLayout linearLayout = (LinearLayout) mTabLayout.getChildAt(0);
        if (linearLayout != null) {
            linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
            linearLayout.setDividerDrawable(divider);
        }
        return this;
    }

    /**
     * 自定义tab 与ViewPager的绑定。如果在绑定ViewPager之前已经添加了tab，绑定的时候会把之前添加的tab清掉，然后根据
     * ViewPager的adapter 重新设置tab，所以需要我们重新渲染数据，改变成我们自定的tab。
     *
     * @param viewPager
     * @return
     */
    public TabLayoutHelper<T> customTabSetupWithViewPager(ViewPager viewPager) {
        if (mTabLayout != null) {
            // 说明之前已经添加过tab了，绑定后会清掉所有的tab，需要重新修改tab
            if (mTabLayout.getChildCount() > 0) {
                mTabLayout.setupWithViewPager(viewPager);
                setCustomAdapter(mResId, mAdapter);

            } else {
                mTabLayout.setupWithViewPager(viewPager);
            }
        }
        return this;
    }

    /**
     * TabLayout 适配器。用于自定义view的情况下，绑定数据
     *
     * @param <T>
     */
    public abstract static class TabLayoutAdapter<T> {

        List<T> data;

        public TabLayoutAdapter(List<T> data) {
            this.data = data;
        }

        public int getSize() {
            return data != null ? data.size() : 0;
        }

        public T getItem(int position) {
            return data.get(position);
        }

        /**
         * 绑定数据
         *
         * @param position 当前tab的位置
         * @param view     当前tab的自定义view
         * @param t        数据
         */
        public abstract void bindData(int position, View view, T t);
    }

    /**
     * Tab 条目点击事件监听
     */
    public interface OnItemClickListener {

        /**
         * tab点击事件, 返回值得情况可具体考虑使用场景。例如：点击tab，未登录和登录时的处理不一样时，可用此方法的返回值做识别
         *
         * @param tab      tab标签
         * @param position 点击的位置
         * @param view     tab中的自定义view
         * @return
         */
        boolean onItemPerformClick(TabLayout.Tab tab, int position, View view);

        /**
         * tab点击监听
         *
         * @param tab      tab标签
         * @param position 点击的位置
         * @param view     tab中的自定义view
         */
        void onItemClick(TabLayout.Tab tab, int position, View view);
    }

    /**
     * Tab 自定义view点击事件监听
     */
    public abstract static class OnTabCustomViewClick implements OnItemClickListener {

        /**
         * tab点击事件，默认返回true.
         *
         * @param tab      tab标签
         * @param position 点击的位置
         * @param view     tab中的自定义view
         * @return
         */
        public boolean onItemPerformClick(TabLayout.Tab tab, int position, View view) {
            return true;
        }

        public void onItemClick(TabLayout.Tab tab, int position, View view) {
        }
    }

    /**
     * Tab 自定义view 选择器监听
     */
    public interface OnCustomItemSelectorListener {

        /**
         * 当选项卡进入所选状态时调用。
         *
         * @param tab      tab标签
         * @param position 当前选中tab的位置
         * @param view     当前选中tab的自定义view
         */
        void onTabSelected(TabLayout.Tab tab, int position, View view);

        /**
         * 当选项卡退出所选状态时调用。
         *
         * @param tab      tab标签
         * @param position 当前选中tab的位置
         * @param view     当前选中tab的自定义view
         */
        void onTabUnselected(TabLayout.Tab tab, int position, View view);
    }
}
