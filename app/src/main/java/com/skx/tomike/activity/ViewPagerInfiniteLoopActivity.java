package com.skx.tomike.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomike.adapter.InfiniteLoopAdapter;
import com.skx.tomike.customview.customlayout.PageIndicatorLayout;
import com.skx.tomikecommonlibrary.utils.DpPxSpTool;

import java.util.ArrayList;

/**
 * Created by shiguotao on 2016/4/20.
 * <p/>
 * ViewPager 无限循环
 * <p>
 * <p>
 * <p>
 * 控制ViewPager 的滑动速度   http://stackoverflow.com/questions/8155257/slowing-speed-of-viewpager-controller-in-android
 */
public class ViewPagerInfiniteLoopActivity extends SkxBaseActivity {

    private ViewPager vp_infiniteLoop;
    private ViewPager vp_automaticLoop;
    private LinearLayout infinite_loop_pageIndicator2_container;
    private PageIndicatorLayout infinite_pageIndicator;

    private ArrayList<Integer> infiniteLoopList = new ArrayList<>();
    private ArrayList<Integer> automaticLoopList = new ArrayList<>();

    private Handler mHandler = new Handler();
    private MyRunnable myRunnable = new MyRunnable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeData();
        initializeView();
        refreshView();
    }


    @Override
    public void initializeView() {
        super.initializeView();
        setContentView(R.layout.activity_vp_infinite_loop);
        infinite_pageIndicator = (PageIndicatorLayout) findViewById(R.id.infinite_loop_viewPager_pageIndicator);
        infinite_loop_pageIndicator2_container = (LinearLayout) findViewById(R.id.infinite_loop_pageIndicator2_container);

        vp_infiniteLoop = (ViewPager) findViewById(R.id.infinite_loop_viewPager);
        vp_automaticLoop = (ViewPager) findViewById(R.id.automatic_loop_viewPager);
    }

    @Override
    public void initializeData() {
        super.initializeData();

        infiniteLoopList.add(R.drawable.image_07);
        infiniteLoopList.add(R.drawable.image_05);
        infiniteLoopList.add(R.drawable.image_06);
        infiniteLoopList.add(R.drawable.image_07);
        infiniteLoopList.add(R.drawable.image_05);

        automaticLoopList.add(R.drawable.image_05);
        automaticLoopList.add(R.drawable.image_05);
        automaticLoopList.add(R.drawable.image_06);
        automaticLoopList.add(R.drawable.image_07);
        automaticLoopList.add(R.drawable.image_08);
    }


    @Override
    public void refreshView() {
        super.refreshView();
        infinite_pageIndicator.setPageCount(infiniteLoopList.size());
        // 无限循环
        InfiniteLoopAdapter adapter = new InfiniteLoopAdapter(this, infiniteLoopList);
        vp_infiniteLoop.setAdapter(adapter);
        vp_infiniteLoop.setCurrentItem(1);
        vp_infiniteLoop.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            int currentPosition;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("positionOffset", position + "----" + positionOffset);
                Log.e("positionOffsetPixels", position + "----" + positionOffsetPixels);
                infinite_pageIndicator.indicatorScroll(position, positionOffset);
                infinite_loop_pageIndicator2_container.scrollTo((int) ((position + positionOffset) * DpPxSpTool.INSTANCE.dip2px(ViewPagerInfiniteLoopActivity.this, 25)), 0);
            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // ViewPager.SCROLL_STATE_IDLE 当前页面完全展示，并且没有动画在运行
                if (state != ViewPager.SCROLL_STATE_IDLE) return;

                if (currentPosition == 0) {
                    // 当视图在第一个时，将页面号设置为图片的最后一张。
                    vp_infiniteLoop.setCurrentItem(infiniteLoopList.size() - 2, false);
                } else if (currentPosition == infiniteLoopList.size() - 1) {
                    // 当视图在最后一个是,将页面号设置为图片的第一张。
                    vp_infiniteLoop.setCurrentItem(1, false);
                }
            }
        });
//        infinite_pageIndicator.initPageIndicatorPosition(0);
        addPageView(1, infiniteLoopList.size());


        // 自动轮播
        InfiniteLoopAdapter automaticAdapter = new InfiniteLoopAdapter(this, automaticLoopList);
        vp_automaticLoop.setAdapter(automaticAdapter);
        mHandler.postDelayed(myRunnable, 3000);
    }

    int i = 0;

    public class MyRunnable implements Runnable {

        @Override
        public void run() {
            i++;
            i = i % automaticLoopList.size();
            vp_automaticLoop.setCurrentItem(i, true);
            mHandler.postDelayed(myRunnable, 3000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(myRunnable);
    }

    private void addPageView(int offset, int length) {
        for (int i = offset; i < length; i++) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DpPxSpTool.INSTANCE.dip2px(this, 25), ViewGroup.LayoutParams.MATCH_PARENT);
            TextView tv = new TextView(this);
            tv.setTextSize(24);
            tv.setGravity(Gravity.CENTER);
            tv.setText(i + "");
            tv.setLayoutParams(lp);
            infinite_loop_pageIndicator2_container.addView(tv);
        }
    }
}
