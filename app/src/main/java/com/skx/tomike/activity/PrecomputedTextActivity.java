package com.skx.tomike.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.skx.tomike.R;

public class PrecomputedTextActivity extends SkxBaseActivity {
    TextView tv_precomputedText_1;
    TextView tv_precomputedText_2;
    TextView tv_precomputedText_3;
    TextView tv_precomputedText_4;
    TextView tv_precomputedText_5;
    TextView tv_precomputedText_6;
    TextView tv_precomputedText_7;
    TextView tv_precomputedText_8;
    TextView tv_precomputedText_9;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precomputed_text);

        initializeView();
    }

    @Override
    public void initializeView() {
        super.initializeView();
        tv_precomputedText_1 = findViewById(R.id.tv_precomputedText_1);
        tv_precomputedText_2 = findViewById(R.id.tv_precomputedText_2);
        tv_precomputedText_3 = findViewById(R.id.tv_precomputedText_3);
        tv_precomputedText_4 = findViewById(R.id.tv_precomputedText_4);
        tv_precomputedText_5 = findViewById(R.id.tv_precomputedText_5);
        tv_precomputedText_6 = findViewById(R.id.tv_precomputedText_6);
        tv_precomputedText_7 = findViewById(R.id.tv_precomputedText_7);
        tv_precomputedText_8 = findViewById(R.id.tv_precomputedText_8);
        tv_precomputedText_9 = findViewById(R.id.tv_precomputedText_9);
    }

    @Override
    public void refreshView() {
        super.refreshView();
    }
}
