package com.skx.tomike.model;

import android.content.Context;

import com.skx.tomike.cannonlaboratory.bean.PhotoUpImageBucket;

import java.util.List;

/**
 * @author shiguotao
 * 
 *         相册model 层
 */
interface PhotoAlbumsModel extends IBaseModel {
	List<PhotoUpImageBucket> getPhotoAlbumsData(Context context);
}
