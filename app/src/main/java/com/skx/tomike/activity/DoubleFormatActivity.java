package com.skx.tomike.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.skx.tomike.R;
import com.skx.tomikecommonlibrary.utils.DoubleFormatToolKt;

public class DoubleFormatActivity extends AppCompatActivity {

    private EditText doubleFormat_editText;
    private TextView doubleFormat_result;
    private TextView doubleFormat_result1;
    private TextView doubleFormat_result2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_format);

        doubleFormat_editText = (EditText) findViewById(R.id.doubleFormat_editText);
        doubleFormat_result = (TextView) findViewById(R.id.doubleFormat_result);
        doubleFormat_result1 = (TextView) findViewById(R.id.doubleFormat_result1);
        doubleFormat_result2 = (TextView) findViewById(R.id.doubleFormat_result2);


        doubleFormat_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                // 直接运算  1.11+0.09 = 1.2000000000000000002
                // 直接运算  1.2/3 = 0.3999999999999999997

                doubleFormat_result1.setText("1.11+0.09=" + DoubleFormatToolKt.add(1.11, 0.09));// 1.11   ??????????????

                Log.e("1.11+0.09=", (1.11 + 0.09) + "");
                doubleFormat_result2.setText("1.2/3 = " + DoubleFormatToolKt.divide(1.2, 3, 2));// 1.12
                Log.e("1.2/3=", (1.2 / 3) + "");
            }
        });
    }
}
