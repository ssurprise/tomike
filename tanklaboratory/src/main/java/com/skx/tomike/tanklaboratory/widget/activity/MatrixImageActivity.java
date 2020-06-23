
package com.skx.tomike.tanklaboratory.widget.activity;

import com.skx.tomike.tanklaboratory.R;
import com.skx.tomike.tanklaboratory.widget.view.TranslateImageView;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;
import com.skx.tomikecommonlibrary.base.TitleConfig;

/**
 * 描述 : 自定义显示方向的ImageView
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/6/23 2:16 PM
 */
public class MatrixImageActivity extends SkxBaseActivity<BaseViewModel> {

    @Override
    protected void initParams() {

    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("自定义Image 显示位置的ImageView").create();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_martix_image;
    }

    @Override
    protected void initView() {
        TranslateImageView mImage = findViewById(R.id.matrixImage_mainImage);
        mImage.setImageResource(R.drawable.image_01);
        // ImageView.ScaleType.FIT_END 扩大，靠右、下展示
        mImage.setPosition(TranslateImageView.Position.RIGHT);
    }

    /*
     *
     * ScaleType.CENTER 图片居中显示。查看源码 可以看到只居中，并没有进行缩放
     if (ScaleType.CENTER == mScaleType) {
     // Center bitmap in view, no scaling.
     mDrawMatrix = mMatrix;
     mDrawMatrix.setTranslate(Math.round((vwidth - dwidth) * 0.5f),
     Math.round((vheight - dheight) * 0.5f));
     }
     */
}
