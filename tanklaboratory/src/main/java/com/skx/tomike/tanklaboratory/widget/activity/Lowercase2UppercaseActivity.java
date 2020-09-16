package com.skx.tomike.tanklaboratory.widget.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ReplacementTransformationMethod;
import android.widget.EditText;
import android.widget.TextView;

import com.skx.tomike.tanklaboratory.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

/**
 * 描述 : EditText 小写字母转大写.
 * http://www.huati365.com/answer/QM2W8K5WdgmRzw6
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/5/7 2:31 PM
 */
public class Lowercase2UppercaseActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("EditText 小写字母转大写").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_lowercase_2_uppercase;
    }

    private ReplacementTransformationMethod transformationMethod = new ReplacementTransformationMethod() {
        @Override
        protected char[] getOriginal() {
            return new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        }

        @Override
        protected char[] getReplacement() {
            return new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        }
    };

    @Override
    protected void initView() {
        EditText mEtInputBox = findViewById(R.id.et_lowercase2Uppercase_content);
        final TextView mTvLogcat = findViewById(R.id.tv_lowercase2Uppercase_logcat);

        mEtInputBox.setTransformationMethod(transformationMethod);

        mEtInputBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mTvLogcat.setText(s.toString());
            }
        });

        TextView mTextView = findViewById(R.id.tv_lowercase2Uppercase_textView);
        mTextView.setTransformationMethod(transformationMethod);
        mTextView.setText("aBcDeFgHiJkLmNoPqRsTuVwXyZ1234566789./.,!@#$%^&*()");
    }
}
