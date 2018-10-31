package com.skx.tomike.iview;

import com.skx.tomike.javabean.PhotoUpImageBucket;

import java.util.List;

/**
 * @author shiguotao
 * 
 *         相册列表接口
 */
public interface IPhotoAlbumsView extends IBaseView{
	void setPhotoAlbums(List<PhotoUpImageBucket> imageList);
	void showPhotoAlbum(PhotoUpImageBucket PhotoUpImageBucket);
}
