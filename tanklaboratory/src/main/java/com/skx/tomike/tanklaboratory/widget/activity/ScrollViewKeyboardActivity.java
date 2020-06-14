package com.skx.tomike.tanklaboratory.widget.activity;

import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;

/**
 * 描述 : ScrollView 和键盘
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/5/30 5:01 PM
 */
public class ScrollViewKeyboardActivity extends SkxBaseActivity {

    private CardView mCvEditView;

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("androidx ViewPager").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scrollview_keyboard;
    }

    @Override
    protected void initView() {
        TextView tvClickView = findViewById(R.id.tv_scrollViewKeyboard_click);

        mCvEditView = findViewById(R.id.cv_landlordCalendar_modifyPrice_wrap);

        tvClickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCvEditView.setVisibility(View.VISIBLE);
            }
        });
    }
}
