package com.skx.tomike.bomber.basics;

import android.widget.EditText;
import android.widget.TextView;

import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.bomber.R;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlEncodeActivity extends SkxBaseActivity<BaseViewModel> {

    private static final String URL_ENCODE = "UTF-8";

    private EditText mEvOriginalVal;
    private TextView mTvResult;

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("URL编码").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_url_encode;
    }

    @Override
    protected void initView() {
        mEvOriginalVal = findViewById(R.id.et_urlEncode_original_value);
        mTvResult = findViewById(R.id.tv_urlEncode_result);


        findViewById(R.id.tv_urlEncode_encoder).setOnClickListener(v -> {
            String oriVal = mEvOriginalVal.getText().toString();
            String encodeResult;
            try {
                encodeResult = URLEncoder.encode(oriVal, URL_ENCODE);
            } catch (Exception e) {
                e.printStackTrace();
                encodeResult = e.getMessage();
            }
            mTvResult.setText(encodeResult);
        });

        findViewById(R.id.tv_urlEncode_decoder).setOnClickListener(v -> {
            String oriVal = mEvOriginalVal.getText().toString();
            String decodeResult;
            try {
                decodeResult = URLDecoder.decode(oriVal, URL_ENCODE);
            } catch (Exception e) {
                e.printStackTrace();
                decodeResult = e.getMessage();
            }
            mTvResult.setText(decodeResult);
        });
    }
}
