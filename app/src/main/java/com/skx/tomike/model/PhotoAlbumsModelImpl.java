package com.skx.tomike.model;

import android.content.Context;

import com.skx.tomike.javabean.PhotoUpImageBucket;
import com.skx.tomike.util.PhotoAlbumHelper;

import java.util.List;

/**
 * @author shiguotao
 * 
 *         相册model 层
 */
public class PhotoAlbumsModelImpl implements PhotoAlbumsModel {

	@Override
	public List<PhotoUpImageBucket> getPhotoAlbumsData(Context context) {
		PhotoAlbumHelper helper = PhotoAlbumHelper.getHelper();
		helper.init(context);
		return helper.getImagesBucketList(false);
	}
}
