package com.skx.tomike.tanklaboratory.widget.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;
import com.skx.tomikecommonlibrary.utils.ImageHelperKt;

public class ColorMatrixActivity extends SkxBaseActivity<BaseViewModel> implements SeekBar.OnSeekBarChangeListener {

    private ImageView main_img;
    private static int MAX_VALUE = 255;
    private static int MID_VALUE = 127;

    private float mHue, mSaturation, mLum;
    private Bitmap bitmap;

    @Override
    protected void initParams() {}

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("修改图片色度、亮度、饱和度").create();
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