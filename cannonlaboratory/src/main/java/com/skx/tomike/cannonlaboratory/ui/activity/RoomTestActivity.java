package com.skx.tomike.cannonlaboratory.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.skx.tomike.cannonlaboratory.R;
import com.skx.tomike.cannonlaboratory.bean.RecentlyBrowsedBean;
import com.skx.tomike.cannonlaboratory.viewmodel.RecentlyBrowsedViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;

import java.util.List;

/**
 * 描述 : ROOM 测试类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020-03-08 21:42
 */
public class RoomTestActivity extends SkxBaseActivity<RecentlyBrowsedViewModel> implements View.OnClickListener {

    private Button mBtnInsert;
    private Button mBtnUpdate;
    private Button mBtnQuery;
    private Button mBtnDelete;
    private TextView mTvLogcat;

    @Override
    protected void initParams() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_room_test;
    }

    @Override
    protected void subscribeEvent() {
        mViewModel.getRecentlyBrowsedLiveData().observe(this, new Observer<List<RecentlyBrowsedBean>>() {
            @Override
            public void onChanged(List<RecentlyBrowsedBean> recentlyBrowsedBeans) {
                Log.e("RoomTestActivity", recentlyBrowsedBeans.size() + "");
                for (RecentlyBrowsedBean item : recentlyBrowsedBeans) {
                    Log.e("RoomTestActivity", "luID：" + item.luID + " - timestamp:" + item.timestamp);
                }
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("Room - 最近浏览案例").create();
    }

    private void initView() {
        mBtnInsert = findViewById(R.id.btn_roomTest_insert);
        mBtnUpdate = findViewById(R.id.btn_roomTest_update);
        mBtnQuery = findViewById(R.id.btn_roomTest_query);
        mBtnDelete = findViewById(R.id.btn_roomTest_delete);
        mTvLogcat = findViewById(R.id.btn_roomTest_logcat);
    }

    private void initListener() {
        mBtnInsert.setOnClickListener(this);
        mBtnUpdate.setOnClickListener(this);
        mBtnQuery.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_roomTest_insert) {
            // 插入数据可以作为修改使用
            RecentlyBrowsedBean browsedBean = new RecentlyBrowsedBean();
            browsedBean.luCityID = "1";
            browsedBean.luID = "101";
            browsedBean.timestamp = System.currentTimeMillis() + "";

            mViewModel.insertRecentlyBrowsed(browsedBean);

        } else if (v.getId() == R.id.btn_roomTest_update) {
            // 修改数据不能做插入处理
            RecentlyBrowsedBean browsedBean = new RecentlyBrowsedBean();
            browsedBean.luCityID = "1";
            browsedBean.luID = "101";
            browsedBean.timestamp = System.currentTimeMillis() + "";

            mViewModel.updateRecentlyBrowsed(browsedBean);

        } else if (v.getId() == R.id.btn_roomTest_delete) {
            RecentlyBrowsedBean browsedBean = new RecentlyBrowsedBean();
            browsedBean.luID = "101";
            browsedBean.timestamp = System.currentTimeMillis() + "";

            mViewModel.deleteRecentlyBrowsed(browsedBean);

        } else if (v.getId() == R.id.btn_roomTest_query) {
            mViewModel.getRecentlyBrowsedByCityId("1");
        }
    }
}
