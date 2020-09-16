package com.skx.tomike.tanklaboratory.widget.activity;

import android.os.Bundle;
import android.os.Handler;

import androidx.viewpager.widget.ViewPager;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomike.tanklaboratory.widget.adapter.InfiniteLoopAdapter;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

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
public class ViewPagerInfiniteLoopActivity extends SkxBaseActivity<BaseViewModel> {

    private ViewPager mVpInfiniteLoop;
    private ViewPager mVpAutomaticLoop;

    private final ArrayList<Integer> infiniteLoopList = new ArrayList<>();
    private final ArrayList<Integer> automaticLoopList = new ArrayList<>();

    private Handler mHandler = new Handler();
    private MyRunnable myRunnable = new MyRunnable();


    @Override
    protected void initParams() {
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
    protected int getLayoutId() {
        return R.layout.activity_vp_infinite_loop;
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("ViewPager 无限循环+自动轮播").create();
    }

    @Override
    protected void initView() {
        mVpInfiniteLoop = findViewById(R.id.vp_loopViewPager_infiniteLoop);
        mVpAutomaticLoop = findViewById(R.id.vp_loopViewPager_autoLoop);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInfiniteLoopView();
        initAutoLoopView();
    }

    private void initInfiniteLoopView() {
        InfiniteLoopAdapter adapter = new InfiniteLoopAdapter(infiniteLoopList);
        mVpInfiniteLoop.setAdapter(adapter);
        mVpInfiniteLoop.setCurrentItem(1);
        mVpInfiniteLoop.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            int currentPosition;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
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
                    mVpInfiniteLoop.setCurrentItem(infiniteLoopList.size() - 2, false);
                } else if (currentPosition == infiniteLoopList.size() - 1) {
                    // 当视图在最后一个是,将页面号设置为图片的第一张。
                    mVpInfiniteLoop.setCurrentItem(1, false);
                }
            }
        });
    }

    private void initAutoLoopView() {
        InfiniteLoopAdapter automaticAdapter = new InfiniteLoopAdapter(automaticLoopList);
        mVpAutomaticLoop.setAdapter(automaticAdapter);
        mHandler.postDelayed(myRunnable, 3000);
    }

    int i = 0;

    public class MyRunnable implements Runnable {

        @Override
        public void run() {
            i++;
            i = i % automaticLoopList.size();
            mVpAutomaticLoop.setCurrentItem(i, true);
            mHandler.postDelayed(myRunnable, 3000);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(myRunnable);
    }

}
