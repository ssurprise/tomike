package com.skx.tomike.tanklaboratory.animation.activity;

import android.graphics.Matrix;
import android.util.Log;
import android.widget.ImageView;

import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.view.OverScrollView;
import com.skx.tomike.tanklaboratory.R;

/**
 * 滑动缩放头图
 *
 * @author shiguotao
 */
public class ScrollZoomImage3Activity extends SkxBaseActivity<BaseViewModel> {

    private final String TAG = "ScrollZoomImage";

    private ImageView iv_mainImage;
    private OverScrollView mScrollView;

    float lastScale = 1.0f;

    @Override
    protected void initParams() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_scroll_zoom_image3;
    }

    @Override
    protected void initView() {
        mScrollView = findViewById(R.id.scrollZoom3_scrollView);
        iv_mainImage = findViewById(R.id.scrollZoom3_mainImage);
        iv_mainImage.setScaleType(ImageView.ScaleType.MATRIX);
        iv_mainImage.setEnabled(false);


        mScrollView.setScrollingLayoutChangeListener(new OverScrollView.OnOverScrollingListener() {
            @Override
            public void onOverScrollingListener(int moveOffset, float scale) {
                Log.e(TAG, "onOverScrollingListener - moveOffset:scale - " + moveOffset + "：" + scale);

                scale = 1 + scale;
                if (lastScale != scale) {
                    lastScale = scale;
                    Matrix matrix = new Matrix();
                    matrix.postScale(lastScale, lastScale, iv_mainImage.getWidth() / 2, iv_mainImage.getHeight() / 2);
                    iv_mainImage.setImageMatrix(matrix);
                }
            }

            @Override
            public void touchEventEndListener() {
            }
        });
    }
}
