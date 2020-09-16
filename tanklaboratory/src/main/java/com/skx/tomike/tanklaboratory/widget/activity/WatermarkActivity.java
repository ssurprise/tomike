package com.skx.tomike.tanklaboratory.widget.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.skx.tomike.tanklaboratory.R;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;

public class WatermarkActivity extends SkxBaseActivity<BaseViewModel> {

    private ImageView imageView01;
    private ImageView imageView02;
    private ImageView imageView03;

    private ImageView imageView11;
    private ImageView imageView12;
    private ImageView imageView13;

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("给图片添加水印").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_watermark;
    }

    @Override
    protected void initView() {
        imageView01 = findViewById(R.id.image01);
        imageView02 = findViewById(R.id.image02);
        imageView03 = findViewById(R.id.image03);

        imageView11 = findViewById(R.id.image11);
        imageView12 = findViewById(R.id.image12);
        imageView13 = findViewById(R.id.image13);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bitmap targetBitmap01 = BitmapFactory.decodeResource(getResources(), R.drawable.image_01);
        Bitmap targetBitmap02 = BitmapFactory.decodeResource(getResources(), R.drawable.image_05);
        Bitmap targetBitmap03 = BitmapFactory.decodeResource(getResources(), R.drawable.image_07);

        imageView01.setImageBitmap(targetBitmap01);
        imageView02.setImageBitmap(targetBitmap02);
        imageView03.setImageBitmap(targetBitmap03);

        Bitmap overlapBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_collect);

        Bitmap newBitmap = getOverlapBitmap(targetBitmap01, overlapBitmap);
        imageView11.setImageBitmap(newBitmap);

        Bitmap newBitmap2 = getOverlapBitmap(targetBitmap02, overlapBitmap);
        imageView12.setImageBitmap(newBitmap2);

        Bitmap newBitmap3 = getOverlapBitmap(targetBitmap03, overlapBitmap);
        imageView13.setImageBitmap(newBitmap3);
    }

    /**
     * 对目标bitmap进行重叠。
     * （可用于添加水印）
     * 注意这里有个问题先记录下：如果设置了ImageView的scaleType，那么重叠后的bitmap 可能会存在大小问题，或者会看不到。
     *
     * @param targetBitmap  目标bitmap
     * @param overlapBitmap 覆盖的bitmap
     * @return 重叠后的bitmap
     */
    public Bitmap getOverlapBitmap(Bitmap targetBitmap, Bitmap overlapBitmap) {
        if (targetBitmap == null) {
            throw new IllegalArgumentException("目标bitmap不能为空");
        }
        if (overlapBitmap == null) {
            return targetBitmap;
        }

        Bitmap copyBitmap = targetBitmap.copy(targetBitmap.getConfig(), true);
        Canvas canvas = new Canvas(copyBitmap);

        int w = targetBitmap.getWidth();
        int h = targetBitmap.getHeight();
        int w_2 = overlapBitmap.getWidth();
        int h_2 = overlapBitmap.getHeight();

        canvas.drawBitmap(overlapBitmap, Math.abs(w - w_2) / 2, Math.abs(h - h_2) / 2, null);
        // 保存
//        canvas.save(Canvas.ALL_SAVE_FLAG);
        // 存储
//        canvas.restore();
        return copyBitmap;
    }
}
