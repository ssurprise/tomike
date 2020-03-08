package com.skx.tomike.tanklaboratory.animation.activity;

import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

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
