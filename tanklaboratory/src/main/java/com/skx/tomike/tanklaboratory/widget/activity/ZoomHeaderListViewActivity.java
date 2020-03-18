package com.skx.tomike.tanklaboratory.widget.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomike.tanklaboratory.widget.view.Pull2ZoomListView;

public class ZoomHeaderListViewActivity extends AppCompatActivity {

    private Pull2ZoomListView pull2ZoomListView;
    private String[] mData = {
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
            "按键的发酒疯阿发阿发啊",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_header_list_view);

        pull2ZoomListView = findViewById(R.id.pull2ZoomListView);
        ArrayAdapter mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mData);

        addHeadView();
        pull2ZoomListView.setAdapter(mAdapter);
    }

    private void addHeadView() {
        View headerView = LayoutInflater.from(this).inflate(R.layout.layout_pull_2_zoom_header, null);
        pull2ZoomListView.addHeaderView(headerView, null, false);
    }
}
