package com.skx.tomike.tanklaboratory.widget.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomike.tanklaboratory.widget.adapter.ItemAnimatorAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * 描述 : RecyclerView 添加/删除item
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/20 6:27 PM
 */
public class RecyclerViewItemUpdateActivity extends AppCompatActivity {

    private ItemAnimatorAdapter mAdapter;
    private List<String> mCon = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_item_update);

        RecyclerView rv = findViewById(R.id.andHeaderView_recyclerView);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv.setItemAnimator(new DefaultItemAnimator());

        generateTestData();
        rv.setAdapter(mAdapter = new ItemAnimatorAdapter(this, mCon));
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
