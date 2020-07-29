package com.skx.tomike.activity.xzdz;

import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxBaseActivity;
import com.skx.tomikecommonlibrary.view.ZoomImageNestedScrollingParent2;

/**
 * 滑动缩放头图
 *
 * @author shiguotao
 */
public class ScrollZoomImage2Activity extends SkxBaseActivity {

    private final String TAG = "ScrollZoomImage";

    Matrix mScaleMatrix = new Matrix();
    private final float[] matrixValues = new float[9];

    float lastScale = 1.0f;


    private ImageView iv_mainImage;
    private ZoomImageNestedScrollingParent2 mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_zoom_image2);
        initializeView();
        refreshView();
        installListener();
    }

    @Override
    public void initializeView() {
        super.initializeView();

        mScrollView = (ZoomImageNestedScrollingParent2) findViewById(R.id.scrollZoom2_scrollView);
        iv_mainImage = (ImageView) findViewById(R.id.scrollZoom2_mainImage);
        iv_mainImage.setScaleType(ImageView.ScaleType.MATRIX);
        iv_mainImage.setEnabled(false);


        mScrollView.setLayoutChangeListener(new ZoomImageNestedScrollingParent2.ScrollingLayoutChangeListener() {
            @Override
            public void layoutChange(int moveOffset, float scale) {
                Log.e(TAG, "onOverScrollingListener - moveOffset:scale - " + moveOffset + "：" + scale);

                if (lastScale != scale) {
                    lastScale = scale;
                    Matrix matrix = new Matrix();
                    matrix.postScale(lastScale, lastScale, iv_mainImage.getWidth() / 2, iv_mainImage.getHeight() / 2);
                    iv_mainImage.setImageMatrix(matrix);
                }
            }

            @Override
            public void touchEventEndListener() {
                iv_mainImage.postDelayed(new AutoScaleRunnable(1.0f, iv_mainImage.getWidth() / 2, iv_mainImage.getHeight() / 2), 16);
            }
        });

    }

    private class AutoScaleRunnable implements Runnable {
        static final float BIGGER = 1.07f;
        static final float SMALLER = 0.93f;
        private float mTargetScale;
        private float tmpScale;

        /**
         * 缩放的中心
         */
        private float x;
        private float y;

        /**
         * 传入目标缩放值，根据目标值与当前值，判断应该放大还是缩小
         *
         * @param targetScale 缩放倍数
         */
        public AutoScaleRunnable(float targetScale, float x, float y) {
            this.mTargetScale = targetScale;
            this.x = x;
            this.y = y;
            Log.e(TAG, "run -getScale(): " + getScale());
            if (getScale() < mTargetScale) {
                tmpScale = BIGGER;
            } else {
                tmpScale = SMALLER;
            }
        }

        @Override
        public void run() {
            // 进行缩放
            Log.e(TAG, "run -getScale(): " + getScale());

            Matrix matrix = new Matrix();
            matrix.postScale(tmpScale, tmpScale, x, y);
            iv_mainImage.setImageMatrix(matrix);

            final float currentScale = getScale();
            // 如果值在合法范围内，继续缩放
            if (((tmpScale > 1f) && (currentScale < mTargetScale))
                    || ((tmpScale < 1f) && (mTargetScale < currentScale))) {
                iv_mainImage.postDelayed(this, 16);
            } else
            // 设置为目标的缩放比例
            {
//                float deltaScale = mTargetScale / currentScale;
                Matrix matrix2 = new Matrix();
                matrix2.postScale(1.0f, 1.0f, x, y);
                iv_mainImage.setImageMatrix(matrix2);
            }
        }
    }

    /**
     * 获得当前的缩放比例
     *
     * @return
     */
    public final float getScale() {
        mScaleMatrix = iv_mainImage.getImageMatrix();
        mScaleMatrix.getValues(matrixValues);
        return matrixValues[Matrix.MSCALE_X];
    }
}
