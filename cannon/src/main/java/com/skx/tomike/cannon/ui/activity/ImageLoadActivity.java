package com.skx.tomike.cannon.ui.activity;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.skx.tomike.cannon.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.utils.LoadImageUtilKt;


/**
 * 描述 : 大图压缩加载。
 * <p>
 * 主要知识点：Bitmap 的 inSampleSize，对大图进行大小压缩。
 * 关键属性：android.graphics.BitmapFactory.Options#inJustDecodeBounds、android.graphics.BitmapFactory.Options#inSampleSize
 * <p>
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/23 3:56 PM
 */
public class ImageLoadActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("大图压缩加载").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_load_test;
    }

    @Override
    protected void initView() {
        ImageView ivImage = findViewById(R.id.imgLoad_imageView);
        // 注意：图片的压缩一个耗时操作
        long l = System.currentTimeMillis();
        Bitmap bitmap = LoadImageUtilKt.decodeSampledBitmapFromResource(getResources(),
                R.drawable.big_img_5760_3840, 300, 300);
        long l2 = System.currentTimeMillis();
        ivImage.setImageBitmap(bitmap);

        Log.e(TAG, "bitmap 压缩时间：" + (l2 - l));
    }
}
