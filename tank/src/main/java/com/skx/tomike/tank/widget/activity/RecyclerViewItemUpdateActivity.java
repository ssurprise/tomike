package com.skx.tomike.tank.widget.activity;

import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skx.tomike.tank.R;
import com.skx.tomike.tank.widget.adapter.ItemAnimatorAdapter;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

import java.util.LinkedList;
import java.util.List;

/**
 * 描述 : RecyclerView 添加/删除item
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/20 6:27 PM
 */
public class RecyclerViewItemUpdateActivity extends SkxBaseActivity<BaseViewModel> {

    private ItemAnimatorAdapter mAdapter;
    private final List<String> mCon = new LinkedList<>();

    @Override
    protected void initParams() {
        for (int i = 0, j = 5; i < j; i++) {
            mCon.add("第" + i + "个");
        }
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("RecyclerView 添加/删除item").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview_item_update;
    }

    @Override
    protected void initView() {
        RecyclerView rv = findViewById(R.id.rv_recyclerviewItemUpdate_content);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setAdapter(mAdapter = new ItemAnimatorAdapter(mCon));
    }

    public void append(View view) {
        mAdapter.append("新加的");
    }

    public void appendRange(View view) {
        List<String> list = new LinkedList<>();
        for (int i = 0, j = 2; i < j; i++) {
            list.add("新加的 - 第" + i + "个");
        }
        mAdapter.insertRange(list);
    }

    public void insertItem(View view) {
        // 插入一项
        mAdapter.insertFirstPos("aaa");
    }


    public void insertRange(View view) {
        // 插入一个区间项
        List<String> list = new LinkedList<>();
        for (int i = 99, j = 101; i < j; i++) {
            list.add("新加的 - 第" + i + "个");
        }
        mAdapter.insertRange(list);
    }

    /**
     * 删除第一项
     *
     * @param view
     */
    public void removeFirst(View view) {
        mAdapter.removeFirst();
    }

    /**
     * 删除最后一项
     *
     * @param view
     */
    public void removeLast(View view) {
        mAdapter.removeLast();
    }

    /**
     * 删除区间
     *
     * @param view
     */
    public void removeRange(View view) {
        List<String> list = new LinkedList<>();
        list.add("第0个");
        list.add("第1个");
        mAdapter.removeRange(list);
    }

}
