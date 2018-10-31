package com.skx.tomike.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.skx.tomike.R;

public class ColorMatrix2Activity extends AppCompatActivity {


    private ImageView imageMatrix_image;
    private GridLayout mGroup;
    private Bitmap mBitmap;
    private int mEtWidth, mEtHeight;
    private EditText[] mEts = new EditText[20];
    private float[] mColorMatrix = new float[20];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_matrix);

        imageMatrix_image = (ImageView) findViewById(R.id.imageMatrix_image);
        mGroup = (GridLayout) findViewById(R.id.imageMatrix_gridLayout);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_03);
//        imageMatrix_image.setImageBitmap(ImageHelper.handlerImageOld(mBitmap));

        mGroup.post(new Runnable() {
            @Override
            public void run() {
                mEtWidth = mGroup.getWidth() / 5;
                mEtHeight = mGroup.getHeight() / 4;
                addEts();
                initMatrix();
            }
        });
    }

    private void addEts() {
        for (int i = 0; i < 20; i++) {
            EditText editText = new EditText(this);
            mEts[i] = editText;
            mGroup.addView(editText, mEtWidth, mEtHeight);
        }
    }

    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                mEts[i].setText(String.valueOf(1));
            } else {
                mEts[i].setText(String.valueOf(0));
            }
        }
    }

    public void getMatrix() {
        for (int i = 0; i < 20; i++) {
            mColorMatrix[i] = Float.valueOf(mEts[i].getText().toString());
        }
    }

    public void setImageMatrix(){
        Bitmap bmp = Bitmap.createBitmap(mBitmap.getWidth(),mBitmap.getHeight(),Bitmap.Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(mColorMatrix);

        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(bmp,0,0,paint);
        imageMatrix_image.setImageBitmap(bmp);
    }


    public void change(View view) {
        getMatrix();
        setImageMatrix();
    }

    public void reset(View view) {
        initMatrix();
        getMatrix();
        setImageMatrix();
    }
}
