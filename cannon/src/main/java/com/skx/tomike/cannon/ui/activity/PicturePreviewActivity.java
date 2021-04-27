package com.skx.tomike.cannon.ui.activity;

import androidx.viewpager.widget.ViewPager;

import com.skx.tomike.cannon.R;
import com.skx.tomike.cannon.bean.IPicture;
import com.skx.tomike.cannon.ui.adapter.PicturePreviewAdapter;
import com.skx.common.base.SkxBaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述 : 图片预览
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/20 8:30 PM
 */
public class PicturePreviewActivity extends SkxBaseActivity {

    private final List<IPicture> mPicturesList = new ArrayList<>();
    private int mPosition = 0;

    @Override
    protected void initParams() {
        if (getIntent().hasExtra("pictureList")) {
            ArrayList<IPicture> pictures = (ArrayList<IPicture>) getIntent().getSerializableExtra("pictureList");
            if (pictures != null) {
                mPicturesList.addAll(pictures);
            }
        }
        if (getIntent().hasExtra("position")) {
            mPosition = getIntent().getIntExtra("position", 0);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_picture_preview;
    }

    @Override
    protected void initView() {
        ViewPager mVpPictures = findViewById(R.id.vp_picturePreview_pictures);
        mVpPictures.setAdapter(new PicturePreviewAdapter(mPicturesList));
        mVpPictures.setCurrentItem(mPosition);
    }

}
