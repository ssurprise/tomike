package com.skx.tomike.activity.function;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxBaseActivity;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PicturePreviewActivity extends SkxBaseActivity {

    private ImageView imgv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {// @Nullable 注解表示参数可为 null
        super.onCreate(savedInstanceState);
        initializeView();
    }

    @Override
    public void initializeView() {
        setContentView(R.layout.activity_picture_preview);
        imgv = (ImageView) findViewById(R.id.preview_imgv);
    }
}
