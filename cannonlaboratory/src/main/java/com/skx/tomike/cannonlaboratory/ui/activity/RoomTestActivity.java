package com.skx.tomike.cannonlaboratory.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.Observer;

import com.skx.tomike.cannonlaboratory.R;
import com.skx.tomike.cannonlaboratory.bean.RecentlyBrowsedBean;
import com.skx.tomike.cannonlaboratory.viewmodel.RecentlyBrowsedViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

import java.util.List;

/**
 * 描述 : ROOM 测试类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020-03-08 21:42
 */
public class RoomTestActivity extends SkxBaseActivity<RecentlyBrowsedViewModel> implements View.OnClickListener {

    private TextView mTvLogcat;

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("Room - 最近浏览案例").create();
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
    protected void initView() {
        mTvLogcat = findViewById(R.id.btn_roomTest_logcat);

        findViewById(R.id.btn_roomTest_insert).setOnClickListener(this);
        findViewById(R.id.btn_roomTest_update).setOnClickListener(this);
        findViewById(R.id.btn_roomTest_query).setOnClickListener(this);
        findViewById(R.id.btn_roomTest_delete).setOnClickListener(this);
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
