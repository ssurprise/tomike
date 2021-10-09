package com.skx.tomike.bomber.basics;

import android.util.Log;

import com.skx.common.base.TitleConfig;
import com.skx.tomike.bomber.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlEncodeActivity extends SkxBaseActivity<BaseViewModel> {

    private static final String URL_ENCODE = "UTF-8";

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("采用utf-8编码/解码").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_url_encode;
    }

    @Override
    protected void initView() {
        String test1 = "12*8";
        String test2 = "556";
        String test3 = "abck.@";
        String test4 = "才发呢过@#￥%&*（）";


        try {
            Log.e("test1", URLEncoder.encode(test1, URL_ENCODE));
            Log.e("test2", URLEncoder.encode(test2, URL_ENCODE));
            Log.e("test3", URLEncoder.encode(test3, URL_ENCODE));
            Log.e("test4", URLEncoder.encode(test4, URL_ENCODE));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
