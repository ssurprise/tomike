package com.skx.tomike.cannonlaboratory.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.skx.tomike.cannonlaboratory.R;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;
import com.skx.tomikecommonlibrary.utils.LoadImageUtil;


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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImageView ivImage = findViewById(R.id.imgLoad_imageView);
        // 注意：图片的压缩一个耗时操作
        long l = System.currentTimeMillis();
        Bitmap bitmap = LoadImageUtil.INSTANCE.decodeSampledBitmapFromResource(getResources(),
                R.drawable.big_img_5760_3840, 300, 300);
        long l2 = System.currentTimeMillis();
        ivImage.setImageBitmap(bitmap);

        Log.e(TAG, "bitmap 压缩时间：" + (l2 - l));
    }
}
