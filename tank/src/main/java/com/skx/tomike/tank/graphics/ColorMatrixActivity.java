package com.skx.tomike.tank.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.utils.ImageHelperKt;
import com.skx.tomike.tank.R;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_HSL;

/**
 * 描述 : 图像处理 - 修改图片HSL(色度、亮度、饱和度)
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2021/11/14 10:37 下午
 */
@Route(path = ROUTE_PATH_HSL)
public class ColorMatrixActivity extends SkxBaseActivity<BaseViewModel> implements SeekBar.OnSeekBarChangeListener {

    private ImageView main_img;
    private static final int MAX_VALUE = 255;
    private static final int MID_VALUE = 127;

    private float mHue, mSaturation, mLum;
    private Bitmap bitmap;

    @Override
    protected void initParams() {
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("ColorMatrix").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_color_matrix;
    }

    @Override
    protected void initView() {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_02);

        main_img = findViewById(R.id.main_img);
        SeekBar seekBar_hun = findViewById(R.id.seekBar_hun);
        SeekBar seekBar_saturation = findViewById(R.id.seekBar_saturation);
        SeekBar seekBar_lum = findViewById(R.id.seekBar_lum);

        seekBar_hun.setOnSeekBarChangeListener(this);
        seekBar_saturation.setOnSeekBarChangeListener(this);
        seekBar_lum.setOnSeekBarChangeListener(this);

        seekBar_hun.setMax(MAX_VALUE);
        seekBar_saturation.setMax(MAX_VALUE);
        seekBar_lum.setMax(MAX_VALUE);

        seekBar_hun.setProgress(MID_VALUE);
        seekBar_saturation.setProgress(MID_VALUE);
        seekBar_lum.setProgress(MID_VALUE);
        main_img.setImageBitmap(bitmap);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int id = seekBar.getId();
        if (id == R.id.seekBar_hun) {
            mHue = (progress - MID_VALUE) * 1.0f / MID_VALUE * 180;
        } else if (id == R.id.seekBar_saturation) {
            mSaturation = progress * 1.0f / MID_VALUE;
        } else if (id == R.id.seekBar_lum) {
            mLum = progress * 1.0f / MID_VALUE;
        }

        main_img.setImageBitmap(ImageHelperKt.handleImageEffect(bitmap, mHue, mSaturation, mLum));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
