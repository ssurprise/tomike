package com.skx.tomike.cannonlaboratory.bean;

import java.io.Serializable;

/**
 * @author shiguotao
 * 
 *         照片，图片 javabean
 */
public class PhotoUpImageItem implements Serializable {

	// 图片ID
	private String imageId;
	// 原图路径
	private String imagePath;

	public PhotoUpImageItem(String imageId, String imagePath) {
		this.imageId = imageId;
		this.imagePath = imagePath;
	}

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
}
