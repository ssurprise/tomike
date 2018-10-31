package com.skx.tomike.activity.function;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxBaseActivity;
import com.skx.tomikecommonlibrary.utils.LoadImageUtil;

public class ImageLoadActivity extends SkxBaseActivity {
    ImageView im;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        refreshView();
    }

    @Override
    public void initializeView() {
        super.initializeView();

        setContentView(R.layout.activity_image_load_test);
        im = findViewById(R.id.imgLoad_imageView);
    }

    @Override
    public void refreshView() {
        super.refreshView();


        Bitmap bitmap = LoadImageUtil.decodeSampledBitmapFromResource(getResources(), R.drawable.big_img_5760_3840, 300, 300);
        im.setImageBitmap(bitmap);
    }
}
