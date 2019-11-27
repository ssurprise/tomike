package com.skx.tomike.viewlaboratory.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
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

import com.skx.tomike.viewlaboratory.R;

import java.util.ArrayList;
import java.util.List;

public class LightDarkTextActivity extends AppCompatActivity {

    boolean isDarkStatus;
    AppCompatEditText input;
    AppCompatEditText input1;
    AppCompatEditText input2;
    AppCompatEditText input3;
    AppCompatButton btnSwitch;

    List<AppCompatEditText> editTexts = new ArrayList<>(4);
    int index = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_dark_text);

        input = findViewById(R.id.lightDarkText_input);
        input1 = findViewById(R.id.lightDarkText_input1);
        input2 = findViewById(R.id.lightDarkText_input2);
        input3 = findViewById(R.id.lightDarkText_input3);
        editTexts.add(input);
        editTexts.add(input1);
        editTexts.add(input2);
        editTexts.add(input3);

        btnSwitch = findViewById(R.id.lightDarkText_switch);
        btnSwitch.setText("暗文");

        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Log.e("KeyCode", event.getKeyCode() + "");
                Log.e("action", event.getAction() + "");
                return false;
            }
        });

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

                } else if (length == 0) {

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
                    input.setTransformationMethod(new PasswordCharSequenceStyle());
                    input.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    btnSwitch.setText("暗文");
                    input.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }

                Selection.setSelection(input.getEditableText(), input.length());

                Log.e("输入框内容", input.getEditableText().toString());
            }
        });
    }

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
