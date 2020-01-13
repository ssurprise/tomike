package com.skx.tomike.tanklaboratory.widget.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;

import com.skx.tomike.tanklaboratory.R;

public class FloatingActionButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);
        FloatingActionButton fab = findViewById(R.id.floating_action_button_fab);
    }

}
