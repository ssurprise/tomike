package com.skx.tomike.javabean;

import java.io.Serializable;

/**
 * @author shiguotao
 * 
 *         照片，图片 javabean
 */
public class PhotoUpImageItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6542928624662570240L;
	// 图片ID
	private String imageId;
	// 原图路径
	private String imagePath;
	// 是否被选择
	private boolean isSelected = false;

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

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

}
