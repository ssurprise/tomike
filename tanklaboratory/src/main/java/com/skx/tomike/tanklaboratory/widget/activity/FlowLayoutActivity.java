package com.skx.tomike.tanklaboratory.widget.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.skx.tomike.tanklaboratory.R;


/**
 * 小猪-在线押金扣除
 */
public class FlowLayoutActivity extends AppCompatActivity {

    private String[] reasons = new String[]{"物品丢失", "物品污损", "水电燃气杂费", "清洁费用", "卫生过差扣费", "违反房屋守则", "加床费用", "其他"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
    }
}
