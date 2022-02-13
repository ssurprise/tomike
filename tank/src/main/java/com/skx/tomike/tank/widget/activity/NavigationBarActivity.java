package com.skx.tomike.tank.widget.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.material.tabs.TabLayout;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.tank.R;
import com.skx.tomike.tank.widget.view.NavigationBarLayout;
import com.skx.tomike.tank.widget.view.TabLayoutHelper;

import java.util.ArrayList;
import java.util.List;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_NAVIGATION_BAR;

/**
 * 描述 : 导航栏view
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020-03-18 23:16
 */
@Route(path = ROUTE_PATH_NAVIGATION_BAR)
public class NavigationBarActivity extends SkxBaseActivity<BaseViewModel> implements View.OnClickListener {

    private NavigationBarLayout<String> mNavigationBar;
    private TextView contentText;

    private final List<String> mTabList = new ArrayList<>();

    @Override
    protected void initParams() {
        mTabList.add("天猫");
        mTabList.add("京东");
        mTabList.add("唯品会");
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("TabLayout使用扩展").create();
    }

    @Override
    protected int layoutId() {
        return R.layout.activity_navigation_bar;
    }

    @Override
    protected void initView() {
        mNavigationBar = findViewById(R.id.navigationBar_tab);
        contentText = findViewById(R.id.navigationBar_content);

        findViewById(R.id.navigationBar_notifyBtn1).setOnClickListener(this);
        findViewById(R.id.navigationBar_notifyBtn2).setOnClickListener(this);
        findViewById(R.id.navigationBar_notifyBtn3).setOnClickListener(this);
        findViewById(R.id.navigationBar_notifyBtn4).setOnClickListener(this);

        mNavigationBar.setTabMode(TabLayout.MODE_FIXED);
        mNavigationBar.setTabGravity(TabLayout.GRAVITY_FILL);
        mNavigationBar.setCustomAdapter(R.layout.layout_tab_item, new TabLayoutHelper.TabLayoutAdapter<String>(mTabList) {
            @Override
            public void bindData(int position, @NonNull View view, String charSequence) {
//                ImageView imgv_tabIcon = view.findViewById(R.id.tab_icon);
                TextView tv_tabTitle = view.findViewById(R.id.tab_title);
                tv_tabTitle.setText(charSequence);
            }
        }).setOnItemClickListener(new TabLayoutHelper.OnTabCustomViewClick() {

            @Override
            public void onItemClick(TabLayout.Tab tab, int position, View view) {
                contentText.setText(mTabList.get(position));
            }
        }).setOnCustomItemSelectorListener(new TabLayoutHelper.OnCustomItemSelectorListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab, int position, View view) {
                TextView tv_tabTitle = view.findViewById(R.id.tab_title);
                tv_tabTitle.setTextColor(Color.parseColor("#ff4081"));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab, int position, View view) {
                TextView tv_tabTitle = view.findViewById(R.id.tab_title);
                tv_tabTitle.setTextColor(Color.parseColor("#323232"));
            }
        });
        mNavigationBar.getTabLayoutHelper()
                .setTabDividerLine(ContextCompat.getDrawable(this, R.drawable.linearlayout_divider));
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.navigationBar_notifyBtn1) {
            mTabList.clear();
            mTabList.add("京东");
            mTabList.add("天猫");
            mNavigationBar.notifyDataChanged();
        } else if (i == R.id.navigationBar_notifyBtn2) {
            mTabList.clear();
            mTabList.add("聚美优品");
            mTabList.add("唯品会");
            mTabList.add("天猫");
            mTabList.add("京东");
            mTabList.add("一号店");
            mTabList.add("亚马逊");
            mNavigationBar.notifyDataChanged();
        } else if (i == R.id.navigationBar_notifyBtn3) {
            mTabList.clear();
            mTabList.add("聚美优品");
            mTabList.add("唯品会");
            mTabList.add("京东");
            mTabList.add("天猫");
            mTabList.add("一号店");
            mTabList.add("亚马逊");
            mNavigationBar.notifyDataChanged();
        } else if (i == R.id.navigationBar_notifyBtn4) {
            mTabList.clear();
            mTabList.add("聚美优品");
            mTabList.add("唯品会");
            mTabList.add("京东");
            mTabList.add("一号店");
            mTabList.add("天猫");
            mTabList.add("亚马逊");
            mNavigationBar.notifyDataChanged();
        }
    }
}
