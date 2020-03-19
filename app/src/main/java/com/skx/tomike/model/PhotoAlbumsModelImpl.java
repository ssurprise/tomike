//package com.skx.tomike.model;
//
//import android.content.Context;
//
//import com.skx.tomike.cannonlaboratory.bean.PhotoUpImageBucket;
//import com.skx.tomike.cannonlaboratory.viewmodel.PhotoAlbumHelper;
//
//import java.util.List;
//
///**
// * @author shiguotao
// *
// *         相册model 层
// */
//public class PhotoAlbumsModelImpl implements PhotoAlbumsModel {
//
//	@Override
//	public List<PhotoUpImageBucket> getPhotoAlbumsData(Context context) {
//		PhotoAlbumHelper helper = PhotoAlbumHelper.getHelper();
//		helper.init(context);
//		return helper.getImagesBucketList(false);
//	}
//}
