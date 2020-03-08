package com.skx.tomike.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.skx.tomike.R;

public class GetTopTestActivity extends AppCompatActivity {

    LinearLayout activity_get_top_text;
    TextView getTop_1;
    TextView getTop_2;
    TextView getTop_3;
    TextView getTop_4;
    TextView getTop_5;
    TextView getTop_6;
    TextView getTop_7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_top_text);
        activity_get_top_text = findViewById(R.id.activity_get_top_text);

        getTop_1 = findViewById(R.id.getTop_1);
        getTop_2 = findViewById(R.id.getTop_2);
        getTop_3 = findViewById(R.id.getTop_3);
        getTop_4 = findViewById(R.id.getTop_4);
        getTop_5 = findViewById(R.id.getTop_5);
        getTop_6 = findViewById(R.id.getTop_6);
        getTop_7 = findViewById(R.id.getTop_7);
    }

    public void test(View view) {
        Log.e("container_height", activity_get_top_text.getHeight() + "");
        getTop_4.setVisibility(View.GONE);
        Log.e("container_height", activity_get_top_text.getHeight() + "");
        Log.e("1", getTop_1.getTop() + "");
        Log.e("2", getTop_2.getTop() + "");
        Log.e("3", getTop_3.getTop() + "");
        Log.e("4", getTop_4.getTop() + "");
        Log.e("5", getTop_5.getTop() + "");
        Log.e("6", getTop_6.getTop() + "");
        Log.e("7", getTop_7.getTop() + "");
    }

    public void test1(View view) {
//        Log.e("container_height", activity_get_top_text.getHeight() + "");
//        getTop_4.setVisibility(View.GONE);
//        Log.e("container_height", activity_get_top_text.getHeight() + "");
        Log.e("1", getTop_1.getTop() + "");
        Log.e("2", getTop_2.getTop() + "");
        Log.e("3", getTop_3.getTop() + "");
        Log.e("4", getTop_4.getTop() + "");
        Log.e("5", getTop_5.getTop() + "");
        Log.e("6", getTop_6.getTop() + "");
        Log.e("7", getTop_7.getTop() + "");
    }
}
