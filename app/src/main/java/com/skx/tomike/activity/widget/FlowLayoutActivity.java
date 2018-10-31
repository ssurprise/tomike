package com.skx.tomike.activity.widget;

import android.os.Bundle;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxBaseActivity;

/**
 * 小猪-在线押金扣除
 */
public class FlowLayoutActivity extends SkxBaseActivity {

    private String[] reasons = new String[]{"物品丢失", "物品污损", "水电燃气杂费", "清洁费用", "卫生过差扣费", "违反房屋守则", "加床费用", "其他"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);

        initializeData();
        initializeView();
        installListener();
        refreshView();
    }

    @Override
    public void initializeData() {
        super.initializeData();
    }

    @Override
    public void initializeView() {
        super.initializeView();
    }

    @Override
    public void installListener() {
        super.installListener();
    }

    @Override
    public void refreshView() {
        super.refreshView();
    }
}
