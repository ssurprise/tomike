package com.skx.tomike.cannonlaboratory.ui.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.skx.tomike.cannonlaboratory.R;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PicturePreviewActivity extends SkxBaseActivity {

    private ImageView imgv;

    @Override
    protected void initParams() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_picture_preview;
    }

    @Override
    protected void subscribeEvent() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imgv = findViewById(R.id.preview_imgv);

    }
}
