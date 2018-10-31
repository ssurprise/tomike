package com.skx.tomike.activity.xzdz;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.widget.ImageView;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxBaseActivity;

/**
 * 滑动缩放头图
 *
 * @author shiguotao
 */
public class ScrollZoomImageActivity extends SkxBaseActivity {

    private ImageView iv_mainImage;
    private NestedScrollView mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_zoom_image);
        initializeView();
        refreshView();
        installListener();
    }

    @Override
    public void initializeView() {
        super.initializeView();

        mScrollView = (NestedScrollView) findViewById(R.id.scrollZoom_scrollView);
        iv_mainImage = (ImageView) findViewById(R.id.scrollZoom_mainImage);
    }

    @Override
    public void installListener() {
        super.installListener();
    }
}
