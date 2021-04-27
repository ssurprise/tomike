package com.skx.tomike.cannon.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 一个目录下的相册对象
 */
public class PhotoUpImageBucket implements Serializable {

    public int count = 0;
    public String bucketName = "";
    public final ArrayList<PhotoUpImageItem> imageList = new ArrayList<>();

    public ArrayList<PhotoUpImageItem> getImageList() {
        return imageList;
    }

}
