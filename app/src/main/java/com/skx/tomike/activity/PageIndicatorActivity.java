package com.skx.tomike.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomike.adapter.InfiniteLoopAdapter;
import com.skx.tomike.customview.customlayout.CustomSwitcher;
import com.skx.tomike.customview.customlayout.PageIndicatorLayout;
import com.skx.tomikecommonlibrary.utils.DpPxSpTool;

import java.util.ArrayList;

/**
 * Created by shiguotao on 2016/4/20.
 * <p>
 */
public class PageIndicatorActivity extends SkxBaseActivity {

    private ViewPager vp_content;

    /**
     * 边框指示器
     */
    private PageIndicatorLayout ll_pageIndicator_type1;
    /**
     * 页码指示器
     */
    private LinearLayout ll_pageIndicator_type2;
    private CustomSwitcher ll_pageIndicator_type3;

    private ArrayList<Integer> imageList = new ArrayList<>();
    private ArrayList<String> contentList = new ArrayList<>();


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
        setContentView(R.layout.activity_page_indicator);
        ll_pageIndicator_type1 = (PageIndicatorLayout) findViewById(R.id.pageIndicator_type1);
        ll_pageIndicator_type2 = (LinearLayout) findViewById(R.id.pageIndicator_type2);
        ll_pageIndicator_type3 = (CustomSwitcher) findViewById(R.id.pageIndicator_type3);

        vp_content = (ViewPager) findViewById(R.id.pageIndicator_viewPager);
    }

    @Override
    public void initializeData() {
        super.initializeData();
        imageList.add(R.drawable.image_05);
        imageList.add(R.drawable.image_06);
        imageList.add(R.drawable.image_07);
        imageList.add(R.drawable.image_08);

        contentList.add("死神白起");
        contentList.add("兵神孙子");
        contentList.add("千古一帝秦始皇");
        contentList.add("西楚霸王项羽");
    }


    @Override
    public void refreshView() {
        super.refreshView();
        ll_pageIndicator_type1.setPageCount(imageList.size());

        InfiniteLoopAdapter adapter = new InfiniteLoopAdapter(this, imageList);
        vp_content.setAdapter(adapter);
        vp_content.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            int currentPosition;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ll_pageIndicator_type1.indicatorScroll(position, positionOffset);
                ll_pageIndicator_type2.scrollTo((int) ((position + positionOffset) * DpPxSpTool.dip2px(PageIndicatorActivity.this, 25)), 0);
            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        addPageView(1, contentList.size());


        TextView textView = (TextView) ll_pageIndicator_type3.getChildAt(1);
        textView.setText(contentList.get(0));

        ll_pageIndicator_type3.updateSwitcherStateByViewPager(vp_content);
        ll_pageIndicator_type3.setSwitcherChangeListener(new CustomSwitcher.SwitcherChangeListener() {
            @Override
            public void nextPageListener(int position) {
                TextView textView = (TextView) ll_pageIndicator_type3.getChildAt(0);
                textView.setText(contentList.get(position));
            }

            @Override
            public void previousPageListener(int position) {
                TextView textView = (TextView) ll_pageIndicator_type3.getChildAt(0);
                textView.setText(contentList.get(position));
            }

            @Override
            public void changeOverListener(int currentPosition) {
                TextView textView = (TextView) ll_pageIndicator_type3.getChildAt(1);
                textView.setText(contentList.get(currentPosition));
            }
        });
    }

    /**
     * 初始化页码指示器，添加页码view
     *
     * @param offset
     * @param length
     */
    private void addPageView(int offset, int length) {
        for (int i = offset; i <= length; i++) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DpPxSpTool.dip2px(this, 25), ViewGroup.LayoutParams.MATCH_PARENT);
            TextView tv = new TextView(this);
            tv.setTextSize(24);
            tv.setGravity(Gravity.CENTER);
            tv.setText(i + "");
            tv.setLayoutParams(lp);
            ll_pageIndicator_type2.addView(tv);
        }
    }
}
