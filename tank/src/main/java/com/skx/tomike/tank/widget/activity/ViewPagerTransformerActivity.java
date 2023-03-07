package com.skx.tomike.tank.widget.activity;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.tank.R;
import com.skx.tomike.tank.widget.adapter.InfiniteLoopAdapter;
import com.skx.tomike.tank.widget.view.DepthPageTransformer;
import com.skx.tomike.tank.widget.view.ScalePageTransformer;
import com.skx.tomike.tank.widget.view.ZoomOutPageTransformer;

import java.util.ArrayList;
import java.util.List;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_VIEWPAGER_PAGE_TRANSFORMER;

/**
 * 描述 : ViewPager 滑动样式Transformer。
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2016/4/20
 */
@Route(path = ROUTE_PATH_VIEWPAGER_PAGE_TRANSFORMER)
public class ViewPagerTransformerActivity extends SkxBaseActivity<BaseViewModel> {

    private ViewPager mViewPager;

    private final List<Integer> list = new ArrayList<>();

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
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("ViewPager transformer").create();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_viewpager_page_transformer;
    }

    @Override
    protected void initView() {
        findViewById(R.id.tv_pageTransformer_depthPageTransformer)
                .setOnClickListener(view -> mViewPager.setPageTransformer(true, new DepthPageTransformer()));
        findViewById(R.id.tv_pageTransformer_zoomOutPageTransformer)
                .setOnClickListener(view -> mViewPager.setPageTransformer(true, new ZoomOutPageTransformer()));

        mViewPager = findViewById(R.id.vp_pageTransformer_content);
        mViewPager.setPageTransformer(false, new ScalePageTransformer());
        mViewPager.setAdapter(new InfiniteLoopAdapter(list));
    }
}
