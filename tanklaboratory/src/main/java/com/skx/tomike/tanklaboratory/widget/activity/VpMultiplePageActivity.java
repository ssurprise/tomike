package com.skx.tomike.tanklaboratory.widget.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomike.tanklaboratory.widget.adapter.InfiniteLoopAdapter;
import com.skx.tomike.tanklaboratory.widget.adapter.MultiplePagerAdapter;
import com.skx.tomike.tanklaboratory.widget.view.AlphaPageTransformer;
import com.skx.tomike.tanklaboratory.widget.view.ScalePageTransformer;
import com.skx.tomike.tanklaboratory.widget.view.ClipViewPager;
import com.skx.tomike.tanklaboratory.widget.view.WrapContentHeightViewPager;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.utils.DpPxSpTool;
import com.skx.tomikecommonlibrary.utils.WidthHeightTool;

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

    private final ArrayList<Integer> list = new ArrayList<>();
    private final List<Integer> list2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        refreshView();
        installListener();
    }

    @Override
    protected void initParams() {
        list.add(R.drawable.image_05);
        list.add(R.drawable.image_06);
        list.add(R.drawable.image_07);

        list2.add(R.drawable.image_05);
        list2.add(R.drawable.image_05);
        list2.add(R.drawable.image_06);
        list2.add(R.drawable.image_07);
        list2.add(R.drawable.image_08);
        list2.add(R.drawable.image_05);
        list2.add(R.drawable.image_05);
        list2.add(R.drawable.image_06);
        list2.add(R.drawable.image_07);
        list2.add(R.drawable.image_08);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_vp_show_multiple_page;
    }

    @Override
    protected void subscribeEvent() {
    }

    @Override
    protected boolean useDefaultLayout() {
        return true;
    }

    @Override
    protected void configHeaderTitleView(@NonNull TextView title) {
        title.setText("ViewPager 一屏显示多个page");
    }

    private void initView() {
        wrapContentHeightViewPager = findViewById(R.id.wrapContentHeightViewPager);
        relativeLayout = findViewById(R.id.clipChildren_relativeLayout);
        clipChildrenVp = findViewById(R.id.show_multiple_clipViewPager);
    }

    private void refreshView() {
        // 第一种形式
        MultiplePagerAdapter adapter = new MultiplePagerAdapter(list);
        wrapContentHeightViewPager.setPageTransformer(false, new AlphaPageTransformer());
        wrapContentHeightViewPager.setPageMargin(15);
        wrapContentHeightViewPager.setAdapter(adapter);


        // 第二种形式
        clipChildrenVp.setOffscreenPageLimit(8);
        clipChildrenVp.setPageMargin((WidthHeightTool.getScreenWidth(this)
                - DpPxSpTool.INSTANCE.dip2px(this, 5) * 2
                - DpPxSpTool.INSTANCE.dip2px(this, 40) * 5) / 4);
        clipChildrenVp.setPageTransformer(false, new ScalePageTransformer());
        InfiniteLoopAdapter adapter2 = new InfiniteLoopAdapter(list2);
        clipChildrenVp.setAdapter(adapter2);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void installListener() {
        relativeLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return clipChildrenVp.dispatchTouchEvent(event);
            }
        });
        clipChildrenVp.setCurrentItem(list.size() * 500);
    }
}
