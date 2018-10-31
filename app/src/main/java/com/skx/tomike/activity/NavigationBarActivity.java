package com.skx.tomike.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomike.customview.NavigationBarLayout;
import com.skx.tomike.util.TabLayoutHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 导航栏Activity
 */
public class NavigationBarActivity extends SkxBaseActivity implements View.OnClickListener {

    NavigationBarLayout<String> mNavigationBar;
    TextView contentText;

    List<String> tabList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_bar);

        mNavigationBar = (NavigationBarLayout) findViewById(R.id.navigationBar_tab);
        contentText = (TextView) findViewById(R.id.navigationBar_content);

        Button navigationBar_notifyBtn1 = (Button) findViewById(R.id.navigationBar_notifyBtn1);
        Button navigationBar_notifyBtn2 = (Button) findViewById(R.id.navigationBar_notifyBtn2);
        Button navigationBar_notifyBtn3 = (Button) findViewById(R.id.navigationBar_notifyBtn3);
        Button navigationBar_notifyBtn4 = (Button) findViewById(R.id.navigationBar_notifyBtn4);
        navigationBar_notifyBtn1.setOnClickListener(this);
        navigationBar_notifyBtn2.setOnClickListener(this);
        navigationBar_notifyBtn3.setOnClickListener(this);
        navigationBar_notifyBtn4.setOnClickListener(this);

        tabList = new ArrayList<>();
        tabList.add("天猫");
        tabList.add("京东");
        tabList.add("唯品会");
//        tabList.add("亚马逊");
//        tabList.add("一号店");
//        tabList.add("聚美优品");

        mNavigationBar.setTabMode(TabLayout.MODE_FIXED);
        mNavigationBar.setTabGravity(TabLayout.GRAVITY_FILL);
        mNavigationBar.setCustomAdapter(R.layout.layout_tab_item, new TabLayoutHelper.TabLayoutAdapter<String>(tabList) {
            @Override
            public void bindData(int position, @NonNull View view, String charSequence) {
                ImageView imgv_tabIcon = (ImageView) view.findViewById(R.id.tab_icon);
                TextView tv_tabTitle = (TextView) view.findViewById(R.id.tab_title);
                tv_tabTitle.setText(charSequence);
            }
        }).setOnItemClickListener(new TabLayoutHelper.OnTabCustomViewClick() {

            @Override
            public void onItemClick(TabLayout.Tab tab, int position, View view) {
                contentText.setText(tabList.get(position));
            }
        }).setOnCustomItemSelectorListener(new TabLayoutHelper.OnCustomItemSelectorListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab, int position, View view) {
                TextView tv_tabTitle = (TextView) view.findViewById(R.id.tab_title);
                tv_tabTitle.setTextColor(Color.parseColor("#ff4081"));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab, int position, View view) {
                TextView tv_tabTitle = (TextView) view.findViewById(R.id.tab_title);
                tv_tabTitle.setTextColor(Color.parseColor("#323232"));
            }
        });
        mNavigationBar.getTabLayoutHelper().setTabDividerLine(ContextCompat.getDrawable(this,R.drawable.linearlayout_divider));

//        ViewGroup childGroup = (ViewGroup) mNavigationBar.getChildAt(0);
//        for (int i = 0, j = childGroup.getChildCount(); i < j; i++) {
//            childGroup.getChildAt(i).setBackgroundColor(Color.parseColor("#123456"));
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.navigationBar_notifyBtn1:
                tabList.clear();
                tabList.add("京东");
                tabList.add("天猫");
                mNavigationBar.notifyDataChanged();
                break;

            case R.id.navigationBar_notifyBtn2:
                tabList.clear();
                tabList.add("聚美优品");
                tabList.add("唯品会");
                tabList.add("天猫");
                tabList.add("京东");
                tabList.add("一号店");
                tabList.add("亚马逊");
                mNavigationBar.notifyDataChanged();
                break;

            case R.id.navigationBar_notifyBtn3:
                tabList.clear();
                tabList.add("聚美优品");
                tabList.add("唯品会");
                tabList.add("京东");
                tabList.add("天猫");
                tabList.add("一号店");
                tabList.add("亚马逊");
                mNavigationBar.notifyDataChanged();
                break;

            case R.id.navigationBar_notifyBtn4:
                tabList.clear();
                tabList.add("聚美优品");
                tabList.add("唯品会");
                tabList.add("京东");
                tabList.add("一号店");
                tabList.add("天猫");
                tabList.add("亚马逊");
                mNavigationBar.notifyDataChanged();
                break;
        }
    }
}
