package com.skx.tomike.cannonlaboratory.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skx.tomike.cannonlaboratory.R;
import com.skx.tomike.cannonlaboratory.bean.PhotoUpImageBucket;
import com.skx.tomike.cannonlaboratory.bean.PhotoUpImageItem;
import com.skx.tomike.cannonlaboratory.ui.adapter.PhotoWallAdapter;
import com.skx.tomike.cannonlaboratory.ui.view.GridSpaceItemDecoration;
import com.skx.tomikecommonlibrary.base.BaseViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;

/**
 * 描述 : 照片墙
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/19 4:08 PM
 */
public class PhotoWallActivity extends SkxBaseActivity<BaseViewModel> {

    private PhotoWallAdapter mAdapter;
    private PhotoUpImageBucket mPhotoAlbum;

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
    protected void subscribeEvent() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPhotoAlbum == null) {
            finish();
            return;
        }
        initView();
        refreshView();
    }

    @Override
    protected boolean useDefaultLayout() {
        return true;
    }

    @Override
    protected void configHeaderTitleView(@NonNull TextView title) {
        super.configHeaderTitleView(title);
        title.setText(mPhotoAlbum.bucketName);
    }

    private void initView() {
        RecyclerView rvPhotoWall = findViewById(R.id.rv_photoWall_photos);
        rvPhotoWall.setLayoutManager(new GridLayoutManager(this, 4));
        rvPhotoWall.addItemDecoration(new GridSpaceItemDecoration(4, 15, 15));
        rvPhotoWall.setAdapter(mAdapter = new PhotoWallAdapter());
        mAdapter.setOnItemClickListener(new PhotoWallAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, PhotoUpImageItem photoUpImageItem) {
                Intent intent = new Intent(mActivity, PicturePreviewActivity.class);
                intent.putExtra("pictureList", mPhotoAlbum.imageList);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

    private void refreshView() {
        mAdapter.setArrayList(mPhotoAlbum.imageList);
    }
}
