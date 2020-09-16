package com.skx.tomike.tanklaboratory.widget.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomike.tanklaboratory.widget.adapter.InfiniteLoopAdapter;
import com.skx.tomike.tanklaboratory.widget.view.CustomSwitcher;
import com.skx.tomike.tanklaboratory.widget.view.PageIndicatorLayout;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.utils.DpPxSpToolKt;

import java.util.ArrayList;
import java.util.Locale;

/**
 * 描述 : ViewPager 页签指示器demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2016/4/20
 */
public class PageIndicatorActivity extends SkxBaseActivity<BaseViewModel> {

    /**
     * 边框指示器
     */
    private PageIndicatorLayout mLayoutIndicator1;
    /**
     * 页码指示器
     */
    private CustomSwitcher mLayoutIndicator2;
    private LinearLayout mLayoutIndicator3;
    private ViewPager mViewPager;

    private final ArrayList<Integer> mImagesList = new ArrayList<>();
    private final ArrayList<String> mIndicatorTitleList = new ArrayList<>();

    @Override
    protected void initParams() {
        mImagesList.add(R.drawable.image_05);
        mImagesList.add(R.drawable.image_06);
        mImagesList.add(R.drawable.image_07);
        mImagesList.add(R.drawable.image_08);

        mIndicatorTitleList.add("死神白起");
        mIndicatorTitleList.add("兵神孙子");
        mIndicatorTitleList.add("千古一帝秦始皇");
        mIndicatorTitleList.add("西楚霸王项羽");
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("ViewPager 页签指示器").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_page_indicator;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        refreshView();
    }

    @Override
    protected void initView() {
        mLayoutIndicator1 = findViewById(R.id.layout_pageIndicator_style_1);
        mLayoutIndicator2 = findViewById(R.id.layout_pageIndicator_style_2);
        mLayoutIndicator3 = findViewById(R.id.layout_pageIndicator_style_3);

        mViewPager = findViewById(R.id.vp_pageIndicator_content);
    }

    private void refreshView() {
        renderIndicatorStyle1();
        renderIndicatorStyle2();
        renderIndicatorStyle3();

        InfiniteLoopAdapter adapter = new InfiniteLoopAdapter(mImagesList);
        mViewPager.setAdapter(adapter);
    }

    private void renderIndicatorStyle1() {
        mLayoutIndicator1.setPageCount(mImagesList.size());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mLayoutIndicator1.indicatorScroll(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    private void renderIndicatorStyle2() {
        TextView textView = (TextView) mLayoutIndicator2.getChildAt(1);
        textView.setText(mIndicatorTitleList.get(0));

        mLayoutIndicator2.updateSwitcherStateByViewPager(mViewPager);
        mLayoutIndicator2.setSwitcherChangeListener(new CustomSwitcher.SwitcherChangeListener() {
            @Override
            public void nextPageListener(int position) {
                TextView textView = (TextView) mLayoutIndicator2.getChildAt(0);
                textView.setText(mIndicatorTitleList.get(position));
            }

            @Override
            public void previousPageListener(int position) {
                TextView textView = (TextView) mLayoutIndicator2.getChildAt(0);
                textView.setText(mIndicatorTitleList.get(position));
            }

            @Override
            public void changeOverListener(int currentPosition) {
                TextView textView = (TextView) mLayoutIndicator2.getChildAt(1);
                textView.setText(mIndicatorTitleList.get(currentPosition));
            }
        });
    }


    private void renderIndicatorStyle3() {
        for (int i = 1, j = mIndicatorTitleList.size(); i <= j; i++) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DpPxSpToolKt.dip2px(this, 25),
                    DpPxSpToolKt.dip2px(this, 25));
            TextView tv = new TextView(this);
            tv.setTextSize(14);
            tv.setGravity(Gravity.CENTER);
            tv.setText(String.format(Locale.getDefault(), "%d", i));
            tv.setLayoutParams(lp);
            mLayoutIndicator3.addView(tv);
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mLayoutIndicator3.scrollTo((int) ((position + positionOffset) * DpPxSpToolKt.dip2px(PageIndicatorActivity.this, 25)), 0);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }
}
