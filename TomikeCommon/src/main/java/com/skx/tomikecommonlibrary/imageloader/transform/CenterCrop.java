package com.skx.tomikecommonlibrary.imageloader.transform;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

/**
 * 作者：shiguotao
 * 日期：2018/11/7 11:54 AM
 * 描述：缩放图像，使图像的宽度与给定的宽度匹配，图像的高度大于给定的高度，反之亦然，然后裁剪较大的尺寸以匹配给定的尺寸。
 */
public class CenterCrop implements Transformation {
    private static final String ID = "com.bumptech.glide.load.resource.bitmap.CenterCrop";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    @Override
    public boolean equals(Object o) {
        return o instanceof CenterCrop;
    }

    @Override
    public Bitmap transform(@NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return null;
    }

    @Override
    public byte[] diskCacheKey() {
        return ID_BYTES;
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }

}
