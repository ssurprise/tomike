package com.skx.tomike.tanklaboratory.widget.activity;

import com.skx.tomike.tanklaboratory.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

/**
 * 描述 : EditText光标修改
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/1 7:46 PM
 */
public class EditTextCursorActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("EditText 光标修改").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_text_cursor;
    }

    @Override
    protected void initView() {

    }
}
