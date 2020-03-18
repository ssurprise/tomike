package com.skx.tomike.tanklaboratory.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.AttrRes;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by shiguotao on 2017/3/17.
 * <p>
 * 自定义切换控件
 */
public class CustomSwitcher extends FrameLayout {

    private float nextViewAlpha = 0.0f;
    private float currentViewAlpha = 1.0f;
    private SwitcherChangeListener mSwitcherChangeListener;

    public CustomSwitcher(@NonNull Context context) {
        super(context);
    }

    public CustomSwitcher(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSwitcher(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (getChildCount() >= 2) {
            throw new IllegalStateException("Can't add more than 2 views to a CustomSwitcher");
        }
        super.addView(child, index, params);
    }

    /**
     * 滑动状态锁。
     * {@link #updateSwitcherStateByViewPager} 方法中用来锁定状态的。在ViewPager 状态为ViewPager.SCROLL_STATE_IDLE时，解除锁定状态！
     */
    private boolean mScrollStateLock = false;
    /**
     * 当前item
     * {@link #updateSwitcherStateByViewPager} 方法中用来存储当前page。需要配合 {@link #mScrollStateLock} 一起使用。
     * 当 mScrollStateLock = false 时，初始化该属性值，然后加锁！防止被修改！
     */
    private int mCurrentItem = 0;

    /**
     * 上个position
     * <p>
     * {@link #updateSwitcherStateByViewPager} 方法中用来识别position是否发生变化，只有发生变化时才会发起previousPageListener 和nextPageListener 的变化。防止切换页面时其他view一直渲染。
     */
    private int lastPosition = -1;

    /**
     * 通过ViewPager的滑动监听更新switcher 状态。
     */
    public void updateSwitcherStateByViewPager(final ViewPager viewPager) {
        if (viewPager == null) return;

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            int selectedPosition;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (!mScrollStateLock) {// 当滑动状态锁是未锁定状态时，加锁！锁定后 currentItem就不会在发生变化
                    mScrollStateLock = true;
                    mCurrentItem = viewPager.getCurrentItem();
                }

                if (positionOffset != 0 && positionOffset != 1) {
                    if (mCurrentItem > position) {// 手势向右滑动（切换上一页）,positionOffset 逐渐变小
                        if (lastPosition != position) {
                            if (mSwitcherChangeListener != null) {
                                // 预展示的是上一个房源的数据，而 position 比currentItem 小1，正合适用它！
                                mSwitcherChangeListener.previousPageListener(position);
                            }
                            lastPosition = position;
                        }

                        setSwitcherAlpha(positionOffset, 1 - positionOffset);

                    } else {//手势向左滑动（切换下一页）,positionOffset 逐渐变大
                        if (lastPosition != position) {
                            if (mSwitcherChangeListener != null) {
                                // 预展示的是下一个房源的数据，而 position是当前的位置，所以需要+1 来标示下一个数据！
                                mSwitcherChangeListener.nextPageListener(position + 1);
                            }
                            lastPosition = position;
                        }
                        setSwitcherAlpha(1 - positionOffset, positionOffset);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                selectedPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {// 当为此状态时，解除状态锁
                    mScrollStateLock = false;
                    if (mSwitcherChangeListener != null) {
                        mSwitcherChangeListener.changeOverListener(selectedPosition);
                    }
                    lastPosition = -1;
                }
            }
        };
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    /**
     * 设置Switcher的alpha
     *
     * @param currentPosOffset 当前view的alpha
     * @param nextPosOffset    下页view的alpha
     */
    private void setSwitcherAlpha(@FloatRange(from = 0.0, to = 1.0) float currentPosOffset,
                                  @FloatRange(from = 0.0, to = 1.0) float nextPosOffset) {
        if (getChildAt(0) != null) {
            getChildAt(0).setAlpha(nextPosOffset);
        }
        if (getChildAt(1) != null) {
            getChildAt(1).setAlpha(currentPosOffset);
        }
    }

    public void setSwitcherChangeListener(SwitcherChangeListener switcherChangeListener) {
        this.mSwitcherChangeListener = switcherChangeListener;
    }

    /**
     * 开关改变监听
     */
    public interface SwitcherChangeListener {
        /**
         * 向后切换listener
         */
        void nextPageListener(int position);

        /**
         * 向前切换listener
         */
        void previousPageListener(int position);

        /**
         * 改变结束listener
         */
        void changeOverListener(int currentPosition);
    }

}
