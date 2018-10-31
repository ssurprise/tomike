package com.skx.tomike.model;

import android.content.Intent;

import com.skx.tomike.javabean.PhotoUpImageBucket;

/**
 * @author shiguotao
 * 
 *         相册model 层
 */
public interface PhotoAlbumModel extends IBaseModel {
	PhotoUpImageBucket getPhotoAlbumData(Intent intent);
}
