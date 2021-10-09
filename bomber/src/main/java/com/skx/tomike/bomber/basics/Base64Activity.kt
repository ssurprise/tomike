package com.skx.tomike.bomber.basics;

import android.util.Base64;
import android.widget.EditText;
import android.widget.TextView;

import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.bomber.R;

public class Base64Activity extends SkxBaseActivity<BaseViewModel> {

    private EditText mEvOriginalVal;
    private TextView mTvResult;

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("Base64 加密/解密").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base64_encode;
    }

    @Override
    protected void initView() {
        mEvOriginalVal = findViewById(R.id.et_base64_original_value);
        mTvResult = findViewById(R.id.tv_base64_result);


        findViewById(R.id.tv_base64_encoder).setOnClickListener(v -> {
            String oriVal = mEvOriginalVal.getText().toString();
            String encodeResult;
            try {
                encodeResult = Base64.encodeToString(oriVal.getBytes(), Base64.DEFAULT);
            } catch (Exception e) {
                e.printStackTrace();
                encodeResult = e.getMessage();
            }
            mTvResult.setText(encodeResult);
        });

        findViewById(R.id.tv_base64_decoder).setOnClickListener(v -> {
            String oriVal = mEvOriginalVal.getText().toString();
            String decodeResult;
            try {
                decodeResult = new String(Base64.decode(oriVal, Base64.DEFAULT));
            } catch (Exception e) {
                e.printStackTrace();
                decodeResult = e.getMessage();
            }
            mTvResult.setText(decodeResult);
        });
    }
}
