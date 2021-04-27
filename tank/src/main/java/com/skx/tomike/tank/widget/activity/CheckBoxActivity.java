package com.skx.tomike.tank.widget.activity;

import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.skx.tomike.tank.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

/**
 * 描述 : CheckBox demo
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020-03-23 23:23
 */
public class CheckBoxActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("CheckBox 实验室").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_box;
    }

    @Override
    protected void initView() {
        CheckBox checkBox = findViewById(R.id.cb_checkBoxTest_1);
        CheckBox checkBox2 = findViewById(R.id.cb_checkBoxTest_2);
        CheckBox checkBox3 = findViewById(R.id.cb_checkBoxTest_3);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }
}
