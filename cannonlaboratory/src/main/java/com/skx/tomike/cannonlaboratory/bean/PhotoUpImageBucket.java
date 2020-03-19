package com.skx.tomike.cannonlaboratory.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 一个目录下的相册对象
 */
public class PhotoUpImageBucket implements Serializable {

    public int count = 0;
    public String bucketName = "";
    public final List<PhotoUpImageItem> imageList = new ArrayList<>();

    public List<PhotoUpImageItem> getImageList() {
        return imageList;
    }

}
