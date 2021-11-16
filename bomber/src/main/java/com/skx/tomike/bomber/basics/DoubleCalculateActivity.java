package com.skx.tomike.bomber.basics;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.utils.DoubleFormatToolKt;
import com.skx.tomike.bomber.R;

import static com.skx.tomike.bomber.RouteConstantsKt.ROUTE_PATH_FLOAT_CALCULATE;

@Route(path = ROUTE_PATH_FLOAT_CALCULATE)
public class DoubleCalculateActivity extends SkxBaseActivity<BaseViewModel> {

    private EditText doubleFormat_editText;
    private TextView doubleFormat_result;
    private TextView doubleFormat_result1;
    private TextView doubleFormat_result2;

    @Override
    protected void initParams() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_double_format;
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("精度计算-浮点数").create();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doubleFormat_editText = findViewById(R.id.doubleFormat_editText);
        doubleFormat_result = findViewById(R.id.doubleFormat_result);
        doubleFormat_result1 = findViewById(R.id.doubleFormat_result1);
        doubleFormat_result2 = findViewById(R.id.doubleFormat_result2);

        doubleFormat_result.setText("1.11+0.09 =" + (1.11 + 0.09));

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
