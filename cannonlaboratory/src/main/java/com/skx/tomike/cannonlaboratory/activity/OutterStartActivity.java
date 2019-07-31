package com.skx.tomike.cannonlaboratory.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.skx.tomike.cannonlaboratory.R;


public class OutterStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outter_start);
    }

    public void startXiaoZhu(View view) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("openapp.xzdz://xiaozhu/openpage?page=index"));
        startActivity(intent);
    }

    public void startXiaoZhu4Tenant(View view) {
        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);// 不确定
        intent.setData(Uri.parse("qn2at72s9jb3xk://"));
        startActivity(intent);
    }

    public void startTenantVideo(View view) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("tenvideo2://?action=66&from= qn2at72s9jb3xk"));
        startActivity(intent);
    }
}
