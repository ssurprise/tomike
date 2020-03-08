package com.skx.tomike.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skx.tomike.R;
import com.skx.tomike.adapter.ItemAnimatorAdapter;

import java.util.LinkedList;
import java.util.List;

public class HeaderFooterRecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ItemAnimatorAdapter mAdapter;
    private List<String> mCon = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_footer_recycler_view);

        mRecyclerView = findViewById(R.id.andHeaderView_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        generateTestData();

        mAdapter = new ItemAnimatorAdapter(this, mCon);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void generateTestData() {
        for (int i = 0, j = 5; i < j; i++) {
            mCon.add("第" + i + "个");
        }
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
        // TODO 插入一项
        mAdapter.insertFirstPos("aaa");
    }


    public void insertRange(View view) {
        // TODO 插入一个区间项
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
