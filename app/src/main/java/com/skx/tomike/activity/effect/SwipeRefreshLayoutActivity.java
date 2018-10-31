package com.skx.tomike.activity.effect;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxBaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwipeRefreshLayoutActivity extends SkxBaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final int REFRESH_COMPLETE = 0X110;
    SwipeRefreshLayout mSwipeLayout;
    ListView swipeRefresh_listView;
    private ArrayAdapter<String> mAdapter;
    private List<String> mDatas = new ArrayList<>(Arrays.asList("Java", "Javascript", "C++", "Ruby", "Json", "HTML"));

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case REFRESH_COMPLETE:
                    mDatas.addAll(Arrays.asList("Lucene", "Canvas", "Bitmap"));
                    mAdapter.notifyDataSetChanged();
                    mSwipeLayout.setRefreshing(false);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_refresh_layout);
        initializeData();
        initializeView();
        refreshView();
        installListener();
    }

    @Override
    public void initializeData() {
        super.initializeData();
    }

    @Override
    public void initializeView() {
        super.initializeView();
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh_container);
        swipeRefresh_listView = (ListView) findViewById(R.id.swipeRefresh_listView);

        mSwipeLayout.setOnRefreshListener(this);
//        new SwipeRefreshLayout.OnRefreshListener(){
//            @Override
//            public void onRefresh() {
//                mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
//            }
//        }
    }

    @Override
    public void refreshView() {
        super.refreshView();
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mDatas);
        swipeRefresh_listView.setAdapter(mAdapter);
    }

    @Override
    public void installListener() {
        super.installListener();
    }


    @Override
    public void onRefresh() {
        mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
    }
}
