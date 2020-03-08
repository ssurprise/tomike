package com.skx.tomike.activity.function;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.skx.tomike.R;
import com.skx.tomike.activity.SkxMVPBaseActivity;
import com.skx.tomike.adapter.PhotoAlbumsAdapter;
import com.skx.tomike.iview.IPhotoAlbumsView;
import com.skx.tomike.javabean.PhotoUpImageBucket;
import com.skx.tomike.viewmodel.PhotoAlbumsPresenterImpl;

import java.util.List;

/**
 * @author shiguotao
 * <p>
 * 相册
 */
public class PhotoAlbumsActivity extends SkxMVPBaseActivity<IPhotoAlbumsView, PhotoAlbumsPresenterImpl> implements IPhotoAlbumsView {
    private GridView cAlbumGridView;
    private PhotoAlbumsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeView();
        installListener();

        mPresenter.initData();

    }

    @Override
    protected PhotoAlbumsPresenterImpl createPresenter() {
        return new PhotoAlbumsPresenterImpl(this, this);
    }


    @Override
    public void initializeView() {
        setContentView(R.layout.activity_photo_albums);
        cAlbumGridView = (GridView) findViewById(R.id.album_gridv);
    }

    @Override
    public void installListener() {
        cAlbumGridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                mPresenter.showAlbum(position);
            }
        });
    }

    @Override
    public void setPhotoAlbums(List<PhotoUpImageBucket> imageList) {
        adapter = new PhotoAlbumsAdapter(PhotoAlbumsActivity.this, imageList);
        cAlbumGridView.setAdapter(adapter);
    }

    @Override
    public void loadingData() {
        Toast.makeText(PhotoAlbumsActivity.this, "数据加载中...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadedData() {
        Toast.makeText(PhotoAlbumsActivity.this, "数据加载完毕...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPhotoAlbum(PhotoUpImageBucket PhotoUpImageBucket) {
        Intent intent = new Intent(PhotoAlbumsActivity.this, PhotoAlbumActivity.class);
        intent.putExtra("imagelist", PhotoUpImageBucket);
        startActivity(intent);
    }
}
