package com.skx.tomike.activity.effect;

import android.os.Bundle;
import android.view.Window;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxBaseActivity;

/**
 * 共享元素2
 */
public class ShareElement2Activity extends SkxBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_element2);
    }
}
