package com.skx.tomike.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.skx.tomike.R;
import com.skx.tomike.adapter.InfiniteLoopAdapter;
import com.skx.tomike.adapter.MultiplePagerAdapter;
import com.skx.tomike.customview.AlphaPageTransformer;
import com.skx.tomike.customview.ClipViewPager;
import com.skx.tomike.customview.ScalePageTransformer;
import com.skx.tomike.customview.WrapContentHeightViewPager;
import com.skx.tomike.util.WidthHeightTool;
import com.skx.tomikecommonlibrary.utils.DpPxSpTool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shiguotao on 2016/4/20.
 * <p/>
 * ViewPager 一屏展示多组page。
 * 常见的有2种形式：
 * 第一种是展示下一个页面的一部分。
 * 第二种是当前页面居中展示，左右各展示上下一个页面
 */
public class VpMultiplePageActivity extends SkxBaseActivity {
    private WrapContentHeightViewPager wrapContentHeightViewPager;
    private ClipViewPager clipChildrenVp;
    private RelativeLayout relativeLayout;
    ArrayList<Integer> list;
    ArrayList<Integer> list2;
    private List<Integer> mList2Ex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeData();
        initializeView();
        refreshView();
        installListener();
    }

    @Override
    public void initializeData() {
        super.initializeData();

        list = new ArrayList<>();
        list2 = new ArrayList<>();

        list.add(R.drawable.image_05);
        list.add(R.drawable.image_06);
        list.add(R.drawable.image_07);


        list2.add(R.drawable.image_05);
        list2.add(R.drawable.image_05);
        list2.add(R.drawable.image_06);
        list2.add(R.drawable.image_07);
        list2.add(R.drawable.image_08);

        // 第二种形式
        mList2Ex = new ArrayList<>();
        mList2Ex.addAll(list2);
        mList2Ex.addAll(list2);
    }

    @Override
    public void initializeView() {
        super.initializeView();
        setContentView(R.layout.activity_vp_show_multiple_page);

        wrapContentHeightViewPager = (WrapContentHeightViewPager) findViewById(R.id.wrapContentHeightViewPager);
        relativeLayout = (RelativeLayout) findViewById(R.id.clipChildren_relativeLayout);
        clipChildrenVp = (ClipViewPager) findViewById(R.id.show_multiple_clipViewPager);
    }

    @Override
    public void refreshView() {
        super.refreshView();
        // 第一种形式
        MultiplePagerAdapter adapter = new MultiplePagerAdapter(this, list);
        wrapContentHeightViewPager.setPageTransformer(false, new AlphaPageTransformer());
        wrapContentHeightViewPager.setPageMargin(15);
        wrapContentHeightViewPager.setAdapter(adapter);

        // ------------------------------------------分割线------------------------------------------------------------------------------------------------------------------------------------------------------

        clipChildrenVp.setOffscreenPageLimit(8);
//        clipChildrenVp.setPageMargin(30);
        clipChildrenVp.setPageMargin((WidthHeightTool.getScreenWidth(this) - DpPxSpTool.dip2px(this, 5) * 2 - DpPxSpTool.dip2px(this, 40) * 5) / 4);
        clipChildrenVp.setPageTransformer(false, new ScalePageTransformer());
        InfiniteLoopAdapter adapter2 = new InfiniteLoopAdapter(this, mList2Ex);
        clipChildrenVp.setAdapter(adapter2);


        Log.e("android.os.Build.BRAND", android.os.Build.BRAND + "");
        Log.e("android.os.Build.MODEL", android.os.Build.MODEL + "");
    }

    @Override
    public void installListener() {
        super.installListener();
        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return clipChildrenVp.dispatchTouchEvent(event);
            }
        });
        clipChildrenVp.setCurrentItem(list.size() * 500);
    }
}
