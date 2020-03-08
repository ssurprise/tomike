package com.skx.tomike.cannonlaboratory.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.skx.tomike.cannonlaboratory.R;
import com.skx.tomikecommonlibrary.utils.LoadImageUtil;


public class ImageLoadActivity extends AppCompatActivity {
    ImageView im;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        refreshView();
    }

    private void initializeView() {
        setContentView(R.layout.activity_image_load_test);
        im = findViewById(R.id.imgLoad_imageView);
    }

    private void refreshView() {
        Bitmap bitmap = LoadImageUtil.INSTANCE.decodeSampledBitmapFromResource(getResources(), R.drawable.big_img_5760_3840, 300, 300);
        im.setImageBitmap(bitmap);
    }
}
