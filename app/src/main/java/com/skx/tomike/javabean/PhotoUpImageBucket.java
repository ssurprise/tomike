package com.skx.tomike.javabean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 一个目录下的相册对象
 */
public class PhotoUpImageBucket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3610252726003897942L;
	public int count = 0;
	public String bucketName = "";
	public List<PhotoUpImageItem> imageList = new ArrayList<>();

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public List<PhotoUpImageItem> getImageList() {
		return imageList;
	}

	public void setImageList(List<PhotoUpImageItem> imageList) {
		this.imageList = imageList;
	}

}
