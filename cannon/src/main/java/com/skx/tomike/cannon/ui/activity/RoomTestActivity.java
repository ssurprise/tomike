package com.skx.tomike.cannon.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.cannon.R;
import com.skx.tomike.cannon.bean.RecentlyBrowsedBean;
import com.skx.tomike.cannon.viewmodel.RecentlyBrowsedViewModel;

import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_ROOM;

/**
 * 描述 : ROOM 测试类
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020-03-08 21:42
 */
@Route(path = ROUTE_PATH_ROOM)
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
    protected int layoutId() {
        return R.layout.activity_room_test;
    }

    @Override
    protected void subscribeEvent() {
        mViewModel.getRecentlyBrowsedLiveData().observe(this, recentlyBrowsedBeans -> {
            Log.e("RoomTestActivity", recentlyBrowsedBeans.size() + "");
            for (RecentlyBrowsedBean item : recentlyBrowsedBeans) {
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
//            browsedBean.luCityID = "1";
//            browsedBean.luID = "101";
//            browsedBean.timestamp = System.currentTimeMillis() + "";

            mViewModel.insertRecentlyBrowsed(browsedBean);

        } else if (v.getId() == R.id.btn_roomTest_update) {
            // 修改数据不能做插入处理
            RecentlyBrowsedBean browsedBean = new RecentlyBrowsedBean();
//            browsedBean.luCityID = "1";
//            browsedBean.luID = "101";
//            browsedBean.timestamp = System.currentTimeMillis() + "";

            mViewModel.updateRecentlyBrowsed(browsedBean);

        } else if (v.getId() == R.id.btn_roomTest_delete) {
            RecentlyBrowsedBean browsedBean = new RecentlyBrowsedBean();
//            browsedBean.luID = "101";
//            browsedBean.timestamp = System.currentTimeMillis() + "";

            mViewModel.deleteRecentlyBrowsed(browsedBean);

        } else if (v.getId() == R.id.btn_roomTest_query) {
            mViewModel.getRecentlyBrowsedByCityId("1");
        }
    }
}
