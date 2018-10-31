package com.skx.tomike.activity.widget;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxBaseActivity;

public class FloatingActionButtonActivity extends SkxBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floating_action_button_fab);
    }

}
