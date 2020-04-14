package com.skx.tomike.activity.xzdz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxBaseActivity;

/**
 * 动效组 activity
 *
 * @author shiguotao
 */
public class EffectGroupActivity extends SkxBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effect_group);
    }

    /**
     * 滑动改变标题栏
     *
     * @param view
     */
    public void onScrollChangeTitle(View view) {
        Intent intent = new Intent(this, ScrollChangeTitleActivity.class);
        startActivity(intent);
    }

    public void onScrollZoomImage2(View view) {
        Intent intent = new Intent(this, ScrollZoomImage2Activity.class);
        startActivity(intent);
    }

    public void onScrollZoomImage3(View view) {
        Intent intent = new Intent(this, ScrollZoomImage3Activity.class);
        startActivity(intent);
    }
}
