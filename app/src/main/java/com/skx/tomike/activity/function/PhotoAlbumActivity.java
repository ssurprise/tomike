package com.skx.tomike.activity.function;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxBaseActivity;
import com.skx.tomike.adapter.PhotoAlbumItemAdapter;
import com.skx.tomike.iview.IPhotoAlbumView;
import com.skx.tomike.javabean.PhotoUpImageItem;
import com.skx.tomike.viewmodel.PhotoAlbumPresenterImpl;

import java.util.List;

/**
 * @author shiguotao
 * <p>
 * 相册
 */
public class PhotoAlbumActivity extends SkxBaseActivity implements IPhotoAlbumView, OnClickListener {
    private GridView cAlbumItemGridView;
    private PhotoAlbumItemAdapter adapter;
    private TextView cAlbumNameTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        refreshView();
        installListener();
    }

    @Override
    public void initializeView() {
        setContentView(R.layout.activity_photo_album);
        cAlbumItemGridView = (GridView) findViewById(R.id.album_item_gridv);
        cAlbumNameTv = (TextView) findViewById(R.id.album_albumName);
    }

    @Override
    public void refreshView() {
        PhotoAlbumPresenterImpl persenter = new PhotoAlbumPresenterImpl(this, getIntent());
        persenter.initData();
    }

    @Override
    public void installListener() {
        cAlbumItemGridView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setPhotoAlbumName(String name) {
        cAlbumNameTv.setText(name);
    }

    @Override
    public void setPhotoAlbum(List<PhotoUpImageItem> imageList) {
        adapter = new PhotoAlbumItemAdapter(imageList, PhotoAlbumActivity.this);
        cAlbumItemGridView.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.album_return_albums:
                onBackPressed();
                break;
            case R.id.album_preview:
                break;

            default:
                break;
        }
    }
}
