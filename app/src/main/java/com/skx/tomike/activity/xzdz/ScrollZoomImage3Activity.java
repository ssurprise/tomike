package com.skx.tomike.activity.xzdz;

import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxBaseActivity;
import com.skx.tomikecommonlibrary.view.OverScrollView;

/**
 * 滑动缩放头图
 *
 * @author shiguotao
 */
public class ScrollZoomImage3Activity extends SkxBaseActivity {

    private final String TAG = "ScrollZoomImage";

    private ImageView iv_mainImage;
    private OverScrollView mScrollView;

    float lastScale = 1.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_zoom_image3);
        initializeView();
        refreshView();
        installListener();
    }

    @Override
    public void initializeView() {
        super.initializeView();

        mScrollView = (OverScrollView) findViewById(R.id.scrollZoom3_scrollView);
        iv_mainImage = (ImageView) findViewById(R.id.scrollZoom3_mainImage);
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
