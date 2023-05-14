package com.skx.tomike.tank.animation.activity;

import android.graphics.Matrix;
import android.util.Log;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.widget.OverScrollView;
import com.skx.tomike.tank.R;

import static com.skx.tomike.tank.RouteConstantsKt.ROUTE_PATH_SCROLL_ZOOM2;

/**
 * 滑动缩放头图
 *
 * @author shiguotao
 */
@Route(path = ROUTE_PATH_SCROLL_ZOOM2)
public class ScrollZoomImage3Activity extends SkxBaseActivity<BaseViewModel<?>> {

    private final String TAG = "ScrollZoomImage";

    private ImageView iv_mainImage;
    private OverScrollView mScrollView;

    float lastScale = 1.0f;

    @Override
    protected void initParams() {

    }

    @Override
    protected int layoutId() {
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
