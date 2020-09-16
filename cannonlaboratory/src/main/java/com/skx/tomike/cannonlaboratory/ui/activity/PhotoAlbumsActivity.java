package com.skx.tomike.cannonlaboratory.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.skx.tomike.cannonlaboratory.R;
import com.skx.tomike.cannonlaboratory.bean.PhotoUpImageBucket;
import com.skx.tomike.cannonlaboratory.ui.adapter.PhotoAlbumsAdapter;
import com.skx.tomike.cannonlaboratory.ui.view.GridSpaceItemDecoration;
import com.skx.tomike.cannonlaboratory.viewmodel.PhotoAlbumViewModel;
import com.skx.common.base.SkxBaseActivity;
import com.skx.common.base.TitleConfig;
import com.skx.common.utils.DpPxSpToolKt;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * 描述 : 相册。主要知识点：ContentProvider 获取相册照片。
 * <p>
 * 注意：读取相册需要申请权限（Manifest.permission.READ_EXTERNAL_STORAGE）
 * <p>
 * 作者 : shiguotao
 * 版本 : V1
 * 创建时间 : 2020-03-20 00:10
 */
public class PhotoAlbumsActivity extends SkxBaseActivity<PhotoAlbumViewModel> {

    private static final int REQUEST_PERMISSION = 0;

    private PhotoAlbumsAdapter mPhotoAlbumsAdapter;

    @Override
    protected void initParams() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo_albums;
    }

    @Override
    protected void subscribeEvent() {
        mViewModel.getPhotoAlbumsLiveData().observe(this, new Observer<List<PhotoUpImageBucket>>() {
            @Override
            public void onChanged(List<PhotoUpImageBucket> photoUpImageBuckets) {
                mPhotoAlbumsAdapter.setArrayList(photoUpImageBuckets);
            }
        });
    }

    @Override
    protected TitleConfig configHeaderTitle() {
        return new TitleConfig.Builder().setTitleText("相册").create();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                /*
                 * 这里权限模式
                 */
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // 解释为什么需要定位权限之类的
                } else {
                    // 请求权限处理
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
                }
                /*
                 * 如果要用意图模式的话，就不需要用权限模式了，直接跳转到系统设置页面，
                 * 让用户自己控制权限的授权与否，app只承担了一个引导作用
                 */
            } else {
                mViewModel.loadPhotoData();
            }
        } else {
            mViewModel.loadPhotoData();
        }
    }

    @Override
    protected void initView() {
        RecyclerView rvPhotoAlbums = findViewById(R.id.rv_photoAlbums_content);
        rvPhotoAlbums.setLayoutManager(new GridLayoutManager(this, 3));
        rvPhotoAlbums.addItemDecoration(new GridSpaceItemDecoration(3,
                DpPxSpToolKt.dip2px(this, 30),
                DpPxSpToolKt.dip2px(this, 10)));
        rvPhotoAlbums.setAdapter(mPhotoAlbumsAdapter = new PhotoAlbumsAdapter());
        mPhotoAlbumsAdapter.setOnItemClickListener(new PhotoAlbumsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, PhotoUpImageBucket photoAlbum) {
                Intent intent = new Intent(PhotoAlbumsActivity.this, PhotoWallActivity.class);
                intent.putExtra("imagelist", photoAlbum);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMISSION: {
                // 如果授权被取消，结果数组是 空的，注意这里是empty ，而不是null
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 已经授权，在这里做你想要做的事
                    mViewModel.loadPhotoData();
                } else {
                    // 拒绝授权
                    finish();
                }
                break;
            }
            // 其他的权限请求处理
            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }
    }
}
