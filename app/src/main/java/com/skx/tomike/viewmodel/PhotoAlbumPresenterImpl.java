//package com.skx.tomike.viewmodel;
//
//import android.content.Intent;
//
//import com.skx.tomike.cannonlaboratory.bean.PhotoUpImageBucket;
//import com.skx.tomike.model.PhotoAlbumModel;
//import com.skx.tomike.model.PhotoAlbumModelImpl;
//
///**
// * @author shiguotao
// *
// *         相册主导器
// */
//public class PhotoAlbumPresenterImpl extends AbsBasePresenter<IPhotoAlbumView>{
//	private IPhotoAlbumView mIView;
//	private PhotoAlbumModel photoAlbumModel;
//	private Intent mIntent = null;
//	public PhotoAlbumPresenterImpl(IPhotoAlbumView view ,Intent intent) {
//		this.mIView = view;
//		photoAlbumModel = new PhotoAlbumModelImpl();
//		mIntent = intent;
//
//	}
//
//	@Override
//	public void initData() {
//		PhotoUpImageBucket photoAlbumData = photoAlbumModel.getPhotoAlbumData(mIntent);;
//		mIView.setPhotoAlbum(photoAlbumData.getImageList());
//		mIView.setPhotoAlbumName(photoAlbumData.getBucketName());
//	}
//}
