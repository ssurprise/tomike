package com.skx.tomike.tanklaboratory.widget.activity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;

/**
 * 描述 : view 焦点
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/1 7:46 PM
 */
public class ViewFocusActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("EditText 焦点").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_focus;
    }

    @Override
    protected void initView() {
        EditText mEtName = findViewById(R.id.et_editTextFocus_name);
        mEtName.setOnFocusChangeListener(focusChangeListener);
        mEtName.setTag("姓名输入框EditText");

        EditText mEtNickname = findViewById(R.id.et_editTextFocus_nickname);
        mEtNickname.setOnFocusChangeListener(focusChangeListener);
        mEtNickname.setTag("昵称输入框EditText");

        RadioButton mRbtnMan = findViewById(R.id.rbtn_editTextFocus_sex_man);
        mRbtnMan.setOnFocusChangeListener(focusChangeListener);
        mRbtnMan.setTag("性别单选框男RadioButton");

        RadioButton mRbtnWomen = findViewById(R.id.rbtn_editTextFocus_sex_women);
        mRbtnWomen.setOnFocusChangeListener(focusChangeListener);
        mRbtnWomen.setTag("性别单选框女RadioButton");

        TextView mTvText = findViewById(R.id.btn_editTextFocus_text);
        mTvText.setOnFocusChangeListener(focusChangeListener);
        mTvText.setTag("纯文本TextView");
        mTvText.setFocusable(true);
        mTvText.setFocusableInTouchMode(true);
        mTvText.requestFocus();

        Button mBtnSubmit = findViewById(R.id.btn_editTextFocus_submit);
        mBtnSubmit.setOnFocusChangeListener(focusChangeListener);
        mBtnSubmit.setTag("提交按钮button");

        mBtnSubmit.setFocusable(true);
        mBtnSubmit.setFocusableInTouchMode(true);
    }

    private View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            Log.e(TAG, v.getTag() + " -> " + (hasFocus ? "获得焦点" : "失去焦点"));
        }
    };
}
