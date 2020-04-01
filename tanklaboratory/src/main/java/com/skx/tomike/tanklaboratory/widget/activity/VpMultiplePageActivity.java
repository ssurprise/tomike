package com.skx.tomike.tanklaboratory.widget.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.ViewPager;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomike.tanklaboratory.widget.adapter.InfiniteLoopAdapter;
import com.skx.tomike.tanklaboratory.widget.adapter.MultiplePagerAdapter;
import com.skx.tomike.tanklaboratory.widget.view.AlphaPageTransformer;
import com.skx.tomike.tanklaboratory.widget.view.ClipViewPager;
import com.skx.tomike.tanklaboratory.widget.view.ScalePageTransformer;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;
import com.skx.tomikecommonlibrary.utils.DpPxSpTool;
import com.skx.tomikecommonlibrary.utils.WidthHeightTool;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述 : ViewPager 一屏展示多组page。
 * <p>
 * 常见的有2种形式：
 * 第一种是展示下一个页面的一部分。
 * 第二种是当前页面居中展示，左右各展示上下一个页面
 * <p>
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2016/4/20
 */
public class VpMultiplePageActivity extends SkxBaseActivity {

    private final ArrayList<Integer> list = new ArrayList<>();
    private final List<Integer> list2 = new ArrayList<>();

    @Override
    protected void initParams() {
        list.add(R.drawable.image_01);
        list.add(R.drawable.image_02);
        list.add(R.drawable.image_03);
        list.add(R.drawable.image_04);
        list.add(R.drawable.image_05);
        list.add(R.drawable.image_06);
        list.add(R.drawable.image_07);
        list.add(R.drawable.image_08);

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
        return R.layout.activity_viewpager_multiple_page;
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("ViewPager 一屏显示多个page").create();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        renderMultiplePage1();
        renderMultiplePage2();
    }

    private void renderMultiplePage1() {
        ViewPager viewPager = findViewById(R.id.vp_multiplePage_widthFactor);
        viewPager.setPageTransformer(false, new AlphaPageTransformer());
        viewPager.setPageMargin(15);
        viewPager.setAdapter(new MultiplePagerAdapter(list));
    }

    @SuppressLint("ClickableViewAccessibility")
    private void renderMultiplePage2() {
        RelativeLayout rlCliViewPageWrap = findViewById(R.id.rl_multiplePage_clipWrap);
        final ClipViewPager clipChildrenVp = findViewById(R.id.vp_multiplePage_clip);

        clipChildrenVp.setOffscreenPageLimit(8);
        clipChildrenVp.setPageMargin((WidthHeightTool.getScreenWidth(this)
                - DpPxSpTool.INSTANCE.dip2px(this, 5) * 2
                - DpPxSpTool.INSTANCE.dip2px(this, 40) * 5) / 4);
        clipChildrenVp.setPageTransformer(false, new ScalePageTransformer());
        InfiniteLoopAdapter adapter2 = new InfiniteLoopAdapter(list2);
        clipChildrenVp.setAdapter(adapter2);

        rlCliViewPageWrap.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return clipChildrenVp.dispatchTouchEvent(event);
            }
        });
        clipChildrenVp.setCurrentItem(list.size() * 500);
    }
}
