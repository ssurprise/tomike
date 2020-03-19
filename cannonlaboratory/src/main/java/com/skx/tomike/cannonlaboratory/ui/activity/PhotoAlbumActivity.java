package com.skx.tomike.cannonlaboratory.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.skx.tomike.cannonlaboratory.R;
import com.skx.tomike.cannonlaboratory.bean.PhotoUpImageItem;
import com.skx.tomike.cannonlaboratory.ui.adapter.PhotoAlbumItemAdapter;
import com.skx.tomike.cannonlaboratory.viewmodel.PhotoAlbumViewModel;
import com.skx.tomikecommonlibrary.base.SkxBaseActivity;

import java.util.List;

/**
 * 描述 : 照片墙
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020/3/19 4:08 PM
 */
public class PhotoAlbumActivity extends SkxBaseActivity<PhotoAlbumViewModel> implements OnClickListener {

    private GridView cAlbumItemGridView;
    private PhotoAlbumItemAdapter adapter;
    private TextView cAlbumNameTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        refreshView();
        installListener();
    }

    @Override
    protected void initParams() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_album;
    }

    @Override
    protected void subscribeEvent() {

    }

    private void initView() {
        cAlbumItemGridView = findViewById(R.id.album_item_gridv);
        cAlbumNameTv = findViewById(R.id.album_albumName);
    }

    private void refreshView() {
//        PhotoAlbumPresenterImpl persenter = new PhotoAlbumPresenterImpl(this, getIntent());
//        persenter.initData();
    }

    private void installListener() {
        cAlbumItemGridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//    }
//
//    @Override
//    public void setPhotoAlbumName(String name) {
//        cAlbumNameTv.setText(name);
//    }
//
//    @Override
//    public void setPhotoAlbum(List<PhotoUpImageItem> imageList) {
//        adapter = new PhotoAlbumItemAdapter(imageList, PhotoAlbumActivity.this);
//        cAlbumItemGridView.setAdapter(adapter);
//    }

//
    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.album_return_albums:
//                onBackPressed();
//                break;
//            case R.id.album_preview:
//                break;
//
//            default:
//                break;
//        }
    }
}
