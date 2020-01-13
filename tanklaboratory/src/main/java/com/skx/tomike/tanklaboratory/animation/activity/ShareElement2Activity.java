package com.skx.tomike.tanklaboratory.animation.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.skx.tomike.tanklaboratory.R;

/**
 * 共享元素2
 */
public class ShareElement2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_element2);
    }
}
