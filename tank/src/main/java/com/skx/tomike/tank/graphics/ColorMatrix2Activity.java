package com.skx.tomike.tank.graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.utils.ScreenUtilKt;
import com.skx.tomike.tank.R;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_COLORMATRIX;

/**
 * 描述 : ColorMatrix
 * 作者 : Android群英传-https://github.com/xurui1995/ColorMatrixStudy
 * 版本 : V1
 * 创建时间 : 2021/11/16 11:25 下午
 */
@Route(path = ROUTE_PATH_COLORMATRIX)
public class ColorMatrix2Activity extends SkxBaseActivity<BaseViewModel> {

    private ImageView mIvImageMatrix;
    private GridLayout mGroup;
    private Bitmap mBitmap;
    private int mEtWidth, mEtHeight;
    private final EditText[] mEts = new EditText[20];
    private final float[] mColorMatrix = new float[20];

    @Override
    protected void initParams() {
        mEtWidth = ScreenUtilKt.getScreenWidth(mActivity) / 5;
        mEtHeight = 120;
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("ColorMatrix").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_matrix;
    }

    @Override
    protected void initView() {
        mIvImageMatrix = findViewById(R.id.imageMatrix_image);
        mGroup = findViewById(R.id.imageMatrix_gridLayout);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_03);
        mIvImageMatrix.setImageBitmap(mBitmap);

        addEts();
        initMatrix();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    // 获取矩阵值
    private void getMatrix() {
        for (int i = 0; i < 20; i++) {
            mColorMatrix[i] = Float.valueOf(mEts[i].getText().toString());
        }
    }

    // 将矩阵值设置到图像
    private void setImageMatrix() {
        Bitmap bmp = Bitmap.createBitmap(
                mBitmap.getWidth(),
                mBitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(mColorMatrix);

        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        mIvImageMatrix.setImageBitmap(bmp);
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

    // 添加EditText
    private void addEts() {
        for (int i = 0; i < 20; i++) {
            EditText editText = new EditText(this);
            mEts[i] = editText;
            mGroup.addView(editText, mEtWidth, mEtHeight);
        }
    }

    // 初始化颜色矩阵为初始状态
    private void initMatrix() {
        for (int i = 0; i < 20; i++) {
            if (i % 6 == 0) {
                mEts[i].setText(String.valueOf(1));
            } else {
                mEts[i].setText(String.valueOf(0));
            }
        }
    }
}
