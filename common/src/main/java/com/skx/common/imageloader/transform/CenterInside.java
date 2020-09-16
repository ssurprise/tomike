package com.skx.common.imageloader.transform;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import java.security.MessageDigest;

/**
 * Returns the image with its original size if its dimensions match or are smaller
 * than the target's, couple with {@link android.widget.ImageView.ScaleType#CENTER_INSIDE}
 * in order to center it in Target. If not, then it is scaled so that one of the dimensions of
 * the image will be equal to the given dimension and the other will be less than the given
 * dimension (maintaining the image's aspect ratio).
 * <p>
 * 注：最终转换成Glide 提供的 CenterInside，这里不用纠结实现内容，可以忽略！
 */
public final class CenterInside implements TransformAdapter {
    private static final String ID = "com.skx.tomikecommonlibrary.imageloader.transform.CenterInside";
    private static final byte[] ID_BYTES = ID.getBytes(CHARSET);

    @Override
    public boolean equals(Object o) {
        return o instanceof CenterInside;
    }

    @Override
    public Bitmap transform(@NonNull Bitmap toTransform, int outWidth, int outHeight) {
        return null;
    }

    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        messageDigest.update(ID_BYTES);
    }

    @Override
    public int hashCode() {
        return ID.hashCode();
    }
}


