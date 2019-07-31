
package com.skx.tomike.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.skx.tomike.R;
import com.skx.tomike.animlaboratory.view.TranslateImageView;

public class MatrixImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_martix_image);

        TranslateImageView mImage = findViewById(R.id.matrixImage_mainImage);
        mImage.setImageResource(R.drawable.image_01);
        mImage.setPosition(TranslateImageView.Position.RIGHT);
    }
    /**
     *
     * ScaleType.CENTER 图片居中显示。查看源码 可以看到只居中，并没有进行缩放
     if (ScaleType.CENTER == mScaleType) {
     // Center bitmap in view, no scaling.
     mDrawMatrix = mMatrix;
     mDrawMatrix.setTranslate(Math.round((vwidth - dwidth) * 0.5f),
     Math.round((vheight - dheight) * 0.5f));
     }
     */
    /**
     * ImageView.ScaleType.FIT_END 扩大，靠右、下展示
     */
}
