package com.skx.tomike.tanklaboratory.widget.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.skx.tomike.tanklaboratory.R;


public class SnackBarActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);

        tv_label = findViewById(R.id.snackbar_label);

        Button btn = findViewById(R.id.snackbar_btn);
        if (btn != null) {
            btn.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.snackbar_btn) {// 父容器 ,提示信息，持续时间
            // setAction() 用于给SnackBar设定一个Action， 右侧显示的东西，点击之后会回调OnclickListener中的Onclick方法
            Snackbar.make(v, "出现了出现了", Snackbar.LENGTH_LONG).setAction("真棒", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tv_label.setText("这就对了嘛！");
                }
            }).setCallback(null).show();
        }
    }
}
