package com.skx.tomike.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.skx.tomike.R;
import com.skx.tomikecommonlibrary.utils.ImageHelper;

public class ColorMatrixActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private ImageView main_img;
    private SeekBar seekBar_hun, seekBar_saturation, seekBar_lum;
    private static int MAX_VALUE = 255;
    private static int MID_VALUE = 127;

    private float mHue, mSaturation, mLum;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matrix);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_02);

        main_img = (ImageView) findViewById(R.id.main_img);
        seekBar_hun = (SeekBar) findViewById(R.id.seekBar_hun);
        seekBar_saturation = (SeekBar) findViewById(R.id.seekBar_saturation);
        seekBar_lum = (SeekBar) findViewById(R.id.seekBar_lum);

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
        switch (seekBar.getId()) {
            case R.id.seekBar_hun:
                mHue = (progress - MID_VALUE) * 1.0f / MID_VALUE * 180;
                break;
            case R.id.seekBar_saturation:
                mSaturation = progress * 1.0f / MID_VALUE;
                break;
            case R.id.seekBar_lum:
                mLum = progress * 1.0f / MID_VALUE;
                break;
        }

//        ImageHelper.handleImageEffect(bitmap, mHue, mSaturation, mLum);
        main_img.setImageBitmap(ImageHelper.INSTANCE.handleImageEffect(bitmap, mHue, mSaturation, mLum));
//        switch (seekBar.getId()) {
//            case R.id.seekBar_hun:
//                mHue = (MAX_VALUE - MID_VALUE)
//                break;
//            case R.id.seekBar_hun:
//                break;
//            case R.id.seekBar_hun:
//                break;
//            default:
//                break;
//        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
