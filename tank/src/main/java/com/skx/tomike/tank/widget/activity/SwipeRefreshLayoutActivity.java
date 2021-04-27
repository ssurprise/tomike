package com.skx.tomike.tank.widget.activity;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.skx.tomike.tank.R;
import com.skx.tomike.tank.widget.viewmodel.SwipeRefreshViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述 : SwipeRefreshLayout demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020-03-18 22:11
 */
public class SwipeRefreshLayoutActivity extends SkxBaseActivity<SwipeRefreshViewModel> implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout mSwipeLayout;
    private ArrayAdapter<String> mAdapter;

    private final List<String> mData = new ArrayList<>();


    @Override
    protected void initParams() {
        mData.addAll(Arrays.asList("Java", "Javascript", "C++", "Ruby", "Json", "HTML"));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_swipe_refresh_layout;
    }

    @Override
    protected void subscribeEvent() {
        mViewModel.getTestDataLiveData().observe(this, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                if (strings != null) {
                    mData.addAll(strings);
                    mAdapter.notifyDataSetChanged();
                }
                mSwipeLayout.setRefreshing(false);
            }
        });
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("SwipeRefreshLayout 下拉刷新").create();
    }

    @Override
    protected void initView() {
        mSwipeLayout = findViewById(R.id.srl_swipeRefreshDemo_warp);
        mSwipeLayout.setOnRefreshListener(this);

        ListView lvTestData = findViewById(R.id.lv_swipeRefreshDem_content);
        lvTestData.setAdapter(mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mData));
    }

    @Override
    public void onRefresh() {
        mViewModel.loadData();
    }
}
