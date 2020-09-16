package com.skx.tomike.tanklaboratory.widget.activity;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.skx.tomike.tanklaboratory.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述 : EditText明暗文切换
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/4/1 7:46 PM
 */
public class LightDarkTextActivity extends SkxBaseActivity<BaseViewModel> {

    private boolean isDarkStatus;
    private AppCompatTextView btnSwitch;

    private final List<AppCompatEditText> editTexts = new ArrayList<>(4);
    private int index = 0;

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("EditText 明暗文切换").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_light_dark_text;
    }

    @Override
    protected void initView() {
        AppCompatEditText input = findViewById(R.id.lightDarkText_input);
        AppCompatEditText input1 = findViewById(R.id.lightDarkText_input1);
        AppCompatEditText input2 = findViewById(R.id.lightDarkText_input2);
        AppCompatEditText input3 = findViewById(R.id.lightDarkText_input3);
        editTexts.add(input);
        editTexts.add(input1);
        editTexts.add(input2);
        editTexts.add(input3);

        btnSwitch = findViewById(R.id.lightDarkText_switch);
        btnSwitch.setText("暗文");

        input.setOnEditorActionListener(actionListener);
        input1.setOnEditorActionListener(actionListener);
        input2.setOnEditorActionListener(actionListener);
        input3.setOnEditorActionListener(actionListener);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.length();
                if (length >= 1 && index < editTexts.size() - 1) {
                    (editTexts.get(++index)).requestFocus(EditorInfo.IME_ACTION_NEXT);
                }
            }
        };

        input.addTextChangedListener(textWatcher);
        input1.addTextChangedListener(textWatcher);
        input2.addTextChangedListener(textWatcher);
        input3.addTextChangedListener(textWatcher);

        btnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDarkStatus = !isDarkStatus;
                if (isDarkStatus) {
                    btnSwitch.setText("明文");
                    for (AppCompatEditText et : editTexts) {
                        et.setTransformationMethod(new PasswordCharSequenceStyle());
                        et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                } else {
                    btnSwitch.setText("暗文");
                    for (AppCompatEditText et : editTexts) {
                        et.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    }
                }
//                Selection.setSelection(input.getEditableText(), input.length());
            }
        });
    }

    private TextView.OnEditorActionListener actionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            Log.e("KeyCode", event.getKeyCode() + "");
            Log.e("action", event.getAction() + "");
            return false;
        }
    };

    public static class PasswordCharSequenceStyle extends PasswordTransformationMethod {

        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return new PasswordCharSequence(source);
        }

        private static class PasswordCharSequence implements CharSequence {
            private CharSequence mSource;

            public PasswordCharSequence(CharSequence source) {
                mSource = source;
            }

            public char charAt(int index) {
                return '*';
            }

            public int length() {
                return mSource.length();
            }

            public CharSequence subSequence(int start, int end) {
                return mSource.subSequence(start, end);
            }
        }
    }
}
