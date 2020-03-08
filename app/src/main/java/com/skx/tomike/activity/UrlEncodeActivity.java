package com.skx.tomike.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.skx.tomike.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlEncodeActivity extends AppCompatActivity {

    private static final String URL_ENCODE = "UTF-8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url_encode);

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
