package com.skx.tomike.model;

import android.content.Context;

import com.skx.tomike.javabean.PhotoUpImageBucket;

import java.util.List;

/**
 * @author shiguotao
 * 
 *         相册model 层
 */
interface PhotoAlbumsModel extends IBaseModel {
	List<PhotoUpImageBucket> getPhotoAlbumsData(Context context);
}
