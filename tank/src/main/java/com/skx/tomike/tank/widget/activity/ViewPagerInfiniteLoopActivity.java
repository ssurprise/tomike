package com.skx.tomike.tank.widget.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.tank.R;
import com.skx.tomike.tank.widget.adapter.InfiniteLoopAdapter;

import java.util.ArrayList;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_VIEWPAGER_LOOP;

/**
 * Created by shiguotao on 2016/4/20.
 * <p/>
 * ViewPager 无限循环
 * <p>
 * <p>
 * <p>
 * 控制ViewPager滑动速度:http://stackoverflow.com/questions/8155257/slowing-speed-of-viewpager-controller-in-android
 */
@Route(path = ROUTE_PATH_VIEWPAGER_LOOP)
public class ViewPagerInfiniteLoopActivity extends SkxBaseActivity<BaseViewModel> {

    private ViewPager mVpInfiniteLoop;
    private ViewPager mVpAutomaticLoop;

    private final ArrayList<Integer> mInfiniteLoopList = new ArrayList<>();
    private final ArrayList<Integer> mAutomaticLoopList = new ArrayList<>();

    private final Handler mHandler = new Handler(Looper.myLooper());
    private final MyRunnable myRunnable = new MyRunnable();


    @Override
    protected void initParams() {
        mInfiniteLoopList.add(R.drawable.image_08);
        mInfiniteLoopList.add(R.drawable.image_05);
        mInfiniteLoopList.add(R.drawable.image_06);
        mInfiniteLoopList.add(R.drawable.image_07);
        mInfiniteLoopList.add(R.drawable.image_08);
        mInfiniteLoopList.add(R.drawable.image_05);

        mAutomaticLoopList.add(R.drawable.image_04);
        mAutomaticLoopList.add(R.drawable.image_05);
        mAutomaticLoopList.add(R.drawable.image_06);
        mAutomaticLoopList.add(R.drawable.image_07);
        mAutomaticLoopList.add(R.drawable.image_08);
    }

    @Override
    protected int layoutId() {
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
        InfiniteLoopAdapter adapter = new InfiniteLoopAdapter(mInfiniteLoopList);
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
                    mVpInfiniteLoop.setCurrentItem(mInfiniteLoopList.size() - 2, false);
                } else if (currentPosition == mInfiniteLoopList.size() - 1) {
                    // 当视图在最后一个是,将页面号设置为图片的第一张。
                    mVpInfiniteLoop.setCurrentItem(1, false);
                }
            }
        });
    }

    private void initAutoLoopView() {
        InfiniteLoopAdapter automaticAdapter = new InfiniteLoopAdapter(mAutomaticLoopList);
        mVpAutomaticLoop.setAdapter(automaticAdapter);
        mHandler.postDelayed(myRunnable, 3000);
    }

    int i = 0;

    public class MyRunnable implements Runnable {

        @Override
        public void run() {
            i++;
            i = i % mAutomaticLoopList.size();
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
