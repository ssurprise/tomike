package com.skx.tomike.tanklaboratory.widget.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;

import java.util.ArrayList;
import java.util.List;

public class LightDarkTextActivity extends SkxBaseActivity {

    private boolean isDarkStatus;
    private AppCompatButton btnSwitch;

    private final List<AppCompatEditText> editTexts = new ArrayList<>(4);
    private int index = 0;

    @Override
    protected void initParams() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_light_dark_text;
    }

    @Override
    protected void subscribeEvent() {

    }

    @Override
    protected void configHeaderTitleView(@NonNull TextView title) {
        super.configHeaderTitleView(title);
        title.setText("EditText 明暗文切换");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                    for (AppCompatEditText et:editTexts) {
                        et.setTransformationMethod(new PasswordCharSequenceStyle());
                        et.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    }
                } else {
                    btnSwitch.setText("暗文");
                    for (AppCompatEditText et:editTexts) {
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

    public class PasswordCharSequenceStyle extends PasswordTransformationMethod {

        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return new PasswordCharSequence(source);
        }

        private class PasswordCharSequence implements CharSequence {
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
