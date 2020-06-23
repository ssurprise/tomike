package com.skx.tomike.tanklaboratory.widget.activity;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomike.tanklaboratory.widget.adapter.ItemAnimatorAdapter;
import com.skx.tomike.tanklaboratory.widget.adapter.RecyclerViewBannerAdapter;
import com.skx.tomike.tanklaboratory.widget.view.RecyclerViewPageChangeListenerHelper;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 描述 : RecyclerView 仿ViewPager 实现滑页功能
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/5/30 5:01 PM
 */
public class RecyclerAsViewPagerActivity extends SkxBaseActivity<BaseViewModel> {

    private final List<String> mBannerList = new ArrayList<>();
    private final List<String> mContentList = new LinkedList<>();


    @Override
    protected void initParams() {
        mBannerList.add("http://pic1.win4000.com/wallpaper/6/58194994a591e.jpg");
        mBannerList.add("http://pic1.win4000.com/wallpaper/6/5819499e74cf8.jpg");
        mBannerList.add("http://pic1.win4000.com/wallpaper/3/584f9928e1771.jpg");
        mBannerList.add("http://pic1.win4000.com/wallpaper/3/584f992d6bd62.jpg");
        mBannerList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1590993126378&di=b2f667623306f875b48d50f120267a88&imgtype=0&src=http%3A%2F%2Fs9.rr.itc.cn%2Fr%2FwapChange%2F201611_18_11%2Fa46lpo74655993745596.gif");
        mBannerList.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1761055549,119613524&fm=26&gp=0.jpg");

        for (int i = 0, j = 50; i < j; i++) {
            mContentList.add("第" + i + "个");
        }
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("RecyclerView 仿 ViewPager效果").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview_as_view_pager;
    }

    @Override
    protected void initView() {
        RecyclerView mRvBanner = findViewById(R.id.rv_recyclerviewAsViewPager_banner);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        mRvBanner.setLayoutManager(layoutManager);
        mRvBanner.setAdapter(new RecyclerViewBannerAdapter(mBannerList));
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(mRvBanner);

        mRvBanner.addOnScrollListener(new RecyclerViewPageChangeListenerHelper(snapHelper, new RecyclerViewPageChangeListenerHelper.OnPageChangeListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.e(TAG, "page selected -> position:" + position);
            }
        }));


        RecyclerView mRvContent = findViewById(R.id.rv_recyclerviewAsViewPager_content);
        mRvContent.setLayoutManager(new LinearLayoutManager(this));
        mRvContent.setAdapter(new ItemAnimatorAdapter(mContentList));
    }
}
