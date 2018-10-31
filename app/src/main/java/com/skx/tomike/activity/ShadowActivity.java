package com.skx.tomike.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.skx.tomike.R;

public class ShadowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shadow);

        /*
        1. CardView 的投影颜色是默认的，并没有提供直接的修改方法。
        2. CardView 的投影是画在父View 上的。如果父View 直接包裹CardView 是不显示投影的

        3. 通过 layer-list 生成生成的drawable 也是不可能超过view的大小来渲染内容的

        4. 重写父View 的onDraw 方法通过 setShadowLayer 的方法

         */

    }
}
