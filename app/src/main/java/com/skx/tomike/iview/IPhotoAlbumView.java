package com.skx.tomike.iview;

import com.skx.tomike.javabean.PhotoUpImageItem;

import java.util.List;

/**
 * @author shiguotao
 * 
 *         相册接口
 */
public interface IPhotoAlbumView{
	void setPhotoAlbumName(String name);
	void setPhotoAlbum(List<PhotoUpImageItem> imageList);
}
