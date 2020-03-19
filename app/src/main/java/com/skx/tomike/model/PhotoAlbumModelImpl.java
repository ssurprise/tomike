package com.skx.tomike.model;

import android.content.Intent;

import com.skx.tomike.cannonlaboratory.bean.PhotoUpImageBucket;

/**
 * @author shiguotao
 * 
 *         相册model 层
 */
public class PhotoAlbumModelImpl implements PhotoAlbumModel {
	@Override
	public PhotoUpImageBucket getPhotoAlbumData(Intent intent) {
		if (intent == null) {
			return new PhotoUpImageBucket();
		}
		if (intent.getExtras().containsKey("imagelist")) {
			return (PhotoUpImageBucket) intent.getSerializableExtra("imagelist");
		} else {
			return new PhotoUpImageBucket();
		}
	}
}
