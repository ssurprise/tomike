package com.skx.tomike.viewmodel;

import android.content.Context;
import android.os.AsyncTask;

import com.skx.tomike.iview.IPhotoAlbumsView;
import com.skx.tomike.javabean.PhotoUpImageBucket;
import com.skx.tomike.model.PhotoAlbumsModelImpl;

import java.util.List;

/**
 * @author shiguotao
 * 
 *         相册列表主导器
 */
public class PhotoAlbumsPresenterImpl extends AbsBasePresenter<IPhotoAlbumsView> {

	private List<PhotoUpImageBucket> photoAlbumsData;
	private IPhotoAlbumsView mIView;
	private PhotoAlbumsModelImpl modelImpl;
	private Context mContext;

	public PhotoAlbumsPresenterImpl(IPhotoAlbumsView iView ,Context context) {
		super();
		this.mIView = iView;
		this.mContext = context;
		modelImpl = new PhotoAlbumsModelImpl();
	}

	@Override
	public void initData() {
		new AsyncTask<String, String, List<PhotoUpImageBucket>>() {

			@Override
			protected List<PhotoUpImageBucket> doInBackground(String... params) {
				photoAlbumsData = modelImpl.getPhotoAlbumsData(mContext);
				return modelImpl.getPhotoAlbumsData(mContext);
			}

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				mIView.loadingData();
			}

			@Override
			protected void onPostExecute(List<PhotoUpImageBucket> result) {
				super.onPostExecute(result);
				mIView.loadedData();
				mIView.setPhotoAlbums(result);

			}
		}.execute("");
	}
}
