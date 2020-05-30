package com.skx.tomike.tanklaboratory.widget.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;

/**
 * 描述 : RecyclerView 仿ViewPager 实现滑页功能
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/5/30 5:01 PM
 */
public class RecyclerAsViewPagerActivity extends SkxBaseActivity {

    @Override
    protected void initParams() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview_as_view_pager;
    }

    @Override
    protected void initView() {
        RecyclerView mRvContent = findViewById(R.id.rv_recyclerviewAsViewPager_content);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        mRvContent.setLayoutManager(layoutManager);
    }
}
