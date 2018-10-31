package com.skx.tomike.customview.customlayout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomike.adapter.HomepageLodgeUnitItemAdapter;
import com.skx.tomikecommonlibrary.utils.DpPxSpTool;

/**
 * Created by shiguotao on 2017/3/15.
 * <p>
 * 页签指示器
 */
public class PageIndicatorLayout extends LinearLayout {

    /**
     * 页数
     */
    public int mPageCount = 1;
    /**
     * 默认单元宽度
     */
    public int mDefaultUnitWight;
    /**
     * 默认单元高度
     */
    public int mDefaultUnitHeight;
    /**
     * 偏移距离
     */
    public int mOffsetDistance;
    /**
     * 最大偏移距离
     */
    public int mMaxOffsetDistance;

    private Bitmap mIndicatorBitmap;
    private Context mContent;
    private int mLeft = 0;

    public PageIndicatorLayout(Context context) {
        this(context, null);
    }

    public PageIndicatorLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PageIndicatorLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContent = context;
        mIndicatorBitmap = BitmapFactory.decodeResource(mContent.getResources(), R.drawable.page_indicator);
        mDefaultUnitWight = DpPxSpTool.dip2px(mContent, 15);
        mDefaultUnitHeight = DpPxSpTool.dip2px(mContent, 12);
        mOffsetDistance = mDefaultUnitWight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        // 因为添加的页签是TextView，而TextView的内容默认上下是有间距的，如果直接使用的话，效果会有点偏下。为了UI调整的参数。
        int height = getMeasuredHeight() + 3;
        setMeasuredDimension(width, height);
    }

    /**
     * 设置页签数量。当数量和现有的数量不一致时，移除之前的view，重新添加页数view
     *
     * @param pageCount 页数
     */
    public void setPageCount(int pageCount) {
        if (pageCount != this.mPageCount) {
            removeAllViews();
            this.mPageCount = pageCount;
            addPageView();
        }
    }

    /**
     * 设置页签指示器bitmap
     *
     * @param pageIndicatorBitmap 页签指示器bitmap
     */
    public void setPageIndicatorBitmap(Bitmap pageIndicatorBitmap) {
        mIndicatorBitmap = pageIndicatorBitmap;
    }

    private void addPageView() {
        for (int i = 0; i < mPageCount; i++) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(mDefaultUnitWight, mDefaultUnitHeight);
            TextView textView = new TextView(mContent);
            textView.setTextSize(10);
            textView.setTextColor(Color.parseColor("#323232"));
            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(lp);
            textView.setText(String.valueOf(i + 1));
            this.addView(textView);
        }
    }

    /**
     * 指示符滚动
     *
     * @param position 现在的位置
     * @param offset   偏移量 0 ~ 1
     */
    public void indicatorScroll(int position, float offset) {
        mLeft = (int) ((position + offset) * mOffsetDistance);
        invalidate();
    }

    public void initPageIndicatorPosition(int position) {
        indicatorScroll(position, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mIndicatorBitmap != null) {
            // 这里top的位置是3，同样是因为添加的子view是TextView,内容上下有默认间距。所以把页签指示器向下位移一点距离，使其达到居中的效果
            canvas.drawBitmap(mIndicatorBitmap, mLeft, 3, null);
        }
    }


    /**
     * 滑动状态锁。
     * 使用位置：
     * 1.在{@link #bindHomepageLodgeUnitViewPage} 方法中.在第一次调用onPageScrolled回调时，加锁。在ViewPager状态变为ViewPager.SCROLL_STATE_IDLE时，解锁。
     * <p>
     * 其他情况下自行使用
     */
    private boolean scrollStateLock = false;
    /**
     * 当前page item
     * 使用位置：
     * 1.在{@link #bindHomepageLodgeUnitViewPage} 方法中用于指示viewpager当前所在page.用于和onPageScrolled 方法中的position作比较来识别左右滑动
     * <p>
     * 其他情况下自行使用
     */
    private int currentItem;

    /**
     * 绑定首页房源ViewPager
     *
     * @param lodgeUnitVp 房源ViewPager
     */
    public void bindHomepageLodgeUnitViewPage(final ViewPager lodgeUnitVp) {
        if (lodgeUnitVp == null) return;

        final HomepageLodgeUnitItemAdapter adapter = (HomepageLodgeUnitItemAdapter) lodgeUnitVp.getAdapter();
        final int lodgeUnitSize = adapter.getLodgeUnitList().size();
        lodgeUnitVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 手势向左滑动（切换下一个pager）  positionOffset  从0到0.9999999 无限接近1
                // 手势向右滑动（切换上一个pager）  positionOffset  从无限接近1 0.999999到0

                if (!scrollStateLock) {// 当滑动状态锁是未锁定状态时，加锁！锁定后 currentItem就不会在发生变化
                    scrollStateLock = true;
                    currentItem = lodgeUnitVp.getCurrentItem();
                }
                if (position != 0 && position != (adapter.getCount() - 1)) {// position 不等于首位和最后一位
                    if (currentItem % lodgeUnitSize == 0) {// 页签1位置
                        if (currentItem > position) {// 手势向右滑动（切换上一页）positionOffset逐渐变小.页签效果：从1位置到末位置

                            // 由于手势是向右滑动，所以positionOffset 是从无限趋近于1慢慢减小到0.所以这里的偏移量应该是（1-positionOffset）从而达到指示器从左向右的效果
                            indicatorScrollBetweenFirstAndLastPos(1 - positionOffset);

                        } else {//手势向左滑动（切换下一页）
                            // 修改偏移量为默认单元格的宽度
                            indicatorScrollUnitDistance(position % lodgeUnitSize, positionOffset);
                        }

                    } else if (currentItem % lodgeUnitSize == lodgeUnitSize - 1) {// 页签末位置
                        if (currentItem == position) {// 手势向左滑动（切换下一页）.positionOffset逐渐变大，页签效果：从末位置到1位置
                            // 由于手势是向左滑动，所以positionOffset 是从0 逐渐变大，无限趋近于1.所以这里的偏移量应该是（1-positionOffset）才能达到指示器从右向左的效果
                            indicatorScrollBetweenFirstAndLastPos(1 - positionOffset);

                        } else {//手势向右滑动（切换上一页）
                            // 修改偏移量为默认单元格的宽度
                            indicatorScrollUnitDistance(position % lodgeUnitSize, positionOffset);
                        }

                    } else { // 其他中间位置
                        indicatorScrollUnitDistance(position % lodgeUnitSize, positionOffset);
                    }

                } else {// 当时第一个或者最后一个位置时，使用默认position、默认偏移距离
                    indicatorScrollUnitDistance(position % lodgeUnitSize, positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {// 解锁，解锁后currentItem 可以重新指定！
                    scrollStateLock = false;
                }
            }
        });
    }

    private void indicatorScrollUnitDistance(int position, float positionOffset) {
        if (mOffsetDistance != mDefaultUnitWight) {
            mOffsetDistance = mDefaultUnitWight;
        }
        indicatorScroll(position, positionOffset);
    }

    /**
     * 指示器在起始位置和最后位置之间直接滑动。
     * <p>
     * 当需要在第一个位置和最后一个位置直接滑动需要满足几个条件：
     * 1.修改起始位置为0；
     * 2.修改偏移量为最大偏移距离。最大偏移距离为view的宽度减去单元view的宽度或者指示器的宽度
     *
     * @param positionOffset 偏移量
     */
    private void indicatorScrollBetweenFirstAndLastPos(float positionOffset) {
        if (mMaxOffsetDistance == 0) {
            // 注意：这里需要减去一个单位view的宽度，否则会页签指示器会滑动超过边界。
            mMaxOffsetDistance = getMeasuredWidth() - mDefaultUnitWight;
        }
        // 修改偏移距离为最大偏移距离
        if (mOffsetDistance != mMaxOffsetDistance) {
            mOffsetDistance = mMaxOffsetDistance;
        }
        int tempPosition = 0;
        indicatorScroll(tempPosition, positionOffset);
    }
}
