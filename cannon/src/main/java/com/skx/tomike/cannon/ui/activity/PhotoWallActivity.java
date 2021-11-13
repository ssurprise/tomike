package com.skx.tomike.cannon.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.skx.common.base.BaseViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.widget.GridSpaceItemDecoration;
import com.skx.tomike.cannon.R;
import com.skx.tomike.cannon.bean.PhotoUpImageBucket;
import com.skx.tomike.cannon.ui.adapter.PhotoWallAdapter;

import static com.skx.tomike.cannon.RouteConstantsKt.ROUTE_PATH_photo_wall;

/**
 * 描述 : 照片墙
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/19 4:08 PM
 */
@Route(path = ROUTE_PATH_photo_wall)
public class PhotoWallActivity extends SkxBaseActivity<BaseViewModel> {

    private PhotoWallAdapter mAdapter;
    private PhotoUpImageBucket mPhotoAlbum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPhotoAlbum == null) {
            finish();
            return;
        }
        refreshView();
    }

    @Override
    protected void initParams() {
        if (getIntent().hasExtra("imagelist")) {
            mPhotoAlbum = (PhotoUpImageBucket) getIntent().getSerializableExtra("imagelist");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_wall;
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText(mPhotoAlbum.bucketName).create();
    }

    @Override
    protected void initView() {
        RecyclerView rvPhotoWall = findViewById(R.id.rv_photoWall_photos);
        rvPhotoWall.setLayoutManager(new GridLayoutManager(this, 4));
        rvPhotoWall.addItemDecoration(new GridSpaceItemDecoration(4, 15, 15));
        rvPhotoWall.setAdapter(mAdapter = new PhotoWallAdapter());
        mAdapter.setOnItemClickListener((position, photoUpImageItem) -> {
            Intent intent = new Intent(mActivity, PicturePreviewActivity.class);
            intent.putExtra("pictureList", mPhotoAlbum.imageList);
            intent.putExtra("position", position);
            startActivity(intent);
        });
    }

    private void refreshView() {
        mAdapter.setArrayList(mPhotoAlbum.imageList);
    }
}
