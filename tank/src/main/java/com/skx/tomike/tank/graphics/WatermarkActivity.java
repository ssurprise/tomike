package com.skx.tomike.tank.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.tomike.tank.R;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_WATER_MARK;

@Route(path = ROUTE_PATH_WATER_MARK)
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

        Bitmap newBitmap2 = getOverlapBitmap2(targetBitmap02, overlapBitmap);
        imageView12.setImageBitmap(newBitmap2);

        Bitmap newBitmap3 = getOverlapBitmap3(targetBitmap03);
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
        return copyBitmap;
    }


    public Bitmap getOverlapBitmap2(Bitmap targetBitmap, Bitmap overlapBitmap) {
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

        canvas.drawBitmap(overlapBitmap, Math.abs(w - w_2) / 2 - 150, Math.abs(h - h_2) / 2 - 150, null);
        return copyBitmap;
    }

    public Bitmap getOverlapBitmap3(Bitmap targetBitmap) {
        if (targetBitmap == null) {
            throw new IllegalArgumentException("目标bitmap不能为空");
        }

        Bitmap copyBitmap = targetBitmap.copy(targetBitmap.getConfig(), true);
        Canvas canvas = new Canvas(copyBitmap);

        int w = targetBitmap.getWidth();
        int h = targetBitmap.getHeight();

        Paint paint = new Paint();
        paint.setFlags(Paint.FAKE_BOLD_TEXT_FLAG | Paint.UNDERLINE_TEXT_FLAG);
        paint.setAntiAlias(true);// 抗锯尺
        paint.setColor(Color.RED);
        paint.setStrokeWidth(15);
        paint.setTextSize(60);

        canvas.save();
        canvas.rotate(-45, w / 2, h / 2);
        canvas.drawText("惊喜", w / 2 + 50, h / 2 - 80, paint);
        canvas.restore();
        return copyBitmap;
    }
}
