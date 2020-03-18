package com.skx.tomike.tanklaboratory.widget.activity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomike.tanklaboratory.widget.viewmodel.SwipeRefreshViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;

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
    protected boolean useDefaultLayout() {
        return true;
    }

    @Override
    protected void configHeaderTitleView(@NonNull TextView title) {
        super.configHeaderTitleView(title);
        title.setText("SwipeRefreshLayout 下拉刷新");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
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
